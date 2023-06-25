package database;

import java.util.ArrayList;
import java.util.List;

public class Country {
    private String country_name;
    private List<Player> players;
    private int player_count;

    public Country(){
        players = new ArrayList();
    }
    public Country(String country_name,List<Player> Players){
        this.country_name=country_name;
        players = new ArrayList();
        for (Player p:Players) {
            if(p.getCountry().equalsIgnoreCase(country_name)){
                players.add(p);
            }
        }
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getCountry_name() {
        return country_name;
    }

    public int getPlayer_count(List<Player> Players) {
        for (Player p:Players) {
            if(p.getCountry().equalsIgnoreCase(country_name)){
                player_count++;
            }
        }
        return player_count;
    }

    public void setPlayers(List<Player> Players) {
        players = new ArrayList();
        for (Player p:Players) {
            if(p.getCountry().equalsIgnoreCase(country_name)){
                players.add(p);
            }
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
