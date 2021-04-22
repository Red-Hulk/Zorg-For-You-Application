package application.BackEndClasses;

import application.GUIClasses.GUIChatClient;
import application.GUIClasses.GUIChatProf;
import application.GUIClasses.GUIFAQ;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Jongeren {
    public Jongeren(Button btnChat, Button btnFAQ, Button btnNoodNummers, Stage primaryStage) {
        actionBtnChat(btnChat, primaryStage);
        actionBtnFAQ(btnFAQ, primaryStage);
        actionBtnNoodNummers(btnNoodNummers, primaryStage);
    }

    private void actionBtnNoodNummers(Button btnNoodNummers, Stage primaryStage) {
    }

    private void actionBtnFAQ(Button btnFAQ, Stage primaryStage) {
        btnFAQ.setOnAction(e-> {
            try {
                new GUIFAQ(primaryStage, "jongeren");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }

    private void actionBtnChat(Button btnChat, Stage primaryStage) {
        btnChat.setOnAction(event -> {
            new GUIChatClient();
        });
    }
}
