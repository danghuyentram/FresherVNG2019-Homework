package rockPaperScissors.model;

import javax.persistence.*;
import java.util.Date;


//import lombok.*;

@Entity // This tells Hibernate to make a table out of this class
//@AllArgsConstructor
//@Data
public class Turn {
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

    private Integer idGame;
    private Integer userStep;
    private Integer botStep;
    private Integer result;
    private Date dateTimePlay;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdGame() {
        return idGame;
    }

    public void setIdGame(Integer idGame) {
        this.idGame = idGame;
    }

    public Integer getUserStep() {
        return userStep;
    }

    public void setUserStep(Integer userStep) {
        this.userStep = userStep;
    }

    public Integer getBotStep() {
        return botStep;
    }

    public void setBotStep(Integer botStep) {
        this.botStep = botStep;
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
}