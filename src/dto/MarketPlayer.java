package dto;

import database.Player;

import java.io.Serializable;

public class MarketPlayer implements Serializable {
    private Player MarketPlayer;
    private double Price;

    public void setMarketPlayer(Player marketPlayer) {
        MarketPlayer = marketPlayer;
    }

    public Player getMarketPlayer() {
        return MarketPlayer;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public double getPrice() {
        return Price;
    }
}
