package application.GUIClasses;


import application.BackEndClasses.LeftPanelCategories;
import application.DataBase;
import application.abstractClasses.LeftPanelCategoriesGUI;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.sql.*;

//Author 1: Rick Vissers (createList() functie)
//Author 2: Thorsten Timmermans (search() functie)

public class GUIFAQ extends LeftPanelCategoriesGUI {

    private Pane root2;
    private String zoekterm, antwoord;
    private final TextField txtZoek;
    private Connection con;
    private Button queButton;
    private double y;
    private int vraagID;

    public GUIFAQ(Stage primaryStage, String userType) throws Exception {
        DataBase db = new DataBase();
        con = db.getCon();
        setUpLeftPanel();

        root2 = new Pane();
        root.setCenter(root2);
        Scene scene = new Scene(root, 600, 400);

        txtZoek = new TextField();
        txtZoek.setId("txtzoek");
        txtZoek.setPromptText("Voer zoekterm(en) in");
        txtZoek.setLayoutX(0);
        txtZoek.setLayoutY(10);

        scene.setOnKeyPressed(s-> {
            KeyCode keyCode = s.getCode();
            if(keyCode.equals(KeyCode.ENTER)) {
                root2.getChildren().clear();
                root2.getChildren().addAll(txtZoek);
                y = 50;
                search(userType, primaryStage);
            }
        });

        root2.getChildren().addAll(txtZoek);
        root2.getStylesheets().add("application/stylesheets/styleFAQ.css");


        primaryStage.setTitle("4-you");
        primaryStage.setScene(scene);
        primaryStage.show();

        createList(userType, primaryStage);

        new LeftPanelCategories(btnYouth, btnHome, btnProfessional, btnRefugee, btnSenior, primaryStage);
    }

    public void search(String userType, Stage primaryStage) {
        antwoord = "";
        try {
            //zoektermen ophalen uit textField
            zoekterm = txtZoek.getText();

            //controleren of is ingevuld of niet
            if (zoekterm.equals("")) {
                txtZoek.setText("");
            } else {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://adainforma.tk/bp3_zorg4you_02", "zorg4you02", "ld8qM?17");

                //query opstellen
                String query = "SELECT vraag, id FROM hulp_vragen WHERE vraag LIKE '%" + zoekterm + "%' AND categorie LIKE '%" + userType + "%' OR zoektermen LIKE '%" + zoekterm + "%' AND categorie LIKE '%" + userType + "%'";

                //conn wordt aangemaakt bij de database connectie
                Statement stZoek = con.createStatement();

                //query uitvoeren en java result set aanmaken
                ResultSet rsZoek = stZoek.executeQuery(query);

                //door resultset heen wandelen zolang er een volgend resultaat is
                while (rsZoek.next()) {
                    //data ophalen uit database
                    antwoord = rsZoek.getString("vraag");
                    vraagID = rsZoek.getInt("id");
                    queButton = new Button("" + antwoord + "");
                    queButton.setId("queButton");
                    checkAntwoord(vraagID, primaryStage, userType);
                    root2.getChildren().addAll(queButton);
                    queButton.setLayoutX(0);
                    queButton.setLayoutY(y);
                    y += 80;

                }
                //statement sluiten
                stZoek.close();
            }

        } catch (Exception e) {
            //error teruggeven
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    public void createList(String userType, Stage primaryStage){
        antwoord = "";
        y = 50;
        try{
            //query opstellen
            String query = "SELECT vraag, id FROM hulp_vragen WHERE categorie LIKE '%" + userType + "%'";

            //conn wordt aangemaakt bij de database connectie
            Statement stZoek = con.createStatement();

            //query uitvoeren en java result set aanmaken
            ResultSet rsZoek = stZoek.executeQuery(query);

            //door resultset heen wandelen zolang er een volgend resultaat is
            while (rsZoek.next()) {
                //data ophalen uit database
                antwoord = rsZoek.getString("vraag");
                vraagID = rsZoek.getInt("id");
                queButton = new Button("" + antwoord + "");
                queButton.setId("queButton");
                checkAntwoord(vraagID, primaryStage, userType);
                root2.getChildren().addAll(queButton);
                queButton.setLayoutX(0);
                queButton.setLayoutY(y);
                y += 80;

            }
        }catch (Exception e){
            //error teruggeven
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }
    public void checkAntwoord(int vraagID, Stage primaryStage, String userType){
        queButton.setOnAction(e -> {
            try {
                new GUIFAQDetail(primaryStage, vraagID, userType);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }

}
