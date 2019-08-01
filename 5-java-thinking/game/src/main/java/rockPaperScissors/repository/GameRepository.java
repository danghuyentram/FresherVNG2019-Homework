package rockPaperScissors.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import rockPaperScissors.model.Game;

import javax.transaction.Transactional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface GameRepository extends JpaRepository<Game, Integer> {
    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update Game set result=?1 where id=?2")
    void setGameResult(Integer result,Integer id);


    @Query("select count(*) from Game where id=?1")
    Integer getGameNumberByUserId(Integer id_user);

    @Query("select count(*) from Game where id=?1 and result=1")
    Integer getWinGameNumberByUserId(Integer id_user);

    @Query("select u.id from User u  join Game g on u.id=g.idUser where g.id=?1")
    Integer getUserIdByGameId(Integer id_game);
}