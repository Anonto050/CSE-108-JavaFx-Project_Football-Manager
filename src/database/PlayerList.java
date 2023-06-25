package database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class PlayerList {
    private List<Player> Players;
    private static final String INPUT_FILE_NAME = "players.txt";
    //private static final String OUTPUT_FILE_NAME = "out.txt";

    public PlayerList(){
        Players = new ArrayList();
    }
    public List<Player> readFromFile() throws Exception {

            BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            String[] tokens = line.split(",");
            Player p = new Player();
            p.setName(tokens[0]);
            p.setCountry(tokens[1]);
            p.setAge(Integer.parseInt(tokens[2]));
            p.setHeight(Double.parseDouble(tokens[3]));
            p.setClub(tokens[4]);
            p.setPosition(tokens[5]);
            p.setNumber(Integer.parseInt(tokens[6]));
            p.setWeekly_Salary(Double.parseDouble(tokens[7]));

            Players.add(p);
        }
        br.close();
        return Players;
    }

    /*public void print() throws Exception{
        readFromFile();
        for (Player p:Players) {
            System.out.println(p.getName());
        }
    }*/

}
