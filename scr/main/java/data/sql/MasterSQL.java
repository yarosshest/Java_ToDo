package data.sql;

import java.sql.*;

public class MasterSQL {
    public Connection c;
    public Statement stat;

    public MasterSQL () throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:db");
        stat = c.createStatement();
    }
}
