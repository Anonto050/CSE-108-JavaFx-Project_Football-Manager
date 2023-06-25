package dto;

import database.Player;

import java.io.Serializable;

public class BuyPlayer implements Serializable {
        private Player buyPlayer;
        private double price;
        private String buyingClub;

    public void setBuyPlayer(Player buyPlayer) {
        this.buyPlayer = buyPlayer;
    }

    public Player getBuyPlayer() {
        return buyPlayer;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setBuyingClub(String buyingClub) {
        this.buyingClub = buyingClub;
    }

    public String getBuyingClub() {
        return buyingClub;
    }
}
