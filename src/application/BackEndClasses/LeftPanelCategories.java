package application.BackEndClasses;

import application.GUIClasses.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LeftPanelCategories {
    public LeftPanelCategories(Button btnYouth, Button btnHome, Button btnProfessional, Button btnRefugee, Button btnSenior, Stage primaryStage) {
        actionBtnYouth(primaryStage, btnYouth);
        actionBtnHome(primaryStage, btnHome);
        actionBtnProfessional(primaryStage, btnProfessional);
        actionBtnRegfugee(primaryStage, btnRefugee);
        actionBtnSenior(primaryStage, btnSenior);
    }

    private void actionBtnSenior(Stage primaryStage, Button btnSenior) {
        btnSenior.setOnAction(e-> {
            new GUIOuderen(primaryStage);
        });
    }

    private void actionBtnRegfugee(Stage primaryStage, Button btnRefugee) {
        btnRefugee.setOnAction(e-> {
            new GUIVluchtelingen(primaryStage);
        });
    }

    private void actionBtnProfessional(Stage primaryStage, Button btnProfessional) {
        btnProfessional.setOnAction(e-> {
            new GUILogin(primaryStage);
        });
    }

    private void actionBtnHome(Stage primaryStage, Button btnHome) {
        btnHome.setOnAction(e-> {
            new GUIHome(primaryStage);
        });
    }

    private void actionBtnYouth(Stage primaryStage, Button btnYouth) {
        btnYouth.setOnAction(e-> {
            new GUIJongeren(primaryStage);
        });
    }
}
