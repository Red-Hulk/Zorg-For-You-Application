/*LOGIN SUBSYSTEM*/

package application.BackEndClasses;

import javafx.scene.control.CheckBox;
import application.Controller;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Registratie extends Database {

    private String strGebruikersnaam;
    private String strWachtwoord;
    private String strNaam;
    private String strEmail;
    private String strDiploma;
    private CheckBox checkBox;
    PreparedStatement pst;

    //Constructor
    public Registratie(String strGebruikersnaam, String strWachtwoord, String strNaam, String strEmail, String strDiploma) {
        this.strGebruikersnaam = strGebruikersnaam;
        this.strWachtwoord = strWachtwoord;
        this.strNaam = strNaam;
        this.strEmail = strEmail;
        this.strDiploma = strDiploma;
        this.checkBox = new CheckBox();
    }

    public void newPerson(Registratie persoon){

        try {

            String query = "INSERT INTO persoon (gebruikersnaam, wachtwoord, naam, email, diploma) " + " VALUES (?,?,?,?,?)";

            pst = conn.prepareStatement(query);

            pst.setString(1, persoon.getGebruikersnaam());
            pst.setString(2, persoon.getWachtwoord());
            pst.setString(3, persoon.getNaam());
            pst.setString(4, persoon.getEmail());
            pst.setString(5, persoon.getDiploma());

            pst.execute();

            conn.close();

            JOptionPane.showMessageDialog(null, "Registratie Succesvol");

        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE,null,ex);
        }
    }


    public String getGebruikersnaam() {
        return strGebruikersnaam;
    }

    public void setGebruikersnaam(String strGebruikersnaam) {
        this.strGebruikersnaam = strGebruikersnaam;
    }

    public String getWachtwoord() {
        return strWachtwoord;
    }

    public void setWachtwoord(String strWachtwoord) {
        this.strWachtwoord = strWachtwoord;
    }

    public String getNaam() {
        return strNaam;
    }

    public void setNaam(String strNaam) {
        this.strNaam = strNaam;
    }

    public String getEmail() {
        return strEmail;
    }

    public void setEmail(String strEmail) {
        this.strEmail = strEmail;
    }

    public String getDiploma() {
        return strDiploma;
    }

    public void setDiploma(String strDiploma) {
        this.strDiploma = strDiploma;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }
}
