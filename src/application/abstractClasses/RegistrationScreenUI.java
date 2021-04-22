/*LOGIN SUBSYSTEM*/

package application.abstractClasses;

import application.interfaces.BaseScreen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;


public abstract class RegistrationScreenUI extends LeftPanelCategoriesGUI implements BaseScreen {

    //Containers
    protected BorderPane containerCenter, labelBox;
    protected GridPane gridCenter;
    protected Button btnIndienen;

    //Building blocks
    protected Label gNaam, pWoord, bpWoord, name, email, diploma, registreren, privacy;
    protected TextField txtGname, txtNaam, txtEmail;
    protected PasswordField txtGpassword, txtBpassword;
    protected CheckBox priCheck;
    protected ComboBox combobox;
    protected ObservableList<String> options = FXCollections.observableArrayList(
            "HBO",
            "Middelbare School",
            "Basisschool"
    );

    //Labels for error messages
    protected Label errGebNaam, errWachtwoord, errBevestigd, errEmail, errCombo, errNaam;


    @Override
    public void setUpMainScreen() {

        //method to make left panel and top panel with logo
        setUpLeftPanel();

        /*-----------------------------------------------------*/

        //Instances containers
        containerCenter = new BorderPane();
        gridCenter = new GridPane();
        labelBox = new BorderPane();

        //Instances Building blocks
        //Parts center grid pane
        gNaam = new Label("Gebruikersnaam:");
        pWoord = new Label("Wachtwoord:");
        bpWoord = new Label("Bevestig wachtwoord");
        name = new Label("Volledige naam:");
        email = new Label("Email:");
        diploma = new Label("Diploma:");

        //New instances Labels for error messages
        errGebNaam = new Label("");
        errWachtwoord = new Label("");
        errBevestigd = new Label("");
        errEmail = new Label("");
        errCombo = new Label("");
        errNaam = new Label("");

        txtGname = new TextField();
        txtGpassword = new PasswordField();
        txtBpassword = new PasswordField();
        txtNaam = new TextField();
        txtEmail = new TextField();
        combobox = new ComboBox(options);
        btnIndienen = new Button("Opslaan");
        registreren = new Label("Registreren");

        //Checkbox & Label to approve privacy restrictions
        privacy = new Label("Akkoord privacy regels");
        priCheck = new CheckBox();



        /*-----------------------------------------------------------*/
        //Placement of elements in containers
        //Root container placement
        root.setCenter(containerCenter);

        //Placement gridpane in borderpane
        containerCenter.setCenter(gridCenter);
        containerCenter.setTop(labelBox);
        labelBox.setCenter(registreren);

        //Placement center grid pane
        gridCenter.add(gNaam,0,0);
        gridCenter.add(txtGname,1,0);

        gridCenter.add(errGebNaam,1,1);

        gridCenter.add(pWoord,0,2);
        gridCenter.add(txtGpassword,1,2);

        gridCenter.add(errWachtwoord,1,3);

        gridCenter.add(bpWoord,0,4);
        gridCenter.add(txtBpassword,1,4);

        gridCenter.add(errBevestigd,1,5);

        gridCenter.add(name,0,6);
        gridCenter.add(txtNaam,1,6);

        gridCenter.add(errNaam,1,7);

        gridCenter.add(email,0,8);
        gridCenter.add(txtEmail,1,8);

        gridCenter.add(errEmail,1,9);

        gridCenter.add(diploma,0,10);
        gridCenter.add(combobox,1,10);

        gridCenter.add(errCombo,1,11);
        gridCenter.add(privacy,0,12);
        gridCenter.add(priCheck,1,12);

        gridCenter.add(btnIndienen,1,13);


        /*---------------------------------------------------*/
        //Styling
        //Bind ID's or classes
        registreren.setId("registrerenTekst");
        root.setId("formulier");
        gridCenter.setId("grid");
        gridCenter.getStylesheets().add("/application/stylesheets/styleform.css");
        gridCenter.setAlignment(Pos.TOP_CENTER);

        //Styling error labels
        errGebNaam.getStyleClass().add("labels");
        errWachtwoord.getStyleClass().add("labels");
        errBevestigd.getStyleClass().add("labels");
        errEmail.getStyleClass().add("labels");
        errCombo.getStyleClass().add("labels");
        errNaam.getStyleClass().add("labels");

        gridCenter.setHgap(20);
        priCheck.setId("checkId");
        privacy.setId("labelCheck");


        //Set fixed value combobox
        combobox.setValue("Niet ingevuld!");


        //Styling Gridpane center form main borderpane
        ColumnConstraints center1 = new ColumnConstraints();
        center1.setPercentWidth(30);
        gridCenter.getColumnConstraints().add(center1);



    }
}
