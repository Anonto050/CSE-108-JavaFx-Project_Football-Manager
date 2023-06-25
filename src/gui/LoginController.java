package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dto.LoginDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class LoginController {

    private Main main;

    @FXML
    private JFXTextField userText;

    @FXML
    private JFXPasswordField passwordText;

    @FXML
    private JFXButton loginButton;

    @FXML
    private JFXButton resetButton;


    @FXML
    void loginAction(ActionEvent event) {
        String userName = userText.getText();
        String password = passwordText.getText();
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUserName(userName);
        loginDTO.setPassword(password);
        try {
            main.getNetworkUtil().write(loginDTO);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("login controller");
        }
    }

    @FXML
    void resetAction(ActionEvent event) {
        userText.setText(null);
        passwordText.setText(null);
    }

    void setMain(Main main) {
        this.main = main;
    }
}
