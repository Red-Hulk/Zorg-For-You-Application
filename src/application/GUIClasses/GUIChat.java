package application.GUIClasses;

import application.BackEndClasses.ChatCreate;
import application.abstractClasses.LeftPanelGUI;
import application.interfaces.BaseScreen;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public abstract class GUIChat extends LeftPanelGUI implements BaseScreen {

    protected TextField input;
    protected Label errUserName;
    protected Stage chatStage;
    protected GridPane chatPane = new GridPane();

    public GUIChat() {
        setUpMainScreen();
    }

    @Override
    public void setUpMainScreen() {
        setUpLeftPanel();
    }

    protected Parent setUpChat(ChatCreate chatCreate) {
        //Columnfullwidth
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(Priority.ALWAYS);
        chatPane.getColumnConstraints().addAll(col1);

        chatPane.setId("chatPane");

        //Chatpane width
        chatPane.setPrefWidth(355);

        input = new TextField();
        input.setPromptText("Uw bericht");
        //On enter send message to charcreate to handle message
        setOnEnterInput(chatCreate);

        Button btnSendMessage = new Button("Verstuur");
        //send message to charcreate to handle message
        actBTNSendMessage(btnSendMessage, chatCreate);

        //Placement button
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().add(btnSendMessage);
        AnchorPane.setRightAnchor(btnSendMessage, 1.0);

        ScrollPane scrollPaneChat = new ScrollPane(chatPane);
        scrollPaneChat.setPrefHeight(350);

        VBox root = new VBox(3, scrollPaneChat, input, anchorPane);
        root.setPrefHeight(400);

        return root;
    }

    private void actBTNSendMessage(Button btnSendMessage, ChatCreate chatCreate) {
        btnSendMessage.setOnAction(event -> {
            sendMessage(chatCreate);
        });
    }

    private void sendMessage(ChatCreate chatCreate) {
        //als inpit niet leeg send message
        if (!input.getText().trim().isEmpty()) {
            //verstuur bericht
            chatCreate.actSendInput(input);
            input.setPromptText("Uw bericht");
        } else {
            input.setPromptText("Wel iets invullen");
            input.getParent().requestFocus();
        }
    }

    private void setOnEnterInput(ChatCreate chatCreate) {
        input.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    sendMessage(chatCreate);
                }
            }
        });
    }

    @Override
    protected void setUpLeftPanelButtons() {

    }
}
