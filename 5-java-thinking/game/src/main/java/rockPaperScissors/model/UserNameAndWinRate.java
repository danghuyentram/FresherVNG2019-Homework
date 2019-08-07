package rockPaperScissors.model;

public class UserNameAndWinRate {
    private String userName;
    private Float winRate;
    private Integer winGameNumber;
    private Integer totalGame;

    public UserNameAndWinRate(String userName,Integer winGameNumber,Integer totalGame){
        this.userName = userName;
        this.winGameNumber = winGameNumber;
        this.totalGame = totalGame;
        this.winRate = this.winGameNumber/this.totalGame*100.f;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Float getWinRate() {
        return winRate;
    }

    public void setWinRate(Float winRate) {
        this.winRate = winRate;
    }
}
