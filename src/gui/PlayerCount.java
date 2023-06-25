package gui;

public class PlayerCount {
    private String Country;
    private int PlayerCount;

    public PlayerCount(){

    }
    public PlayerCount(String Country,int PlayerCount){
           this.Country=Country;
           this.PlayerCount=PlayerCount;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public int getPlayerCount() {
        return PlayerCount;
    }

    public void setPlayerCount(int playerCount) {
        PlayerCount = playerCount;
    }
}
