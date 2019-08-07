package rockPaperScissors.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import rockPaperScissors.model.Game;

import javax.transaction.Transactional;
import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface GameRepository extends JpaRepository<Game, Integer> {
    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update Game set result=?1, turn_number=?2 where id=?3")
    void setGameResult(Integer result,Integer turnNumber,Integer id);


    @Query("select count(*) from Game where id=?1")
    Integer getGameNumberByUserId(Integer id_user);

    @Query("select count(*) from Game where id=?1 and result=1")
    Integer getWinGameNumberByUserId(Integer id_user);

//    @Query("select u.id from User u  join Game g on u.id=g.idUser where g.id=?1")
//    Integer getUserIdByGameId(Integer id_game);

    List<Game> findByUserId(Integer userId);

    @Query(value = "select count(*) from user u join game g on u.id=g.user_id where u.user_name =?1 and g.id=?2",nativeQuery = true)
    Integer checkExistsGameWithUserName(String userName,Integer gameId);
}