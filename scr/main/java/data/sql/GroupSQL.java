package java.data.sql;
import java.sql.*;
import java.util.Objects;
import java.sql.DriverManager;

public class GroupSQL {
    private static Connection c;
    private static Statement stat;
    private static ResultSet res;

    public GroupSQL () throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:./scr/main/db/db");
        stat = c.createStatement();
    }

    private boolean CheckExists(String name) throws SQLException{
        res = stat.executeQuery("SELECT name FROM 'Group'");
        while(res.next())
            if (Objects.equals(name, res.getString("name")))
                return true;
        return false;
    }

    public boolean AddGroup(String name) throws SQLException {
        if (!CheckExists(name)){
            stat.execute("INSERT INTO 'Group' (name) VALUES ('"+name+"');");
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        GroupSQL a = new GroupSQL();
        a.AddGroup("ИКБО-06-21");
    }
}
