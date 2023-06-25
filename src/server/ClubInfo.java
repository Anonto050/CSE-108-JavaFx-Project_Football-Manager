package server;

import database.Player;
import util.NetworkUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClubInfo implements Serializable {
    private String ClubName;
    private String Password;
    private List<Player> ClubPlayers;
    private List<Player> SellingList;
    private List<Player> BuyingList;
    private boolean loggedBefore;
    private NetworkUtil networkUtil;

    public ClubInfo(){
        ClubName=new String("");
        Password=new String("");
        ClubPlayers=new ArrayList();
        SellingList=new ArrayList();
        BuyingList=new ArrayList();
        loggedBefore=false;
    }

    public void setClubName(String clubName) {
        ClubName = clubName;
    }

    public String getClubName() {
        return ClubName;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPassword() {
        return Password;
    }

    public void setClubPlayers(List<Player> clubPlayers) {
        ClubPlayers = clubPlayers;
    }

    public List<Player> getClubPlayers() {
        return ClubPlayers;
    }

    public void setSellingList(List<Player> sellingList) {
        SellingList = sellingList;
    }

    public List<Player> getSellingList() {
        return SellingList;
    }

    public void setBuyingList(List<Player> buyingList) {
        BuyingList = buyingList;
    }

    public List<Player> getBuyingList() {
        return BuyingList;
    }

    public void setNetworkUtil(NetworkUtil networkUtil) {
        this.networkUtil = networkUtil;
    }

    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }
}
