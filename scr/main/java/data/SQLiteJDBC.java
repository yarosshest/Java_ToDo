package scr.main.java.data;
import java.sql.*;
import java.util.Objects;

public class SQLiteJDBC {
    private static Connection c;
    private static Statement stat;
    private static ResultSet res;

    public SQLiteJDBC() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:./scr/main/db/db");
        stat = c.createStatement();
        System.out.println("Opened database successfully");
    }

    private boolean CheckLogin(String login) throws SQLException{
        res = stat.executeQuery("SELECT login FROM User");
        while(res.next())
            if (Objects.equals(login, res.getString("login")))
                return false;

        return true;
    }

    public boolean AddUser(String Ulog, String Upass) throws SQLException {
        if (CheckLogin(Ulog)){
            stat.execute("INSERT INTO User (login, pass) VALUES ('"+Ulog+"', '"+Upass+"');");
            return true;
        }
        else {
            return false;
        }
    }

    public void SetUserCol(int id,String coll, String val) throws SQLException {
        stat.executeUpdate("UPDATE User SET '"+coll+"' = '"+val+"' where id = "+id+";");
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SQLiteJDBC a = new SQLiteJDBC();
        a.SetUserCol(1,"group", "ИКБО-06-21");

    }
}