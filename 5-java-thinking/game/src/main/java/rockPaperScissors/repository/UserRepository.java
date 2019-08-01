package rockPaperScissors.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import rockPaperScissors.model.User;
import rockPaperScissors.model.UserNameAndWinRate;
import rockPaperScissors.model.History;


import javax.persistence.TypedQuery;
import javax.swing.text.Document;
import javax.transaction.Transactional;

import java.util.Dictionary;
import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.userName = ?1")
    User findByUserName(String userName);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update User set win_rate=?1 where user_name=?2")
    void setWinRate(Float win_rate,String username);

//    @Query("select u.winRate from User u where u.userName = ?1")
//    Float getWinRate(String userName);


    @Query("select new rockPaperScissors.model.UserNameAndWinRate(u.userName,u.winRate)  from User u")
    List<UserNameAndWinRate> getTop100Users(Pageable pageable);

    @Query("select new rockPaperScissors.model.History(u.userName, g.id,g.dateTimePlay,g.result,t.id,t.result) from User u join Game g on u.id=g.idUser join Turn t on g.id=t.idGame where u.userName=?1")
    List<History> getHistoryByUserName(String userName);
}