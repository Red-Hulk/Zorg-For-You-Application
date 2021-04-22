/*LOGIN SUBSYSTEM*/

package application.BackEndClasses;


import java.sql.*;


public abstract class Database {


    protected Connection conn;

    /*Connection to the database, if there is a other connection required, change the variables db/port/user/passwd*/
    // This is domein to go to database:   https://adainforma.tk/phpmyadmin

    public Database(){
        String host = "161.35.94.166";
        String db ="bp3_zorg4you_02";
        String user = "zorg4you02";
        String password = "ld8qM?17";

        try {
            conn = DriverManager.getConnection("jdbc:mysql://"+ host +"/" + db ,user,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


}
