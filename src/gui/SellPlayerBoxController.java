package gui;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SellPlayerBoxController {

    public boolean isSubmit=false;
    public double price;

    @FXML
    private Button resetButton;

    @FXML
    private DialogPane dialogPane;

    @FXML
    private JFXTextField setPrice;

    @FXML
    private Label PlayerName;

    @FXML
    private Button submitButton;

    @FXML
    void submitAction(ActionEvent event) {
        try {
            isSubmit = true;
            price = Double.parseDouble(setPrice.getText());
            Stage stage = (Stage) dialogPane.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wrong Input");
            alert.setHeaderText("WRONG INPUT");
            alert.setContentText("Please enter your player's price.");
            alert.showAndWait();
        }

    }

    public void setPlayerName(String name){
        PlayerName.setText(name);
    }

    public boolean isSubmit() {
        return isSubmit;
    }

    public double getPrice(){
        return price;
    }

    public void resetAction(ActionEvent actionEvent) {
        setPrice.setText(null);
    }
}