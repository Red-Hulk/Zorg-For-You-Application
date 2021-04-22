package application.BackEndClasses;

import application.GUIClasses.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Home {


    public Home(Stage primaryStage, Button btnJongeren, Button btnOuderen, Button btnProfessionals, Button btnVluchtelingen, Button btnFeedback, Button btnAdmin) {
        actionBtnJongeren(primaryStage, btnJongeren);
        actionBtnOuderen(primaryStage, btnOuderen);
        actionBtnProfessionals(primaryStage, btnProfessionals);
        actionBtnVluchtelingen(primaryStage, btnVluchtelingen);
        actionBtnAdmin(primaryStage, btnAdmin);
        actionBtnFeedback(primaryStage, btnFeedback);
    }

    private void actionBtnFeedback(Stage primaryStage, Button btnFeedback) {
        btnFeedback.setOnAction(e-> {
            System.out.println("feedback");
            new GUIChatProf("PlaceHoldernaam");
        });
    }

    private void actionBtnAdmin(Stage primaryStage, Button btnAdmin) {
        btnAdmin.setOnAction(e-> {
            new GUILoginAdmin(primaryStage);
        });
    }

    private void actionBtnJongeren(Stage primaryStage, Button btnJongeren) {
        btnJongeren.setOnAction(e-> {
            new GUIJongeren(primaryStage);
        });
    }

    private void actionBtnOuderen(Stage primaryStage, Button btnOuderen) {
        btnOuderen.setOnAction(e-> {
            new GUIOuderen(primaryStage);
        });
    }

    private void actionBtnProfessionals(Stage primaryStage, Button btnProfessionals) {
        btnProfessionals.setOnAction(e-> {
            new GUILogin(primaryStage);
        });
    }

    private void actionBtnVluchtelingen(Stage primaryStage, Button btnVluchtelingen) {
        btnVluchtelingen.setOnAction(e-> {
            new GUIVluchtelingen(primaryStage);
        });
    }
}
