package application.GUIClasses;

import application.BackEndClasses.ChatProf;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUIChatProf extends GUIChat {

    private ChatProf chatProf;
    private ScrollPane scrollPaneOpenClients;

    public GUIChatProf(String profName) {
        super();
        chatProf = new ChatProf(profName, chatStage);
        setUpLeftPanelButtons();
    }

    @Override
    public void setUpMainScreen() {
        setUpLeftPanel();

        chatStage = new Stage();
        chatStage.setTitle("Chatten");
        chatStage.setScene(new Scene(root, 600, 400));

        root.setCenter(setUpDefaultCenter());
        root.getStylesheets().add(baseStylePath + "styleChat.css");

        chatStage.show();
    }

    public Parent setUpDefaultCenter() {
        Label label = new Label("Klik een onderwerp aan om te chatten met een client");
        return new VBox(label);
    }

    @Override
    public void setUpLeftPanelButtons() {
        VBox vBox = new VBox(10);
        scrollPaneOpenClients = new ScrollPane(vBox);
        scrollPaneOpenClients.setLayoutX(7);
        scrollPaneOpenClients.setLayoutY(100);
        scrollPaneOpenClients.setId("scrollPaneOpenClients");
        try {
            //Vraag controller voor all ActiveClients maakt voor elke ActiveClients een butten aan en voegt die to aan een vBox
            chatProf.getActiveClients().forEach(dataCOpenClients -> {
                Button btnOpenClient = new Button(dataCOpenClients.getOnderwerp());
                vBox.getChildren().add(btnOpenClient);
                actBTNOpenClient(btnOpenClient, dataCOpenClients.getGesprek_id());
                btnOpenClient.setWrapText(true);
            });
        } catch (NullPointerException nullPointerException) {
            System.out.println("geen chatters");
        }

        containerButton.getChildren().add(scrollPaneOpenClients);
    }

    private void actBTNOpenClient(Button btnOpenClient, int GesprekID) {
        btnOpenClient.setOnAction(event -> {
            try {
                containerButton.getChildren().remove(scrollPaneOpenClients);
                //inladen chat
                //Omdat het niet mogelijk om meerdere chat servers aan te maken. Hoeft er geen server gegevens megegeven te worden omdat deze altijd het zelfde zijn
                //Wat er wel gebeurd is: Het gesprek in de database wordt op 1(actief/bezig) gezet
                root.setCenter(setUpChat(chatProf.connectToClient(chatPane, GesprekID)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
