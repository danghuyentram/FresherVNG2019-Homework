package rockPaperScissors.model;

import javax.persistence.*;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


//import lombok.*;

@Entity // This tells Hibernate to make a table out of this class
//@AllArgsConstructor
//@Data
@Table(name = "turn")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Turn {
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "gameId", nullable = false)
    @JsonIgnore
    private Game game;

//    private Integer idGame;

    @Column(name = "user_step")
    private Integer userStep;

    @Column(name = "bot_step")
    private Integer botStep;

    @Column(name = "result")
    private Integer result;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTimePlay;

    public Turn() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public Integer getIdGame() {
//        return idGame;
//    }
//
//    public void setIdGame(Integer idGame) {
//        this.idGame = idGame;
//    }


    public Turn(Game game,Integer userStep,Integer botStep,Integer result,Date dateTimePlay){
        this.game = game;
        this.userStep = userStep;
        this.botStep = botStep;
        this.result = result;
        this.dateTimePlay = dateTimePlay;
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

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}