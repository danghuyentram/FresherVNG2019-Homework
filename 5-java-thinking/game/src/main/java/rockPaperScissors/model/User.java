package rockPaperScissors.model;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import javax.persistence.*;


//import lombok.*;

@Entity // This tells Hibernate to make a table out of this class
//@AllArgsConstructor
//@Data
public class User {
    @Id
    @TableGenerator(
            name = "class_gen",
            table = "id_gen",
            pkColumnName = "gen_name",
            valueColumnName = "gen_val",
            allocationSize = 1
    )


    @GeneratedValue(strategy = GenerationType.TABLE,generator = "class_gen")
    private Integer id;

    private String userName;

    private String userPassword;

    private Float winRate;

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


    public Float getWinRate() {
        return winRate;
    }

    public void setWinRate(Float winRate) {
        this.winRate = winRate;
    }



}