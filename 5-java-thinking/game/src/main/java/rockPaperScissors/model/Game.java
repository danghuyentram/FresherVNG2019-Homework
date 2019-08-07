package rockPaperScissors.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity // This tells Hibernate to make a table out of this class
//@AllArgsConstructor
//@Data

@Table(name = "game")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Game {
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
    @JoinColumn(name = "userId", nullable = false)
    @JsonIgnore
    private User user;

//    private Integer idUser;

    @Column(name = "result")
    private Integer result;

    @Column(name = "date_time_play")
    private Date dateTimePlay;

    @Column(name = "turn_number")
    private Integer turnNumber;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Turn> turns;


    public Game() {
    }



    public Game(User user,Date dateTimePlay,Integer turnNumber){
        this.user=user;
        this.dateTimePlay=dateTimePlay;
        this.turnNumber=turnNumber;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public Integer getIdUser() {
//        return idUser;
//    }
//
//    public void setIdUser(Integer idUser) {
//        this.idUser = idUser;
//    }

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



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Turn> getTurns() {
        return turns;
    }

    public void setTurns(Set<Turn> turns) {
        this.turns = turns;
    }

    public Integer getId() {
        return id;
    }
}
