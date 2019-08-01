package rockPaperScissors.model;

public class UserNameAndWinRate {
    private String userName;
    private Float winRate;

    public UserNameAndWinRate(String userName,Float winRate){
        this.userName = userName;
        this.winRate = winRate;
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
