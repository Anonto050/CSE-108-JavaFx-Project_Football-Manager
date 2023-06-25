package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import util.NetworkUtil;

import java.io.IOException;

public class Main extends Application {

    private Stage stage;
    private NetworkUtil networkUtil;

    public Stage getStage() {
        return stage;
    }

    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        connectToServer();
        showLoginPage();
    }

    private void connectToServer() throws IOException {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        networkUtil = new NetworkUtil(serverAddress, serverPort);
        new ReadThread(this);
    }

    public void showLoginPage() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login.fxml"));
        Parent root = loader.load();

        // Loading the controller
        LoginController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 831, 496));
        stage.show();
    }

    public void showHomePage(String userName) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("homePage.fxml"));
        Parent root = loader.load();

        // Loading the controller
        homePageController controller = loader.getController();
        controller.init(userName);
        controller.load();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("FOOTBALL MANAGER 2021");
        stage.setScene(new Scene(root, 831, 505));
        stage.show();
    }

    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("The username and password you provided is not correct.");
        alert.showAndWait();
    }

    public void loginAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Already logged in");
        alert.setHeaderText("ALREADY LOGGED IN");
        alert.setContentText("You are already logged in through this ID.\nKindly login through another ID");
        alert.showAndWait();
    }

    public static void main(String[] args) {
        // This will launch the JavaFX application
        launch(args);
    }
}
