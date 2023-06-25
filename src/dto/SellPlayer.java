package dto;

import database.Player;

import java.io.Serializable;

public class SellPlayer implements Serializable {
       private Player sellPlayers;
       private double price;

    public void setSellPlayers(Player sellPlayers) {
        this.sellPlayers = sellPlayers;
    }

    public Player getSellPlayers() {
        return sellPlayers;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
