package application.GUIClasses;

import application.BackEndClasses.LeftPanelCategories;
import application.abstractClasses.LeftPanelCategoriesGUI;
import application.BackEndClasses.LoginAdmin;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GUILoginAdmin extends LeftPanelCategoriesGUI {

    public GUILoginAdmin(Stage primaryStage) {
        setUpLeftPanel();

        Pane root2 = new Pane();
        root.setCenter(root2);

        TextField txtUser = new TextField();
        txtUser.setLayoutX(150);
        txtUser.setLayoutY(140);

        TextField txtPasswd = new TextField();
        txtPasswd.setLayoutX(150);
        txtPasswd.setLayoutY(190);

        Label lbUser = new Label("Username");
        lbUser.setLayoutX(60);
        lbUser.setLayoutY(140);

        Label lbPasswd = new Label("Password");
        lbPasswd.setLayoutX(60);
        lbPasswd.setLayoutY(190);

        Button btnLogin = new Button("Login");
        btnLogin.setId("btnLogin");
        btnLogin.setLayoutX(110);
        btnLogin.setLayoutY(290);

        root2.getStylesheets().add("application/stylesheets/styleAdmin.css");
        root2.getChildren().addAll(lbUser, txtUser, lbPasswd, txtPasswd, btnLogin);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

        new LoginAdmin(primaryStage, btnLogin, txtUser, txtPasswd);
        new LeftPanelCategories(btnYouth, btnHome, btnProfessional, btnRefugee, btnSenior, primaryStage);
    }
}
