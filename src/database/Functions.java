package database;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Functions {
    private PlayerList p;
    private Club loadClub;
    private List<Player> players;
    private List<Club> clubs;
    private List<Country> country;
    private List<Player> clubPlayers;
    private static final String OUTPUT_FILE_NAME = "players.txt";

    public Functions() throws Exception {
        p = new PlayerList();
        players = new ArrayList();
        players = p.readFromFile();

        clubPlayers=new ArrayList();
        loadClub=new Club();

        clubs = new ArrayList();
        country = new ArrayList();

        for (Player p : players) {
            boolean found = false;
            if (!clubs.isEmpty()) {
                for (Club c : clubs) {
                    if (c.getClub_name().equalsIgnoreCase(p.getClub())) {
                        found = true;
                        break;
                    }
                }
            }
            if (found) {
                continue;
            }
            Club c = new Club(p.getClub(), players);
            clubs.add(c);
        }
        for (Player p : players) {
            boolean found = false;
            if (!country.isEmpty()) {
                for (Country c : country) {
                    if (c.getCountry_name().equalsIgnoreCase(p.getCountry())) {
                        found = true;
                        break;
                    }
                }
            }
            if (found) {
                continue;
            }
            Country c = new Country(p.getCountry(), players);
            country.add(c);
        }
    }

    public List<Club> getClubs() {
        return clubs;
    }

    public Club LoadClub(String ClubName){
        for (Club c:clubs) {
            if(c.getClub_name().equalsIgnoreCase(ClubName)){
                clubPlayers=c.getPlayers();
                return c;
            }
        }
        return null;
    }

        public HashMap<String, Integer> country_wise_playerCount() {
            HashMap<String, Integer> map=new HashMap<String, Integer>();
            for (Country c : country) {
                if(c.getPlayer_count(clubPlayers)>0) {
                    map.put(c.getCountry_name(), c.getPlayer_count(clubPlayers));
                }
            }
            return map;
        }

        public void addPlayer()throws Exception {
            Scanner scn = new Scanner(System.in);
            String name;
            int age;
            double height;
            double salary;
            int number;
            String CountryName;
            String ClubName;
            String position;
            List<Player> club_players=new ArrayList();

            while (true) {
                System.out.println("Enter Name : ");
                name = scn.nextLine();
                boolean found = false;
                for (Player p : players) {
                    if (p.getName().equalsIgnoreCase(name)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    break;
                } else {
                    System.out.println("Player with this name already exists. Please enter an unique name.\n");
                }
            }
            System.out.println("Enter Country Name : ");
            CountryName = scn.nextLine();


            while (true) {
                System.out.println("Enter Age in years : ");
                if (scn.hasNextInt()) {
                    age = scn.nextInt();
                    scn.nextLine();
                    if (age < 0) {
                        System.out.println("Please enter a valid age\n ");
                    } else {
                        break;
                    }
                } else {
                    scn.nextLine();
                    System.out.println("Please enter a valid age\n ");
                }
            }


            while (true) {
                System.out.println("Enter Height in meters : ");
                if (scn.hasNextDouble()) {
                    height = scn.nextDouble();
                    scn.nextLine();
                    if (height < 0) {
                        System.out.println("Please enter a valid height\n ");
                    } else {
                        break;
                    }
                } else {
                    scn.nextLine();
                    System.out.println("Please enter a valid height\n ");
                }
            }

            ClubName = checkClub();
            for (Club c:clubs) {
                if(c.getClub_name().equalsIgnoreCase(ClubName)){
                    club_players=c.getPlayers();
                }
            }

            while (true) {
                System.out.println("Enter Position : ");
                position = scn.nextLine();
                if (position.equalsIgnoreCase("Goalkeeper") || position.equalsIgnoreCase("Defender") || position.equalsIgnoreCase("Midfielder") || position.equalsIgnoreCase("Forward")) {
                    break;
                } else {
                    System.out.println("Please enter any of these 4 positions : Goalkeeper, Defender, Midfielder, Forward\n");
                }
            }

            while (true) {
                System.out.println("Enter Number : ");
                if (scn.hasNextInt()) {
                    number = scn.nextInt();
                    scn.nextLine();
                    if (number < 0) {
                        System.out.println("Please enter a valid number\n ");
                    } else {
                        boolean found = false;
                        for (Player p : club_players) {
                            if (p.getNumber() == number) {
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            break;
                        } else {
                            System.out.println("Player with this number already exists. Please enter an unique number.\n");
                        }
                    }
                } else {
                    scn.nextLine();
                    System.out.println("Please enter a valid number\n ");
                }
            }

            while (true) {
                System.out.println("Enter Weekly Salary : ");
                if (scn.hasNextDouble()) {
                    salary = scn.nextDouble();
                    scn.nextLine();
                    if (salary < 0) {
                        System.out.println("Please enter a valid salary\n ");
                    } else {
                        break;
                    }
                } else {
                    scn.nextLine();
                    System.out.println("Please enter a valid salary\n ");
                }
            }
            Player p = new Player(name, CountryName, age, height, ClubName, position, number, salary);
            players.add(p);
            updateInfo();
        }

        public String checkClub() {
            String ClubName;
            Scanner scn = new Scanner(System.in);
            while (true) {
                System.out.println("Enter Club : ");
                ClubName = new String(scn.nextLine());
                boolean possible = true;
                for (Club c : clubs) {
                    if (c.getClub_name().equalsIgnoreCase(ClubName)) {
                        if (c.getPlayer_count() == 7) {
                            possible = false;
                            break;
                        } else {
                            possible = true;
                            break;
                        }
                    }
                }
                if (!possible) {
                    System.out.println("The club already has 7 players. Enter another club.\n");
                } else {
                    break;
                }
            }
            return ClubName;
        }


        public void updateInfo()throws Exception{
            writeToFile(players);

       /* p = new PlayerList();
        players = new ArrayList();
        players = p.readFromFile();

        clubs = new ArrayList();
        country = new ArrayList();

        for (Player p : players) {
            boolean found = false;
            if (!clubs.isEmpty()) {
                for (Club c : clubs) {
                    if (c.getClub_name().equalsIgnoreCase(p.getClub())) {
                        found = true;
                        break;
                    }
                }
            }
            if (found) {
                continue;
            }
            Club c = new Club(p.getClub(), players);
            clubs.add(c);
        }
        for (Player p : players) {
            boolean found = false;
            if (!country.isEmpty()) {
                for (Country c : country) {
                    if (c.getCountry_name().equalsIgnoreCase(p.getCountry())) {
                        found = true;
                        break;
                    }
                }
            }
            if (found) {
                continue;
            }
            Country c = new Country(p.getCountry(), players);
            country.add(c);
        }*/
        }

        public void writeToFile(List<Player> playerList) throws Exception {
            BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
            for (Player p : playerList) {
                bw.write(p.getName() + "," + p.getCountry()+ "," + p.getAge()+ "," + p.getHeight()+","+p.getClub()+","+p.getPosition()+","+p.getNumber()+","+p.getWeekly_Salary());
                bw.write("\n");
            }
            bw.close();
        }
    }

