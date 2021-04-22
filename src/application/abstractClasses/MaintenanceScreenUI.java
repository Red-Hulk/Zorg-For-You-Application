/*LOGIN SUBSYSTEM*/

package application.abstractClasses;

import application.BackEndClasses.DatabaseMethods;
import application.interfaces.BaseScreen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import application.BackEndClasses.Database;
import application.BackEndClasses.Registratie;


public abstract class MaintenanceScreenUI implements BaseScreen {

    //Containers & instances
    protected BorderPane root;
    protected GridPane containerButton;
    protected TableView table;
    protected Button verwijderen, opslaan;
    protected DatabaseMethods data;


    //Building Blocks
    protected TableColumn firstCol, secondCol, thirdCol, fourthCol, fifthCol, action;


    @Override
    public void setUpMainScreen() {

        //Containers & instances
        root = new BorderPane();
        table = new TableView();
        verwijderen = new Button("Verwijderen");
        opslaan = new Button("Opslaan");
        containerButton = new GridPane();
        data = new DatabaseMethods();

        firstCol = new TableColumn("Gebruikersnaam");
        secondCol = new TableColumn("Wachtwoord");
        thirdCol = new TableColumn("Naam");
        fourthCol = new TableColumn("Email");
        fifthCol = new TableColumn("Diploma");

        action = new TableColumn("Action");

        /*--------------------------------------------*/
        //Placement blocks in containers
        root.setCenter(table);
        root.setBottom(containerButton);

        containerButton.add(verwijderen,0,0);
        containerButton.add(opslaan,1,0);

        //Place all elements in TableView
        table.getColumns().addAll(firstCol, secondCol, thirdCol, fourthCol, fifthCol,action);

        //Values to columns in tableview

        firstCol.setCellValueFactory(
                new PropertyValueFactory<Registratie,String>("gebruikersnaam")
        );
        secondCol.setCellValueFactory(
                new PropertyValueFactory<Registratie,String>("wachtwoord")
        );
        thirdCol.setCellValueFactory(
                new PropertyValueFactory<Registratie,String>("naam")
        );
        fourthCol.setCellValueFactory(
                new PropertyValueFactory<Registratie,String>("email")
        );
        fifthCol.setCellValueFactory(
                new PropertyValueFactory<Registratie,String>("diploma")
        );

        action.setCellValueFactory(
                new PropertyValueFactory<Registratie,String>("checkBox")
        );

        /*-------------------------------------------------------*/
        //Add stylesheet to root
        root.getStylesheets().add("/application/stylesheets/style.css");

        //Spacing in the root between elements
        root.setPadding(new Insets(10,10,10,10));

        //Spacing in Gridpane between elements
        containerButton.setPadding(new Insets(10,10,10,10));

        //Make all the columns equal size
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //Center the button
        containerButton.setAlignment(Pos.CENTER);

        //Spacing between the buttons
        containerButton.setHgap(10);


    }
}
