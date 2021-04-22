package application.BackEndClasses;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ChatProf extends Database {


    private final String usrName;
    private int actChatID;
    private final Stage stage;

    public ChatProf(String usrName, Stage stage) {
        this.usrName = usrName;
        this.stage = stage;
        setOnCloseStage();
    }

    //het ophalen van alle openstaande chats en deze weer terug geven in een Arraylist waarin een dataClass zit met
    public ArrayList<ChatOpenClients> getActiveClients() {
        ArrayList<ChatOpenClients> chatOpenClients = new ArrayList<>();
        String qSelectGesprek = "SELECT * FROM `tbl_gesprek` WHERE `tbl_gesprek`.`status` = 2";
        try {
            Statement stmt = conn.createStatement();
            stmt.executeQuery(qSelectGesprek);
            ResultSet rSet = stmt.getResultSet();
            while (rSet.next()) {
                //het aanmaken van een dataClass + toevoegen aan de arraylist

                chatOpenClients.add(new ChatOpenClients(rSet.getInt(1), rSet.getInt(3), rSet.getString(2)));
            }
            stmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return chatOpenClients;
    }

    //Als de prof wilt de client z'n chat will joinen moet het gesprek status gelijk op 1(actief) gezet worden
    //verder gebeurt er niks
    //Omdat het niet mogelijk om meerdere chat servers aan te maken. Hoeft er geen server gegevens megegeven te worden omdat deze altijd het zelfde zijn

    public ChatCreate connectToClient(GridPane chatPane, int gesprekID) throws Exception {
        actChatID = gesprekID;
        try {
            String qAltGesprekStatus = "UPDATE `tbl_gesprek` SET `status`= 1 ,`professional_id` = ? WHERE  `gesprek_id`= ?";
            PreparedStatement pstGesprekStatus = conn.prepareStatement(qAltGesprekStatus);
            pstGesprekStatus.setString(1, usrName);
            pstGesprekStatus.setInt(2, gesprekID);

            System.out.println(usrName);
            System.out.println(gesprekID);
            System.out.println(qAltGesprekStatus);

            pstGesprekStatus.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ChatCreate(false, chatPane, usrName);
    }

    //als window gesloten wordt kan de chat niet opnieuw geopend worden dus moet het als beeindigd  beschouwt worden en dus moet de status naar 0(beeindigd)
    public void setOnCloseStage() {
        stage.setOnCloseRequest(event -> {
            try {
                String qAltGesprekStatus = "UPDATE `tbl_gesprek` SET `status`= 0 WHERE  `gesprek_id`= ?";
                PreparedStatement pstGesprekStatus = conn.prepareStatement(qAltGesprekStatus);
                pstGesprekStatus.setInt(1, actChatID);
                pstGesprekStatus.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


}
