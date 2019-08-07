package rockPaperScissors.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Set;



@Entity
@Table(name = "user")
public class User {
//    @Id
//    @TableGenerator(
//            name = "class_gen",
//            table = "id_gen",
//            pkColumnName = "gen_name",
//            valueColumnName = "gen_val",
//            allocationSize = 1
//    )

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min=3, max = 50)
    @Column(unique = true)
    private String userName;

    @NotBlank
    @Size(min=3, max = 18)
    private String userPassword;

//    private Float winRate;

    private Integer winGameNumber;

    private Integer totalGame;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Game> games;

    public User() {
    }

    public User(@NotBlank @Size(min = 3, max = 20) String userName,@NotBlank @Size(min = 3, max = 18) String userPassword,Integer winGameNumber,Integer totalGame){
        this.userName=userName;
        this.userPassword=userPassword;
        this.winGameNumber=winGameNumber;
        this.totalGame=totalGame;
    }


    public User(@NotBlank @Size(min = 3, max = 20) String userName, @NotBlank @Size(min = 3, max = 18) String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String email) {
        this.userPassword = email;
    }

//
//    public Float getWinRate() {
//        return winRate;
//    }
//
//    public void setWinRate(Float winRate) {
//        this.winRate = winRate;
//    }


    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public Integer getWinGameNumber() {
        return winGameNumber;
    }

    public void setWinGameNumber(Integer winGameNumber) {
        this.winGameNumber = winGameNumber;
    }

    public Integer getTotalGame() {
        return totalGame;
    }

    public void setTotalGame(Integer totalGame) {
        this.totalGame = totalGame;
    }
}