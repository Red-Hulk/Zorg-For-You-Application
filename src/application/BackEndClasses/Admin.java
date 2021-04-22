package application.BackEndClasses;

import application.DataBase;
import application.GUIClasses.GUIMaintenance;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;

public class Admin {

    private String sNaam, sGebruikersNaam, sEmail, sDiploma;
    private String sId, sVraag, sCategorie, sAntwoord;
    private int vid;
    private String gb;
    private String sForum;
    private ArrayList<String> personenLijst, vragenLijst, forumLijst;
    private Connection con;

    public Admin(Button btnPersonen, TextArea taVragen, TextArea taForum, TextField txtDelForum, TextField txtDelVraag, Button btnDelForum, Button btnDelVraag) throws SQLException, ClassNotFoundException {
        setCon();
        ophalenVragen(taVragen);
        //ophalenForum(taForum);
        delForum(txtDelForum, btnDelForum);
        delVraag(txtDelVraag, btnDelVraag, taVragen);
        btnPersoon(btnPersonen);
    }

    private void btnPersoon(Button btnPersonen) {
        btnPersonen.setOnAction(e->{
            new GUIMaintenance();
        });
    }

    public void setCon() throws SQLException, ClassNotFoundException {
        DataBase db = new DataBase();
        this.con = db.getCon();
    }

    //vragen verwijderen uit database
    private void delVraag(TextField txtDelVraag, Button btnDelVraag, TextArea taVragen) {
        btnDelVraag.setOnAction(e-> {
            try {
                vid = Integer.parseInt(txtDelVraag.getText());

                //query opstellen
                String query = "DELETE FROM hulp_vragen WHERE id = " + vid;

                //conn wordt aangemaakt bij de database connectie
                Statement stVragen = con.createStatement();

                //query uitvoeren en java result set aanmaken
                stVragen.executeUpdate(query);

                //statement sluiten
                stVragen.close();

                ophalenVragen(taVragen);

                txtDelVraag.setText("");

            }  catch (Exception i) {
                //error teruggeven
                System.err.println("Got an exception! ");
                System.err.println(i.getMessage());
            }
        });
    }

    //forum items verwijderen uit database
    private void delForum(TextField txtDelForum, Button btnDelForum) throws SQLException, ClassNotFoundException {
    }


    private void ophalenVragen(TextArea taVragen) throws SQLException, ClassNotFoundException {
        vragenLijst = new ArrayList<>();

        taVragen.clear();

        //query opstellen
        String query = "SELECT * FROM hulp_vragen";

        //conn wordt aangemaakt bij de database connectie
        Statement stVragen = con.createStatement();

        //query uitvoeren en java result set aanmaken
        ResultSet rsVragen = stVragen.executeQuery(query);

        //door resultset heen wandelen zolang er een volgend resultaat is
        while (rsVragen.next()) {
            //data ophalen uit database en plaatsen in arraylist
            sId = rsVragen.getString("id");
            sVraag = rsVragen.getString("vraag");
            sCategorie = rsVragen.getString("categorie");
            sAntwoord = rsVragen.getString("antwoord");

            vragenLijst.add(sId + " | " + sVraag + " | " + sCategorie + " | " + sAntwoord);
        }

        for (String s : vragenLijst) {
            //uitprinten van de variabelen uit database
            s = s + "\n" + taVragen.getText();
            taVragen.setText(s);
        }

        //statement sluiten
        stVragen.close();
    }

//    private void ophalenForum(TextArea taForum) throws SQLException, ClassNotFoundException {
//        //TODO: aanpassen op tabel forum zodra daar
//        forumLijst = new ArrayList<>();
//        //query opstellen
//        String query = "SELECT * FROM personen";
//
//        //conn wordt aangemaakt bij de database connectie
//        Statement stPersonen = con.createStatement();
//
//        //query uitvoeren en java result set aanmaken
//        ResultSet rsPersonen = stPersonen.executeQuery(query);
//
//        //door resultset heen wandelen zolang er een volgend resultaat is
//        while (rsPersonen.next()) {
//            //data ophalen uit database en plaatsen in arraylist
//            sNaam = rsPersonen.getString("naam");
//            sGebruikersNaam = rsPersonen.getString("gebruikersnaam");
//            sEmail = rsPersonen.getString("email");
//            sDiploma = rsPersonen.getString("diploma");
//
//            forumLijst.add(sNaam + " | " + sGebruikersNaam + " | " + sEmail + " | " + sDiploma);
//        }
//
//        for (String s : forumLijst) {
//            //uitprinten van de variabelen uit database
//            s = s + "\n" + taForum.getText();
//            taForum.setText(s);
//        }
//
//        //statement sluiten
//        stPersonen.close();
//    }
}