package rockPaperScissors.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity // This tells Hibernate to make a table out of this class
//@AllArgsConstructor
//@Data


public class Game {
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

    private Integer idUser;
    private Integer result;
    private Date dateTimePlay;
    private Integer turnNumber;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Date getDateTimePlay() {
        return dateTimePlay;
    }

    public void setDateTimePlay(Date dateTimePlay) {
        this.dateTimePlay = dateTimePlay;
    }

    public Integer getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(Integer turnNumer) {
        this.turnNumber = turnNumer;
    }
}
