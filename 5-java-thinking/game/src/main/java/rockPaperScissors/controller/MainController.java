package rockPaperScissors.controller;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import rockPaperScissors.filter.JWTAuthenticationFilter;
import rockPaperScissors.model.Game;
import rockPaperScissors.model.Turn;
import rockPaperScissors.model.User;
import rockPaperScissors.model.UserNameAndWinRate;
import rockPaperScissors.repository.GameRepository;
import rockPaperScissors.repository.TurnRepository;
import rockPaperScissors.repository.UserRepository;
import rockPaperScissors.service.TokenAuthenticationService;

import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

public class MainController {
    private static final Logger logger = LogManager.getLogger(MainController.class);



    private  int low = 0;
    private  int high = 3;

    public Integer getBotStep(){
        Random r = new Random();
        return r.nextInt(high-low) + low;
    }

    public Integer getResult(Integer userStep,Integer botStep){
        // -1: rock, 0: paper, 1: scissors
        // result = 0: draw, -1: user lose, 1: user win

        if(userStep==botStep)
            return 0;
        else if((userStep==-1 && botStep==0)||(userStep==0 && botStep==1) || (userStep==1 && botStep==-1))
            return -1;
        else
            return 1;

    }



    public ResponseEntity<String> addNewUser (String username
            , String password, UserRepository userRepository) {

        if(username.length()<3|| username.length()>20){
            logger.info("Error with signup: Username's length is >=3 and <=20");
            return ResponseEntity.status(400).body("Username's length is >=3 and <=20, try with another username");
        }

        if(password.length()<3|| password.length()>18){
            logger.info("Error with signup: Password's length is >=3 and <=18");
            return ResponseEntity.status(400).body("Password's length is >=3 and <=18, try with another password");
        }

        Jedis jedis = new Jedis("localhost");

        User n = new User();
        n.setUserName(username);
        n.setUserPassword(password);
        n.setTotalGame(-1);
        n.setWinGameNumber(0);

        if(userRepository.findByUserName(username)!=null){
            logger.info("Username is exists, try with another username");
            return ResponseEntity.status(409).body("Username is exists, try with another username");
        }

        userRepository.save(n);
        String key = "user:"+n.getUserName();

        Map<String,String> mapUser = new HashMap<>();
        mapUser.put("winGameNumber","0");
        mapUser.put("totalGameNumber","-1");

        try{
            logger.info("save user={}, data={}", key, mapUser);
            jedis.hmset(key,mapUser);
        }catch (Exception e){
            logger.error("save redis failed, key={}, error={}", key, e);
        }

        logger.info("New user created: userName: "+username);
        jedis.close();
        return ResponseEntity.status(201).body("New user created ");
    }




    public ResponseEntity<String> createNewGame(String userName,UserRepository userRepository,GameRepository gameRepository){
        User user = userRepository.findByUserName(userName);
        String res;
        Jedis jedis = new Jedis("localhost");

        if(user!=null){
            Date now = new Date();
            Game game = new Game(user,now,0);
            gameRepository.save(game);


            String key = "game:"+game.getId().toString();
            Map<String,String> mapGame = new HashMap<>();
            mapGame.put("turn","0");
            mapGame.put("result","0");
            mapGame.put("isEndGame","0"); // 0 is not end game, 1 is end game

            try{

                jedis.hmset(key,mapGame);
            }catch (Exception e){
                logger.error(e);

            }

            Dictionary<String,String> dictionary = new Hashtable<String,String>();
            dictionary.put("NewGameId",game.getId().toString());
            Gson gson = new Gson();
            String json = gson.toJson(dictionary);

            logger.info(json);
            return ResponseEntity.status(201).body(json);


        }
        logger.info("Failed to authenticate, is fake token");

        jedis.close();
        return ResponseEntity.status(403).body("Failed to authenticate");

    }

    public String getResponseString(String key, String value){
        Dictionary<String,String> dictionary = new Hashtable<String,String>();
        dictionary.put(key,value);
        Gson gson = new Gson();
        return  gson.toJson(dictionary);

    }


    public ResponseEntity<String> createNewTurn(Integer idgame,Integer userstep,String userName,UserRepository userRepository,GameRepository gameRepository,TurnRepository turnRepository){
        Jedis jedis = new Jedis("localhost");


        if(userRepository.findByUserName(userName)==null){
            logger.info("Failed to authenticate, is fake token");
            return ResponseEntity.status(403).body(getResponseString("error","Failed to authenticate"));
        }


        if(gameRepository.checkExistsGameWithUserName(userName,idgame)==0){
            logger.info("Not exists game of user: "+userName);
            return ResponseEntity.status(400).body( getResponseString("error","Not exists game of user: "+userName));
        }

        Optional<Game> game = gameRepository.findById(idgame);

        if(userstep!=0 && userstep!=-1 && userstep!=1){
            logger.info("User step is not corret of user: "+userName);
            return ResponseEntity.status(400).body( getResponseString("error","Userstep is not correct, userstep is can be [0,-1,1]: "));
        }

        Integer turnNow,isEndGame,winGameNumber,totalGame;
        Map<String,String> gameMap = jedis.hgetAll( "game:"+idgame.toString());
        turnNow = Integer.parseInt(gameMap.get("turn"));
        isEndGame = Integer.parseInt(gameMap.get("isEndGame"));

        try{
            Map<String,String> mapUser = jedis.hgetAll("user:"+userName);
            winGameNumber=Integer.parseInt(mapUser.get("winGameNumber"));
            totalGame = Integer.parseInt(mapUser.get("totalGameNumber"));
        }catch (Exception e){
            logger.error(e);
            User user = userRepository.findByUserName(userName);
            winGameNumber = user.getWinGameNumber();
            totalGame=user.getTotalGame();
        }


        Integer result = getResult(userstep,getBotStep());
        Turn turn = new Turn(game.get(),userstep,getBotStep(),result,new Date());
        turnRepository.save(turn);

        String res="";
        if(result==0 )
            res+= "Draw - continue until win or lose";
        else if(result==-1)
            res+="You lose - end game";
        else {
            res+="You win - end game";
            winGameNumber +=1;
            jedis.hset("user:"+userName,"winGameNumber",winGameNumber.toString());
        }

        if(isEndGame==1)
            return ResponseEntity.status(200).body(getResponseString("error","End turns of game - Create new game to continue"));


        turnNow++;

        jedis.hset("game:"+idgame.toString(),"turn",turnNow.toString());
        jedis.hset("game:"+idgame.toString(),"result",result.toString());

        if(result!=0){
            isEndGame=1;
            jedis.hset("game:"+idgame.toString(),"isEndGame",isEndGame.toString());
            if(totalGame==-1)
                totalGame=totalGame+2;
            else
                totalGame=totalGame+ 1;

            try{
                jedis.hset("user:"+userName,"totalGameNumber",totalGame.toString());
            }
            catch (Error e){
                logger.error(e);
            }

            gameRepository.setGameResult(result,turnNow,idgame);
            userRepository.setResultGame(winGameNumber,totalGame,userName);

        }
        jedis.close();
        return ResponseEntity.status(200).body(res);

    }


    public ResponseEntity<List<Game>> getHistory(String userName,UserRepository userRepository,GameRepository gameRepository){
//        List<History> histories = userRepository.getHistoryByUserName(TokenAuthenticationService.getUsername());
        User user = userRepository.findByUserName(userName);
        if(user!=null){
            List<Game> histories = gameRepository.findByUserId(user.getId());
//        String json = new Gson().toJson(histories);
            logger.info("User: "+userName+" get history");
            return ResponseEntity.status(200).body(histories);
        }

        logger.info("Failed to authenticate, is fake token");
        return ResponseEntity.status(403).body(null);

    }


    public ResponseEntity<List<UserNameAndWinRate>>  getTop100(String userName,UserRepository userRepository){
        User user = userRepository.findByUserName(userName);
        if(user!=null){
            Pageable pageable = PageRequest.of(0,100);
            List<UserNameAndWinRate> listUser = userRepository.getTop100Users(pageable);
            logger.info("User:  get top100 users");
            return ResponseEntity.status(200).body(listUser);
        }

        logger.info("Failed to authenticate, is fake token");
        return ResponseEntity.status(403).body(null);

    }


}
