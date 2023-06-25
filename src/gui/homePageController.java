package gui;

import database.Club;
import database.Functions;
import database.Player;
import dto.SellPlayer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class homePageController implements Initializable {

    private Main main;
    private Functions functions;
    private Club club;
    ObservableList<Player> player;
    ObservableList<PlayerCount> playerCounts;
    private List<Player> players;

    @FXML
    public ImageView refreshImage;
    @FXML
    public Button sell;
    @FXML
    public Button details;

    @FXML
    private TableView tableView;

    @FXML
    private ImageView clubImage;
    @FXML
    private ImageView searchImage;

    @FXML
    private Button logOut;

    @FXML
    private Button homeButton;

    @FXML
    private Button marketButton;

    @FXML
    private Button searchButton;

    @FXML
    private TextField textSearch;
    @FXML
    private TextField toSearch;

    @FXML
    private Label clubName;

    @FXML
    private ComboBox comboBox;

    @FXML
    private Label select;

    private boolean init = true;
    private boolean isMarket = false;
    private Image img;

    private void initializeColumns() {
        TableColumn<Player, String> NameCol = new TableColumn<>("Name");
        NameCol.setMinWidth(80);
        NameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));

        TableColumn<Player, String> CountryCol = new TableColumn<>("Country");
        CountryCol.setMinWidth(60);
        CountryCol.setCellValueFactory(new PropertyValueFactory<>("Country"));

        TableColumn<Player, Integer> AgeCol = new TableColumn<>("Age");
        AgeCol.setMinWidth(40);
        AgeCol.setCellValueFactory(new PropertyValueFactory<>("Age"));

        TableColumn<Player, Double> HeightCol = new TableColumn<>("Height");
        HeightCol.setMinWidth(40);
        HeightCol.setCellValueFactory(new PropertyValueFactory<>("Height"));

        TableColumn<Player, String> PositionCol = new TableColumn<>("Position");
        PositionCol.setMinWidth(60);
        PositionCol.setCellValueFactory(new PropertyValueFactory<>("Position"));

        TableColumn<Player, Integer> NumberCol = new TableColumn<>("Number");
        NumberCol.setMinWidth(40);
        NumberCol.setCellValueFactory(new PropertyValueFactory<>("Number"));

        TableColumn<Player, Double> Weekly_SalaryCol = new TableColumn<>("Weekly Salary");
        Weekly_SalaryCol.setMinWidth(50);
        Weekly_SalaryCol.setCellValueFactory(new PropertyValueFactory<>("Weekly_Salary"));

        tableView.getColumns().addAll(NameCol, CountryCol, AgeCol, HeightCol,PositionCol,NumberCol,Weekly_SalaryCol);
    }

    public void load() {
        if (init) {
            tableView.getColumns().clear();
            initializeColumns();
            init = false;
        }

        player = FXCollections.observableArrayList(players);

        tableView.setEditable(true);
        tableView.setItems(player);
    }

    public void NotFound(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Not Found");
        alert.setHeaderText("Not Found");
        alert.setContentText("No such player found under this criteria.");
        alert.showAndWait();
    }

    public void PlayerCountColumns(){
        TableColumn<PlayerCount, String> CountryCol = new TableColumn<>("Country");
        CountryCol.setMinWidth(150);
        CountryCol.setCellValueFactory(new PropertyValueFactory<>("Country"));

        TableColumn<PlayerCount, Integer> PlayerCountCol = new TableColumn<>("Player Count");
        PlayerCountCol.setMinWidth(150);
        PlayerCountCol.setCellValueFactory(new PropertyValueFactory<>("PlayerCount"));

        tableView.getColumns().addAll(CountryCol, PlayerCountCol);
    }

    @FXML
    void comboBoxAction(ActionEvent event) {
        searchButton.setVisible(true);
        textSearch.setVisible(true);
        searchImage.setVisible(true);

        String s=comboBox.getValue().toString();
        textSearch.setText("");
        select.setText(s);
        if (s.equalsIgnoreCase("by maximum salary")){
            searchButton.setVisible(false);
            searchImage.setVisible(false);
            textSearch.setVisible(false);

            players=club.player_max_salary();
            if(players.isEmpty()){
                NotFound();
            }
            load();
        }
        else if(s.equalsIgnoreCase("by maximum age")){
            searchButton.setVisible(false);
            searchImage.setVisible(false);
            textSearch.setVisible(false);
            players=club.player_max_age();
            if(players.isEmpty()){
                NotFound();
            }
            load();
        }
        else if(s.equalsIgnoreCase("by maximum height")){
            searchButton.setVisible(false);
            searchImage.setVisible(false);
            textSearch.setVisible(false);
            players=club.player_max_height();
            if(players.isEmpty()){
                NotFound();
            }
            load();
        }
        else if(s.equalsIgnoreCase("by salary range")){
               textSearch.setPromptText("FROM");
               toSearch.setVisible(true);
        }
        else if(s.equalsIgnoreCase("country wise player count")){
            searchButton.setVisible(false);
            searchImage.setVisible(false);
            textSearch.setVisible(false);

            tableView.getColumns().clear();
            init=true;
            PlayerCountColumns();

            List<PlayerCount> p=new ArrayList();

            HashMap<String, Integer> map=new HashMap<String, Integer>();
            map= functions.country_wise_playerCount();
            map.forEach((key,value)->p.add(new PlayerCount(key,value)));

            playerCounts = FXCollections.observableArrayList(p);
            tableView.setEditable(true);
            tableView.setItems(playerCounts);

        }
        else if(s.equalsIgnoreCase("total yearly salary")){
           /* Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("CLUB INFO");
            alert.setHeaderText("TOTAL YEARLY SALARY ");
            try {
                alert.setContentText(String.valueOf(club.totalSalary()));
            } catch (Exception e) {
                System.out.println(e);
            }
            alert.showAndWait();*/
            double salary = club.totalSalary();
            searchButton.setVisible(false);
            searchImage.setVisible(false);
            textSearch.setVisible(false);
            select.setText(s+" : "+String.format("%.02f",salary));
        }

    }

    public void init(String msg) throws Exception {
        clubName.setText(msg.toUpperCase(Locale.ROOT));
        club=new Club();
        functions=new Functions();
        players=new ArrayList();
        club=functions.LoadClub(msg);
        players=club.getPlayers();

        select.setText("");
        toSearch.setVisible(false);
        searchButton.setVisible(true);
        textSearch.setVisible(true);
        searchImage.setVisible(true);


        img=new Image(Main.class.getResourceAsStream("images/arsenal.png"));
        if(msg.equalsIgnoreCase("arsenal")) {
            img = new Image(Main.class.getResourceAsStream("images/arsenal.png"));
        }
        else if(msg.equalsIgnoreCase("liverpool")) {
            img = new Image(Main.class.getResourceAsStream("images/liverpool.png"));
        }
        else if(msg.equalsIgnoreCase("chelsea")) {
            img = new Image(Main.class.getResourceAsStream("images/chelsea.png"));
        }
        else if(msg.equalsIgnoreCase("manchester united")) {
            img = new Image(Main.class.getResourceAsStream("images/manchester united.png"));
        }
        else if(msg.equalsIgnoreCase("manchester city")) {
            img = new Image(Main.class.getResourceAsStream("images/manchester city.png"));
        }
        clubImage.setImage(img);
    }


    void setMain(Main main) {
        this.main = main;
    }


    @FXML
    void homeButtonPressed(ActionEvent event) throws Exception {
             init(club.getClub_name());
             init=true;
             load();
    }

    @FXML
    void logOutAction(ActionEvent event) {
        try {
            main.showLoginPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void searchAction(ActionEvent event) {
         String s=select.getText();


        if(s.equalsIgnoreCase("By player name")){
             String[] tokens = textSearch.getText().split(",");
             players=new ArrayList();
             Player p=new Player();
            for (String t:tokens) {
                System.out.println(t);
                p=club.search_byPlayerName(t);
                players.add(p);
            }
            if(players.isEmpty()){
                NotFound();
            }
            load();
        }
        else if(s.equalsIgnoreCase("by country")){
            players=club.search_byCountry(textSearch.getText());
            if(players.isEmpty()){
                NotFound();
            }
            load();
        }
        else if(s.equalsIgnoreCase("by position")){
            players=club.search_byPosition(textSearch.getText());
            if(players.isEmpty()){
                NotFound();
            }
            load();
        }
        else if(s.equalsIgnoreCase("by salary range")){
            double from = Double.parseDouble(textSearch.getText());
            double to = Double.parseDouble(toSearch.getText());
            players=club.search_bySalary(from,to);
            if(players.isEmpty()){
                NotFound();
            }
            load();
            toSearch.setVisible(false);
            textSearch.setPromptText("TYPE HERE TO SEARCH");
            textSearch.setText("");
        }

    }

    @FXML
    void showMarket(ActionEvent event) {

    }

    @FXML
    void textSearchAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> optionList = FXCollections.observableArrayList("BY PLAYER NAME","BY COUNTRY","BY POSITION","BY SALARY RANGE","COUNTRY WISE PLAYER COUNT","BY MAXIMUM SALARY","BY MAXIMUM AGE","BY MAXIMUM HEIGHT","TOTAL YEARLY SALARY");
        comboBox.setItems(optionList);
    }

    public void buyAction(){
        if(tableView.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Not Selected");
            alert.setHeaderText("NOT SELECTED");
            alert.setContentText("You haven't selected any player yet.");
            alert.showAndWait();
        }
        else{

        }
    }

    @FXML
    public void sellAction(ActionEvent actionEvent) throws IOException {
        if(tableView.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Not Selected");
            alert.setHeaderText("NOT SELECTED");
            alert.setContentText("You haven't selected any player yet.");
            alert.showAndWait();
        }
        else{
            ObservableList<Player> p = tableView.getSelectionModel().getSelectedItems();
            SellPlayer sellPlayer=new SellPlayer();
            sellPlayer.setSellPlayers(p.get(0));

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("SellPlayerBox.fxml"));
            DialogPane dialogPane = loader.load();

            // Loading the controller
            SellPlayerBoxController controller = loader.getController();
            controller.setPlayerName(p.get(0).getName());

            Dialog<Object> dialog = new Dialog<>();
            dialog.setDialogPane(dialogPane);
            dialog.setTitle("Sell Player");
            dialog.showAndWait();

            if(controller.isSubmit()){
                sellPlayer.setPrice(controller.getPrice());
                System.out.println(sellPlayer.getPrice());
                tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItems());
            }
            main.getNetworkUtil().write(sellPlayer);

        }
    }

    @FXML
    public void detailsAction(ActionEvent actionEvent) throws Exception{
        if(tableView.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Not Selected");
            alert.setHeaderText("NOT SELECTED");
            alert.setContentText("You haven't selected any player yet.");
            alert.showAndWait();
        }
        else {
            ObservableList<Player> p = tableView.getSelectionModel().getSelectedItems();
            System.out.println(p.get(0).getName());

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("LoadPlayer.fxml"));
            DialogPane dialogPane = loader.load();

            // Loading the controller
            LoadPLayerController controller = loader.getController();
            controller.setPlayer(p.get(0));

            Dialog<Object> dialog = new Dialog<>();
            dialog.setDialogPane(dialogPane);
            dialog.setTitle("Player Info");
            dialog.showAndWait();
        }
    }

    @FXML
    public void refreshAction(MouseEvent mouseEvent) {
    }
}
