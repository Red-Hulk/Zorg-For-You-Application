/*LOGIN SUBSYSTEM*/

package application.GUIClasses;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import sample.backendklassen.DataValidationForm;
import javax.swing.*;
import application.abstractClasses.RegistrationScreenUI;
import application.BackEndClasses.Registratie;
import application.abstractClasses.RegistrationScreenUI;

public class GUIRegistratie extends RegistrationScreenUI {

    //Constructor
    public GUIRegistratie(Stage primaryStage){

        /*Method to set up the scene*/
        setUpMainScreen();

        /*-----------------------------------*/

        //Events handling
        //Fills data into database for registered person
        btnIndienen.setOnAction(event -> {
            //Checks if the minimal characters is 8 or more
            boolean checkLengthString = DataValidationForm.dataLength(txtGname, errGebNaam, "Moet 8 karakters bevatten!");

            //Check if name is filled in
            boolean checkNameField = DataValidationForm.checksName(txtNaam, errNaam, "Naam moet ingevuld zijn!");

            //Checks if password has the right condition one capital letter and one digit
            boolean checkPassword = DataValidationForm.checkPassword(txtGpassword, errWachtwoord, "Moet minimaal één cijfer en één hoofdletter bevatten!");

            //Check if passwords are matched
            boolean checkPasswordMatches = DataValidationForm.checkPasswordMatches(txtGpassword,txtBpassword, errBevestigd, "Wachtwoorden komt niet overeen!");

            //Checks if e-mail has the right format
            boolean checkEmail = DataValidationForm.checkEmail(txtEmail, errEmail, "Moet juiste formaat bevatten! (naam@adres.nl)");

            //Check if combobox is filled
            boolean comboBoxCheck = DataValidationForm.checkCombobox(combobox, errCombo, "Maak een keuze!");

            //Check if privacy agreement
            if (priCheck.isSelected() == true) {
                if ((checkLengthString && checkPassword && checkPasswordMatches && checkEmail && comboBoxCheck) == true) {


                    String strG = txtGname.getText();
                    String strW = txtGpassword.getText();
                    String strN = txtNaam.getText();
                    String strE = txtEmail.getText();
                    String strD = combobox.getValue().toString();


                    if (strG.equals("") || strW.equals("") || strN.equals("") || strE.equals("") || strD.equals("")) {
                        JOptionPane.showMessageDialog(null, "Niet alle invoervelden zijn ingevuld!");
                    } else {
                        Registratie persoon = new Registratie(strG, strW, strN, strE, strD);
                        persoon.newPerson(persoon);

                        txtGname.clear();
                        txtGpassword.clear();
                        txtNaam.clear();
                        txtEmail.clear();
                        combobox.setValue("Niet ingevuld!");

                        GUILogin login = new GUILogin(primaryStage);
                    }
                }
            }else{
                //Alert message for privacy agreement
                Alert melding = new Alert(Alert.AlertType.INFORMATION);
                melding.setContentText("Privacy akkoord moet geaccepteerd zijn!");
                melding.show();
            }
        });

        //Goes to login scene
        btnProfessional.setOnAction(e->{
            GUILogin login = new GUILogin(primaryStage);
        });


        /*---------------------------------------------------------------*/

        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(400);
        //primaryStage.setResizable(false);

        primaryStage.setTitle("Registreren");
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.show();

    }
}
