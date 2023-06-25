package server;

import database.*;
import dto.*;
import util.NetworkUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;


public class ReadThreadServer implements Runnable {
    private final Thread thr;
    private final NetworkUtil networkUtil;
    private Functions functions;
    public HashMap<String, ClubInfo> clubMap;
    public HashMap<String, String > userMap;


    public ReadThreadServer(HashMap<String, ClubInfo> map, NetworkUtil networkUtil) {
        this.clubMap = map;
        this.networkUtil = networkUtil;

        userMap = new HashMap<>();
        userMap.put("arsenal", "arsenal");
        userMap.put("liverpool", "liverpool");
        userMap.put("chelsea", "chelsea");
        userMap.put("manchester city", "manchester city");
        userMap.put("manchester united", "manchester united");

        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = networkUtil.read();
                System.out.println(o);
                if (o != null) {
                    if (o instanceof LoginDTO) {
                        LoginDTO loginDTO = (LoginDTO) o;
                        String password = userMap.get(loginDTO.getUserName().toLowerCase(Locale.ROOT));
                        loginDTO.setStatus(loginDTO.getPassword().equals(password));
                        if (loginDTO.isStatus()) {
                            if (clubMap.containsKey(loginDTO.getUserName().toLowerCase(Locale.ROOT))) {
                                LoggedBefore loggedBefore = new LoggedBefore();
                                loggedBefore.setLoggedBefore(true);
                                networkUtil.write(loggedBefore);
                            } else {
                                functions = new Functions();
                                for (Club c : functions.getClubs()) {
                                    if (loginDTO.getUserName().equalsIgnoreCase(c.getClub_name())) {
                                        ClubInfo clubInfo = new ClubInfo();
                                        clubInfo.setClubName(c.getClub_name().toLowerCase(Locale.ROOT));
                                        clubInfo.setPassword(c.getClub_name().toLowerCase(Locale.ROOT));
                                        clubInfo.setClubPlayers(c.getPlayers());
                                        clubInfo.setNetworkUtil(networkUtil);
                                        System.out.println(clubInfo);
                                        clubMap.put(c.getClub_name().toLowerCase(Locale.ROOT), clubInfo);
                                    }
                                }
                                networkUtil.write(loginDTO);
                            }
                        }
                    }

                    if(o instanceof SellPlayer){
                               SellPlayer sellPlayer = (SellPlayer) o;
                               MarketPlayer marketPlayer=new MarketPlayer();
                               marketPlayer.setMarketPlayer(sellPlayer.getSellPlayers());
                               marketPlayer.setPrice(sellPlayer.getPrice());

                        Iterator<String> iterator = clubMap.keySet().iterator();
                        while (iterator.hasNext()) {
                            String name = iterator.next();
                            ClubInfo clubInfo = clubMap.get(name);
                            if (!name.equalsIgnoreCase(sellPlayer.getSellPlayers().getClub())) {
                                //clubInfo.getNetworkUtil().write(marketPlayer);
                                clubInfo.getBuyingList().add(sellPlayer.getSellPlayers());
                            }
                            else{
                                clubInfo.getSellingList().add(sellPlayer.getSellPlayers());
                                clubInfo.getClubPlayers().remove(sellPlayer.getSellPlayers());
                                //clubInfo.getNetworkUtil().write(sellPlayer);
                            }
                        }
                    }
                    if(o instanceof BuyPlayer){
                          BuyPlayer buyPlayer=(BuyPlayer) o;

                        Iterator<String> iterator = clubMap.keySet().iterator();
                        while (iterator.hasNext()) {
                            String name = iterator.next();
                            ClubInfo clubInfo = clubMap.get(name);
                            if (name.equalsIgnoreCase(buyPlayer.getBuyPlayer().getClub())) {
                                clubInfo.getSellingList().remove(buyPlayer.getBuyPlayer());
                            }
                            if(name.equalsIgnoreCase(buyPlayer.getBuyingClub())){
                                clubInfo.getBuyingList().add(buyPlayer.getBuyPlayer());
                                clubInfo.getNetworkUtil().write(buyPlayer);
                            }
                        }
                    }
                    if(o instanceof MarketList){

                    }
                    }
                }
            }
         catch (Exception e) {
            System.out.println(e);
            System.out.println("read thread server");
        } finally {
            try {
                networkUtil.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



