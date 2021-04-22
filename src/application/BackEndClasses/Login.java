/*LOGIN SUBSYSTEM*/

package application.BackEndClasses;

import application.GUIClasses.GUIProfessional;
import javafx.stage.Stage;
import application.Controller;
import application.GUIClasses.GUILogin;

import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login extends Database{

    private String strGebruikersnaam;
    private String strWachtwoord;
    PreparedStatement pst;
    ResultSet rs;

    //Constructor
    public Login(String strGebruikersnaam, String strWachtwoord) {
        this.strGebruikersnaam = strGebruikersnaam;
        this.strWachtwoord = strWachtwoord;
    }

    /*This function checks if username and password are located in database */
    public void checkLoginGegevens(Login log){
        try {
            pst = conn.prepareStatement("select * from registratie_beheer where gebruikersnaam=? and wachtwoord=?");

            pst.setString(1, log.getGebruikersnaam());
            pst.setString(2, log.getWachtwoord());

            rs = pst.executeQuery();

            if(rs.next()){

                new GUIProfessional(rs.getString(1));

            }
            else{
                JOptionPane.showMessageDialog(null, "Login failed");
                Stage voorbeeld = new Stage();
                new GUILogin(voorbeeld);
            }

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
}
