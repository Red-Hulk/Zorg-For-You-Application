package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {

    public Connection con;

    public DataBase() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://adainforma.tk/bp3_zorg4you_02", "zorg4you02", "ld8qM?17");
    }

    public Connection getCon() {
        return con;
    }
}
