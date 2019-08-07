package rockPaperScissors.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import rockPaperScissors.model.Game;
import rockPaperScissors.model.User;
import rockPaperScissors.model.UserNameAndWinRate;
import rockPaperScissors.model.History;


import javax.persistence.TypedQuery;
import javax.swing.text.Document;
import javax.transaction.Transactional;

import java.util.Dictionary;
import java.util.List;
import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.userName = ?1")
    User findByUserName(String userName);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update User set win_game_number=?1, total_game=?2 where user_name=?3")
    void setResultGame(Integer winGame,Integer totalGame,String username);

//    @Query("select u.winRate from User u where u.userName = ?1")
//    Float getWinRate(String userName);


    @Query("select new rockPaperScissors.model.UserNameAndWinRate(u.userName, u.winGameNumber,u.totalGame)  from User u order by u.winGameNumber/u.totalGame desc")
    List<UserNameAndWinRate> getTop100Users(Pageable pageable);

//    @Query(value = "select u.id, u.user_name, u.win_game_number, u.user_password, u.total_game from user u order by (u.win_game_number/u.total_game) desc limit 100;",nativeQuery = true)
//    Optional<List<User>> getTopp100Users();

//    @Query("select new rockPaperScissors.model.History(u.userName, g.id,g.dateTimePlay,g.result,t) from User u join Game g on u=g.user join Turn t on g=t.game  where u.userName=?1")
//    List<History> getHistoryByUserName(String userName);

}