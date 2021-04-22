/*LOGIN SUBSYSTEM*/

package application.BackEndClasses;

import application.Controller;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseMethods extends Database {

    protected ArrayList<Registratie> lijst;
    protected PreparedStatement pst;

    public ArrayList<Registratie> overview(){
        lijst = new ArrayList<>();
        String sQuery = "SELECT * FROM persoon";
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeQuery(sQuery);
            ResultSet rSet = stmt.getResultSet();
            while (rSet.next())
            {
                Registratie vt = new Registratie(rSet.getString("gebruikersnaam"), rSet.getString("wachtwoord"), rSet.getString("naam"), rSet.getString("email"), rSet.getString("diploma") );
                lijst.add(vt);
            }
            stmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return lijst;

    }

    public void NewData(Registratie str){
        try {

            String query = "INSERT INTO registratie_beheer (gebruikersnaam, wachtwoord, naam, email, diploma) " + " VALUES (?,?,?,?,?)";


            pst = conn.prepareStatement(query);

            pst.setString(1, str.getGebruikersnaam());
            pst.setString(2, str.getWachtwoord());
            pst.setString(3, str.getNaam());
            pst.setString(4, str.getEmail());
            pst.setString(5, str.getDiploma());

            pst.execute();

            conn.close();
            JOptionPane.showMessageDialog(null, "Gelukt");

        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    public void delData(Registratie str){
        try {

            String query = "DELETE FROM persoon WHERE gebruikersnaam = ?";


            pst = conn.prepareStatement(query);

            pst.setString(1, str.getGebruikersnaam());

            pst.execute();

            conn.close();

            JOptionPane.showMessageDialog(null, "Gelukt");

        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE,null,ex);
        }

    }
}
