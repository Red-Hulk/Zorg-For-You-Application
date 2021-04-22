package application.BackEndClasses;

import application.Controller;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatClient extends Database {

    private ArrayList<ChatCreate> chatCreateArrayList = new ArrayList<>();

    private final String usrName ,onderwerp;
    private final Stage stage;

    public ChatClient(String usrName, Stage stage,String onderwerp) {
        this.usrName = usrName;
        this.stage = stage;
        this.onderwerp = onderwerp;
        setOnCloseStage();
    }


    public ChatCreate newCreateChat(GridPane chatPane) throws Exception {
        try {
            ChatCreate chatCreate = new ChatCreate(true, chatPane, usrName);
            String qInsertClient = "INSERT into tbl_client(naam) VALUES (?)";

            PreparedStatement pstInsertClient = conn.prepareStatement(qInsertClient, Statement.RETURN_GENERATED_KEYS);
            pstInsertClient.setString(1, usrName);

            pstInsertClient.execute();
            ResultSet resultSet = pstInsertClient.getGeneratedKeys();
            if (resultSet.next()) {
                String qInsertGesprek = "INSERT INTO tbl_gesprek (client_id, onderwerp,status)VALUES(?,?,?)";
                PreparedStatement pstInsertGesprek = conn.prepareStatement(qInsertGesprek, Statement.RETURN_GENERATED_KEYS);
                pstInsertGesprek.setInt(1, resultSet.getInt(1));
                pstInsertGesprek.setString(2, onderwerp);
                //Status gesprek 0=done 1=bezig 2=open(geen proffaanwezig);
                pstInsertGesprek.setInt(3, 2);
                pstInsertGesprek.execute();
                ResultSet resultSet2 = pstInsertGesprek.getGeneratedKeys();
                if (resultSet2.next()) {
                    chatCreate.setChatID(resultSet2.getInt(1));
                }
            }
            chatCreateArrayList.add(chatCreate);
            return chatCreate;
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void setOnCloseStage() {
        stage.setOnCloseRequest(event -> {
            try {
                //Voor later gebruik stel dat de gebruiker meerderere chats mag/kan maken is nu niet nodig tho
                //de foreach ^^^^^^^
                chatCreateArrayList.forEach(chatCreate -> {
                    try {
                        //als window gesloten wordt kan de chat niet opnieuw geopend worden dus moet het als beeindigd  beschouwt worden en dus moet de status naar 0(beeindigd)
                        String qAltGesprekStatus = "UPDATE `tbl_gesprek` SET `status`= 0 WHERE  `gesprek_id`= ?";
                        PreparedStatement pstGesprekStatus = conn.prepareStatement(qAltGesprekStatus);
                        pstGesprekStatus.setInt(1, chatCreate.getChatID());
                        pstGesprekStatus.execute();
                        chatCreate.closeConnection();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
