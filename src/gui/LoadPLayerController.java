package gui;

import database.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Locale;

public class LoadPLayerController {

    @FXML
    private Label ClubName;

    @FXML
    private Label PlayerName;

    @FXML
    private ImageView ClubImage;

    @FXML
    private ImageView PlayerImage;

    @FXML
    private Label Position;

    @FXML
    private Label Country;

    @FXML
    private Label Age;

    @FXML
    private Label Height;

    @FXML
    private Label Salary;

    @FXML
    private Label Number;

    public void setPlayer(Player player){
        ClubName.setText(player.getClub().toUpperCase(Locale.ROOT));
        PlayerName.setText(player.getName());

        String s="images/"+player.getClub().toLowerCase()+".png";
        Image img = new Image(Main.class.getResourceAsStream(s));
        ClubImage.setImage(img);

        Position.setText(player.getPosition().toUpperCase(Locale.ROOT));
        Country.setText("Country : "+player.getCountry());
        Age.setText("Age : "+String.valueOf(player.getAge()));
        Height.setText("Height : "+String.valueOf(player.getHeight()));
        Salary.setText("Weekly Salary : "+String.valueOf(player.getWeekly_Salary()));
        Number.setText("Number : "+String.valueOf(player.getNumber()));
    }

}
