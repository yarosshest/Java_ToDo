package data.sql;
import java.sql.*;
import java.util.Objects;
import java.sql.DriverManager;

public class GroupSQL {
    private final MasterSQL m;

    public GroupSQL (MasterSQL master) throws ClassNotFoundException, SQLException {
        m = master;
        String sql = "CREATE TABLE IF NOT exists \"Group\"\n" +
                "(\n" +
                "    id   INTEGER not null\n" +
                "        primary key autoincrement,\n" +
                "    name TEXT    not null\n" +
                ");";
        m.stat.execute(sql);
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

    public int AddGroup(String name) throws SQLException {
        if (!CheckExists(name)){
            String sql = "INSERT INTO 'Group' (name) VALUES ('"+name+"');";
            PreparedStatement ps = m.c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return -1;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MasterSQL m = new MasterSQL();
        GroupSQL a = new GroupSQL(m);
        a.AddGroup("ИКБО-06-21");
    }
}
