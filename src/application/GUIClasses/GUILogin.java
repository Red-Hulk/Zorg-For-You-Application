/*LOGIN SUBSYSTEM*/

package application.GUIClasses;

import application.BackEndClasses.LeftPanelCategories;
import application.abstractClasses.LoginScreenUI;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;
import sample.Api.Api;
import sample.Api.EmailApi;
import application.BackEndClasses.Login;
import sample.backendklassen.DataValidationForm;
import javax.swing.*;
import java.io.IOException;
import java.util.Optional;

public class GUILogin extends LoginScreenUI {

    //Constructor
    public GUILogin(Stage primaryStage){

        /*Method to set up the scene*/
        setUpMainScreen();

        /*---------------------------------------------------------------------*/

        //Event handling
        //Goes to registration menu
        btnRegistreren.setOnAction(event->{
            GUIRegistratie registratie = new GUIRegistratie(primaryStage);
        });

        //Check if text fields are filled in and shows if login has succeed
        btnInloggen.setOnAction(event->{
            String strG = txtGname.getText();
            String strW = txtGpassword.getText();

            if(strG.equals("") || strW.equals("")){
                JOptionPane.showMessageDialog(null, "Niet alle invoervelden zijn ingevuld!");
            }
            else{
                Login voorbeeld = new Login(strG,strW);
                voorbeeld.checkLoginGegevens(voorbeeld);

                txtGname.clear();
                txtGpassword.clear();

                primaryStage.close();
            }
        });

        //The link "forgot password" is pressed
        link.setOnAction(click->{
            //Ask input email user
            TextInputDialog td = new TextInputDialog();
            td.setHeaderText("Vul e-mailadres in!");
            Optional<String> result = td.showAndWait();
            Button okButton = (Button) td.getDialogPane().lookupButton(ButtonType.OK);
            TextField inputField = td.getEditor();



            String emailVar = inputField.getText();

            boolean checkEmailLogin = DataValidationForm.checkEmailLogin(emailVar);

            if(checkEmailLogin == true) {

                try {
                    EmailApi send = new EmailApi(emailVar);
                    Api.getInstance().sendMail("zorg4you_02@bp3.adainforma.tk", "ld8qM?17", send.getSettings());
                    System.out.println("Gelukt");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
            }else{

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Moet juiste formaat bevatten! (naam@adres.nl)");
                alert.show();
            }



        });


        /*---------------------------------------------------*/
        //Set On action Buttons to other UI Screens

        new LeftPanelCategories(btnYouth, btnHome, btnProfessional, btnRefugee, btnSenior, primaryStage);



        /*----------------------------------------------------*/

        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(400);
        //primaryStage.setResizable(false);

        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}
