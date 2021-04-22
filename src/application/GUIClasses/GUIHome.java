package application.GUIClasses;

import application.BackEndClasses.Home;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GUIHome {

    public GUIHome(Stage primaryStage) {
        Button btnJongeren = new Button("Jongeren -23");
        btnJongeren.setLayoutX(87.5);
        btnJongeren.setLayoutY(112.5);

        Button btnOuderen = new Button("Ouderen 65+");
        btnOuderen.setLayoutX(312.5);
        btnOuderen.setLayoutY(112.5);

        Button btnProfessionals = new Button("Professionals");
        btnProfessionals.setLayoutX(87.5);
        btnProfessionals.setLayoutY(212.5);

        Button btnVluchtelingen = new Button("Vluchtelingen");
        btnVluchtelingen.setLayoutX(312.5);
        btnVluchtelingen.setLayoutY(212.5);

        Button btnAdmin = new Button("Admin");
        btnAdmin.setId("btnAdmin");
        btnAdmin.setLayoutX(10);
        btnAdmin.setLayoutY(350);

        Button btnFeedback = new Button("Feedback");
        btnFeedback.setId("btnFeedback");
        btnFeedback.setLayoutX(500);
        btnFeedback.setLayoutY(350);

        Pane root = new Pane();
        root.setId("root");

        root.getStylesheets().add("application/stylesheets/home.css");
        root.getChildren().addAll(btnJongeren, btnOuderen, btnProfessionals, btnVluchtelingen, btnAdmin, btnFeedback);

        primaryStage.setTitle("4-you");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

        new Home(primaryStage, btnJongeren, btnOuderen, btnProfessionals, btnVluchtelingen, btnFeedback, btnAdmin);
    }
}
