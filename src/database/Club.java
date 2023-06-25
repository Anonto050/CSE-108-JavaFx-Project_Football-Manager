package database;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Club {
    private String club_name;
    private List<Player> players;
    private double max_salary;
    private double max_height;
    private int max_age;
    private int player_count;

    public Club(){
        players = new ArrayList();
    }
    public Club(String club_name,List<Player> Players){
        this.club_name=club_name;
        players = new ArrayList();
        for (Player p:Players) {
            if (p.getClub().equalsIgnoreCase(club_name)) {
                players.add(p);
            }
        }
        for (Player p:players) {
            if(max_salary<p.getWeekly_Salary()){
                max_salary=p.getWeekly_Salary();
            }
            if(max_age<p.getAge()){
                max_age=p.getAge();
            }
            if(max_height<p.getHeight()){
                max_height=p.getHeight();
            }
        }
    }

    public void setClub_name(String club_name) {
        this.club_name = club_name;
    }

    public String getClub_name() {
        return club_name;
    }

    public int getPlayer_count() {
        player_count=players.size();
        return player_count;
    }

    public void setPlayers(List<Player> Players) {
        players = new ArrayList();
        for (Player p:Players) {
            if(p.getClub().equalsIgnoreCase(club_name)){
                players.add(p);
            }
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public double getMax_height() {
        return max_height;
    }

    public double getMax_salary() {
        return max_salary;
    }

    public int getMax_age() {
        return max_age;
    }

    public List<Player> player_max_salary(){
        List<Player> temp=new ArrayList();
        for (Player p:players) {
            if(p.getWeekly_Salary()==max_salary){
                temp.add(p);
            }
        }
        if(temp.isEmpty()){
            return null;
        }
        return temp;
    }
    public List<Player> player_max_age(){
        List<Player> temp=new ArrayList();
        for (Player p:players) {
            if(p.getAge()==max_age){
                temp.add(p);
            }
        }
        if(temp.isEmpty()){
            return null;
        }
        return temp;
    }
    public List<Player> player_max_height(){
        List<Player> temp=new ArrayList();
        for (Player p:players) {
            if(p.getHeight()==max_height){
                temp.add(p);
            }
        }
        if(temp.isEmpty()){
            return null;
        }
        return temp;
    }
    public double totalSalary(){
        double totalSalary=0;
        for (Player p :players) {
            totalSalary=totalSalary+52*p.getWeekly_Salary();
        }
        return totalSalary;
    }
    public Player search_byPlayerName(String playerName) {
        for (Player p : players) {
            if (p.getName().equalsIgnoreCase(playerName)) {
                return p;
            }
            else if(p.getName().toUpperCase(Locale.ROOT).contains(playerName.toUpperCase(Locale.ROOT))){
                return p;
            }
        }
        return null;
    }
    public List<Player> search_byCountry(String CountryName) {
        List<Player> temp=new ArrayList();
        for (Player p : players) {
            if (p.getCountry().equalsIgnoreCase(CountryName)) {
                temp.add(p);
            }
        }
        if(temp.isEmpty()){
            return null;
        }
        return temp;
    }
    public List<Player> search_byPosition(String position) {
        List<Player> temp = new ArrayList();

        for (Player p : players) {
            if (p.getPosition().equalsIgnoreCase(position)) {
                temp.add(p);
            }
        }
        if(temp.isEmpty()){
            return null;
        }
        return temp;
    }
    public List<Player> search_bySalary(double lower, double upper) {
        List<Player> temp = new ArrayList();
        for (Player p : players) {
            if (p.getWeekly_Salary() >= lower && p.getWeekly_Salary() <= upper) {
                temp.add(p);
            }
        }
        if(temp.isEmpty()){
            return null;
        }
        return temp;
    }
}
