package application.BackEndClasses;

import application.abstractClasses.ChatNetworkConnection;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ChatCreate {

    private final boolean isServer;
    private final GridPane chatPane;
    private final ChatNetworkConnection chatConnection;
    private final String usrName;
    private Integer messageCount;
    private int chatID;

    public ChatCreate(boolean isServer, GridPane chatPane, String usrName) throws Exception {
        this.isServer = isServer;
        this.usrName = usrName;
        this.chatPane = chatPane;

        messageCount = 0;

        //Boolean function function | if boolean true 1e function/statement , false 2e function/statement
        chatConnection = isServer ? createServer() : createClient();
        chatConnection.startConnection();
    }

    public void closeConnection() throws Exception {
        chatConnection.closeConnection();
    }

    public int getChatID() {
        return chatID;
    }

    public void setChatID(int chatID) {
        this.chatID = chatID;
    }

    public void actSendInput(TextField input) {
        //Format message string
        String message = isServer ? "[" + usrName + "]" : "[Professional " + usrName + "]";
        String sendMessage = message + ": " + input.getText();
        String ownMessage = input.getText() + " :" + message;

        input.clear();

        Label labelErr = new Label("Uw bericht is niet verzonden,omdat er niemand anders in deze chat zit");

        //add message to own chatPane
        Label label1 = new Label(ownMessage);
        label1.setWrapText(true);
        chatPane.add(label1, 0, messageCount);
        GridPane.setHalignment(label1, HPos.RIGHT);
        messageCount++;
        //send message
        try {
            chatConnection.send(sendMessage);
        } catch (Exception e) {
            chatPane.add(labelErr, 0, messageCount);
            messageCount++;
        }
    }

    public ChatNetworkHost createServer() {
        return new ChatNetworkHost(8989, data -> {
            Platform.runLater(() -> {
                //What to do with received data(message string)
                Label label2 = new Label(data.toString());
                label2.setWrapText(true);
                chatPane.add(label2, 0, messageCount);
                messageCount++;
            });
        });
    }

    private ChatNetworkClient createClient() {
        return new ChatNetworkClient("localhost", 8989, data -> {
            Platform.runLater(() -> {
                //What to do with received data(message string)
                Label label2 = new Label(data.toString());
                label2.setWrapText(true);
                chatPane.add(label2, 0, messageCount);
                messageCount++;
            });
        });
    }


}
