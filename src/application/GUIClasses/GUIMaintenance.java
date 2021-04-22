/*LOGIN SUBSYSTEM*/

package application.GUIClasses;

import application.abstractClasses.MaintenanceScreenUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import application.abstractClasses.MaintenanceScreenUI;
import application.BackEndClasses.Registratie;
import application.BackEndClasses.DatabaseMethods;

public class GUIMaintenance extends MaintenanceScreenUI {

    public GUIMaintenance(){

        Stage primaryStage = new Stage();

        setUpMainScreen();

        ObservableList<Registratie> lijstje = FXCollections.observableArrayList(data.overview());

        //Add elements to table
        table.setItems(lijstje);


        //Deleting records from table
        verwijderen.setOnAction(event -> {
            //Container for elements to delete later
            ObservableList<Registratie> dataListRemove = FXCollections.observableArrayList();

            //For loop to check if checkbox is filled
            for (Registratie person : lijstje){
                if(person.getCheckBox().isSelected()){
                    dataListRemove.add(person);
                }
            }

            //Takes every elements to delete out of the list
            lijstje.removeAll(dataListRemove);


            //Delete persons out of list
            dataListRemove.forEach(e->{
                DatabaseMethods delete = new DatabaseMethods();
                delete.delData(e);
            });
        });

        opslaan.setOnAction(event -> {

            //Container for elements to delete later
            ObservableList<Registratie> dataListRemove = FXCollections.observableArrayList();

            //For loop to check if checkbox is filled
            for (Registratie person : lijstje){
                if(person.getCheckBox().isSelected()){
                    DatabaseMethods listData = new DatabaseMethods();
                    listData.NewData(person);
                    dataListRemove.add(person);
                }
            }

            //Takes every elements to delete out of the list
            lijstje.removeAll(dataListRemove);

            //Delete persons out of list
            dataListRemove.forEach(e->{
                DatabaseMethods delete = new DatabaseMethods();
                delete.delData(e);
            });


        });



        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(400);
        primaryStage.setResizable(false);

        primaryStage.setTitle("Registreren");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}
