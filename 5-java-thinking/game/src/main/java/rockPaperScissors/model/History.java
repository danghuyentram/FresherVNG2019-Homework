package rockPaperScissors.model;

import java.util.Date;
import java.util.List;

public class History {
    private String userName;
    private Integer idGame;
    private Date dateTimePlay;
    private Integer resultGame;
    private List<Turn> turns;

//    private Integer idTurn;
//    private Integer resultTurn;

    public History(String userName,Integer idGame,Date dateTimePlay,Integer resultGame){
        this.userName = userName;
        this.idGame = idGame;
        this.dateTimePlay  = dateTimePlay;
        this.resultGame = resultGame;
//        this.turns = turns;
//        this.idTurn = idTurn;
//        this.resultTurn = resultTurn;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getIdGame() {
        return idGame;
    }

    public void setIdGame(Integer idGame) {
        this.idGame = idGame;
    }

    public Date getDateTimePlay() {
        return dateTimePlay;
    }

    public void setDateTimePlay(Date dateTimePlay) {
        this.dateTimePlay = dateTimePlay;
    }

    public Integer getResultGame() {
        return resultGame;
    }

    public void setResultGame(Integer resultGame) {
        this.resultGame = resultGame;
    }

    public List<Turn> getTurns() {
        return turns;
    }

    public void setTurns(List<Turn> turns) {
        this.turns = turns;
    }

//    public Integer getIdTurn() {
//        return idTurn;
//    }
//
//    public void setIdTurn(Integer idTurn) {
//        this.idTurn = idTurn;
//    }
//
//    public Integer getResultTurn() {
//        return resultTurn;
//    }
//
//    public void setResultTurn(Integer resultTurn) {
//        this.resultTurn = resultTurn;
//    }
}
