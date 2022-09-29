package data.sql;
import java.sql.*;
import java.util.Objects;

public class UserSQL {
    private final MasterSQL m;

    public UserSQL(MasterSQL master) throws SQLException {
        m = master;
        String sql = "CREATE TABLE IF NOT exists User\n" +
                "(\n" +
                "    passwd   TEXT not null,\n" +
                "    login    TEXT not null,\n" +
                "    FIO      TEXT,\n" +
                "    id       INTEGER\n" +
                "        primary key autoincrement,\n" +
                "    group_id INTEGER\n" +
                "        references \"Group\"(id)\n" +
                ");";
        m.stat.execute(sql);
    }

    private boolean CheckLogin(String login) throws SQLException{
        ResultSet res = m.stat.executeQuery("SELECT login FROM User");
        while(res.next())
            if (Objects.equals(login, res.getString("login")))
                return false;
        return true;
    }

    public boolean AddUser(String Ulog, String Upass) throws SQLException {
        if (CheckLogin(Ulog)){
            m.stat.execute("INSERT INTO User (login, passwd) VALUES ('"+Ulog+"', '"+Upass+"');");
            return true;
        }
        else {
            return false;
        }
    }

    public void SetUserCol(int id,String coll, String val) throws SQLException {
        m.stat.executeUpdate("UPDATE User SET '"+coll+"' = '"+val+"' where id = "+id+";");
    }

    public boolean CheckUser(String Ulog, String Upass) throws SQLException {  // stasit ?
        ResultSet res = m.stat.executeQuery("SELECT COUNT(*) FROM User WHERE login = '"+Ulog+"' AND passwd = '"+Upass+"';");
        return res.getInt(1) == 1;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MasterSQL m = new MasterSQL();
        UserSQL a = new UserSQL(m);
        a.AddUser("adin", "admin");
        System.out.print(a.CheckUser("admin", "admin"));
    }
}