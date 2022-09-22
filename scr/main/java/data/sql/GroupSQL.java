package scr.main.java.data.sql;
import java.sql.*;
import java.util.Objects;
import java.sql.DriverManager;

public class GroupSQL {
    private final MasterSQL m;

    public GroupSQL (MasterSQL master) throws ClassNotFoundException, SQLException {
        m = master;
    }

    private boolean CheckExists(String name) throws SQLException{
        ResultSet res = m.stat.executeQuery("SELECT name FROM 'Group'");
        while(res.next())
            if (Objects.equals(name, res.getString("name")))
                return true;
        return false;
    }

    public int GetID(String name) throws SQLException {
        ResultSet res = m.stat.executeQuery("SELECT id, name FROM 'Group'");
        while(res.next())
            if (Objects.equals(res.getString("name"), name))
                return res.getInt("id");
        return -1;
    }

    public boolean AddGroup(String name) throws SQLException {
        if (!CheckExists(name)){
            m.stat.execute("INSERT INTO 'Group' (name) VALUES ('"+name+"');");
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MasterSQL m = new MasterSQL();
        GroupSQL a = new GroupSQL(m);
        a.AddGroup("ИКБО-06-21");
    }
}
