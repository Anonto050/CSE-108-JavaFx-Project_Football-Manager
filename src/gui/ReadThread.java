package gui;

import dto.LoggedBefore;
import dto.LoginDTO;
import dto.MarketList;
import dto.MarketPlayer;
import javafx.application.Platform;

import java.io.IOException;

public class ReadThread implements Runnable {
    private final Thread thr;
    private final Main main;

    public ReadThread(Main main) {
        this.main = main;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = main.getNetworkUtil().read();
                System.out.println(o);
                if (o != null) {
                    if (o instanceof LoginDTO) {
                        LoginDTO loginDTO = (LoginDTO) o;
                        System.out.println(loginDTO.getUserName());
                        System.out.println(loginDTO.isStatus());
                        // the following is necessary to update JavaFX UI components from user created threads
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if (loginDTO.isStatus()) {
                                    try {
                                        main.showHomePage(loginDTO.getUserName());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    main.showAlert();
                                }

                            }
                        });
                    }
                    if (o instanceof LoggedBefore) {
                        LoggedBefore loggedBefore = (LoggedBefore) o;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if (loggedBefore.isLoggedBefore()) {
                                    main.loginAlert();
                                }
                            }
                        });
                    }
                    if (o instanceof MarketPlayer) {
                        MarketPlayer marketPlayer = (MarketPlayer) o;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });
                    }
                    if (o instanceof MarketList) {
                        MarketList marketList = (MarketList) o;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("read thread");
        } finally {
            try {
                main.getNetworkUtil().closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



