package application.GUIClasses;

import application.BackEndClasses.LeftPanelCategories;
import application.DataBase;
import application.abstractClasses.LeftPanelCategoriesGUI;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class GUIFAQDetail extends LeftPanelCategoriesGUI {

    private Pane root2;
    private String vraag, antwoord;
    private TextArea textArea;
    private Connection con;
    private Label questionLabel;
    private Button backButton;

    public GUIFAQDetail(Stage primaryStage, int vraagID, String userType) throws Exception {
        DataBase db = new DataBase();
        con = db.getCon();
        setUpLeftPanel();

        root2 = new Pane();
        root.setCenter(root2);
        Scene scene = new Scene(root, 600, 400);

        Text text1 = new Text("Test");
        text1.setLayoutX(300);

        root2.getChildren().addAll(text1);
        root2.getStylesheets().add("application/stylesheets/styleFAQ.css");


        primaryStage.setTitle("4-you");
        primaryStage.setScene(scene);
        primaryStage.show();

        loadData(userType, primaryStage, vraagID);

        new LeftPanelCategories(btnYouth, btnHome, btnProfessional, btnRefugee, btnSenior, primaryStage);
    }

    public void loadData(String userType, Stage primaryStage, int vraagID) {
        vraag = "";
        antwoord = "";
        try{
            //query opstellen
            String query = "SELECT vraag, id, antwoord FROM hulp_vragen WHERE id = " + vraagID + "";

            //conn wordt aangemaakt bij de database connectie
            Statement stZoek = con.createStatement();

            //query uitvoeren en java result set aanmaken
            ResultSet rsZoek = stZoek.executeQuery(query);

            //door resultset heen wandelen zolang er een volgend resultaat is
            while (rsZoek.next()) {
                //data ophalen uit database
                vraag = rsZoek.getString("vraag");
                vraagID = rsZoek.getInt("id");
                antwoord = rsZoek.getString("antwoord");
                questionLabel = new Label("" + vraag + "");
                textArea = new TextArea("" + antwoord + "");
                backButton = new Button("Terug naar de FAQ");

                questionLabel.setId("userLabel");
                textArea.setId("textAreaFAQ");
                textArea.setWrapText(true);
                textArea.setEditable(false);
                backButton.setId("backButton");
                backButton(primaryStage, userType);

                root2.getChildren().addAll(questionLabel, textArea, backButton);

                questionLabel.setLayoutX(0);
                questionLabel.setLayoutY(50);
                textArea.setLayoutX(0);
                textArea.setLayoutY(125);
                backButton.setLayoutY(350);
                backButton.setLayoutX(107.5);
            }
        } catch(Exception e){
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

    }

    public void backButton(Stage primaryStage, String userType){
        //terug naar het overzicht van de FAQ
        backButton.setOnAction(e -> {
            try {
                new GUIFAQ(primaryStage, userType);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }

}
