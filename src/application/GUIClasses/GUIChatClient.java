package application.GUIClasses;

import application.BackEndClasses.ChatClient;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GUIChatClient extends GUIChat {

    private TextField txfUserName, txfOnderwerp;

    public GUIChatClient() {
        super();
    }

    public void actOpenChat() throws Exception {
        if (!txfUserName.getText().trim().isEmpty() && !txfOnderwerp.getText().trim().isEmpty()) {
            try {
                //Hier vull ik rootcenter met de chat Hiervoor maak ik eerst de chat controller aan zodat ik daarvan uit een new chat kan aanmaken deze returned ChatCreate voor setupchat
                root.setCenter(setUpChat(new ChatClient(txfUserName.getText(), chatStage, txfOnderwerp.getText()).newCreateChat(chatPane)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setUpMainScreen() {
        setUpLeftPanel();

        chatStage = new Stage();
        chatStage.setTitle("Chatten");
        chatStage.setScene(new Scene(root, 600, 400));

        root.setCenter(setUpAskName());
        root.getStylesheets().add(baseStylePath + "styleChat.css");

        chatStage.show();
    }

    private Parent setUpAskName() {
        errUserName = new Label("");

        txfUserName = new TextField();
        //On enter open Chat
        actEnterOpenChat(txfUserName);
        //Limit user input
        addListenTXFUserName(25);

        txfOnderwerp = new TextField();
        actEnterOpenChat(txfOnderwerp);
        addListenTXFUserName(100);

        GridPane gridName = new GridPane();
        gridName.setId("gridName");

        Label lblOnderwerp = new Label("Onderwerp:");
        Label lblUserNAme = new Label("Gebruikersnaam:");

        Button btnOpenChat = new Button("Verder gaan");
        actBTNOpenChat(btnOpenChat);

        gridName.add(lblUserNAme, 0, 0);
        gridName.add(lblOnderwerp, 0, 2);
        gridName.add(errUserName, 1, 1);
        gridName.add(txfUserName, 1, 0);
        gridName.add(txfOnderwerp, 1, 2);
        gridName.add(btnOpenChat, 1, 3);

        return gridName;
    }

    private void addListenTXFUserName(int charLimit) {
        //Input limiten tot 123 abc en max 25 char's
        //idk regex is shit maar werkt
        txfUserName.textProperty().addListener((observable, oldValue, newValue) -> {
            //if not any of [these char's] do
            if (!newValue.matches("[a-zA-Z0-9]")) {
                //do delete all not of [these char's]
                txfUserName.setText(newValue.replaceAll("[^\\sa-zA-Z-\\d]", ""));
            }
            // Check if the new character is greater than LIMIT
            if (txfUserName.getText().length() >= charLimit) {
                txfUserName.setText(txfUserName.getText().substring(0, charLimit));
            }
        });
    }

    private void actBTNOpenChat(Button btnOpenChat) {
        btnOpenChat.setOnAction(event -> {
            try {
                actOpenChat();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void actEnterOpenChat(TextField txfUserName) {
        txfUserName.setOnAction(event -> {
            try {
                actOpenChat();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


}
