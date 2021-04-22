/*LOGIN SUBSYSTEM*/

package application.abstractClasses;

import application.interfaces.BaseScreen;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;


public abstract class LoginScreenUI extends LeftPanelCategoriesGUI implements BaseScreen {

    //Containers
    protected BorderPane containerCenter, labelBox, linkContainer;
    protected GridPane gridCenter;
    protected FlowPane bottomPart;

    //Building blocks
    protected Label gName, pPassword, login;
    protected Button btnInloggen, btnRegistreren;
    protected TextField txtGname;
    protected PasswordField txtGpassword;
    protected Hyperlink link;



    @Override
    public void setUpMainScreen() {

        //method to make left panel and top panel with logo
        setUpLeftPanel();

        /*-----------------------------------------*/

        //making new instances
        //Containers
        containerCenter = new BorderPane();
        labelBox = new BorderPane();
        gridCenter = new GridPane();
        linkContainer = new BorderPane();
        bottomPart = new FlowPane();

        //Building Blocks
        gName = new Label("Gebruikersnaam:");
        pPassword = new Label("Wachtwoord:");
        login = new Label("Inloggen");

        btnInloggen = new Button("Inloggen");
        btnRegistreren = new Button("Registreren");

        txtGname = new TextField();
        txtGpassword = new PasswordField();

        //Link when password is forgotten
        link = new Hyperlink();
        link.setText("Wachtwoord vergeten?");



        /*-----------------------------------------------*/

        //Placement elements in containers
        //Root container placement
        root.setCenter(containerCenter);



        //Placement gridpane in borderpane
        containerCenter.setCenter(gridCenter);
        containerCenter.setTop(labelBox);
        labelBox.setCenter(login);
        linkContainer.setCenter(link);


        //Placement center grid pane
        gridCenter.add(gName,0,0);
        gridCenter.add(txtGname,1,0);
        gridCenter.add(pPassword,0,1);
        gridCenter.add(txtGpassword,1,1);
        gridCenter.add(linkContainer,1,2);
        gridCenter.add(btnInloggen,1,3);


        //Placement flowpane bottom
        containerCenter.setBottom(bottomPart);
        bottomPart.getChildren().add(btnRegistreren);

        /*------------------------------------------*/

        //Styling
        //setting ID's
        gridCenter.getStyleClass().add("grid");
        bottomPart.setId("bottomPart");
        login.setId("loginTekst");


        //Styling Gridpane columns
        ColumnConstraints center1 = new ColumnConstraints();
        center1.setPercentWidth(25);
        gridCenter.getColumnConstraints().addAll(center1);



        //Styling Bottom part Flow Pane
        bottomPart.setAlignment(Pos.CENTER_RIGHT);

    }
}
