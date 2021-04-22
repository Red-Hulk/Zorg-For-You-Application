package application.BackEndClasses;

import application.GUIClasses.GUIAdmin;
import application.Main;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class LoginAdmin {

    private String sUser, sPasswd;

    public LoginAdmin(Stage primaryStage, Button btnLogin, TextField txtUser, TextField txtPasswd) {
        actionBtnLogin(primaryStage, btnLogin, txtUser, txtPasswd);
    }

    private void actionBtnLogin(Stage primaryStage, Button btnLogin, TextField txtUser, TextField txtPasswd) {
        btnLogin.setOnAction(e-> {
            sUser = txtUser.getText();
            sPasswd = txtPasswd.getText();

            if (sUser.equals(Main.userAdmin) && sPasswd.equals(Main.passwdAdmin)) {
                try {
                    new GUIAdmin(primaryStage);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            } else {
                System.out.println("Inloggen mislukt");
            }
        });
    }
}
