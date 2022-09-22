package data.sql;
import java.sql.*;
import java.util.Objects;

public class UserSQL {
    private final MasterSQL m;

    public UserSQL(MasterSQL master) throws SQLException {

        m = master;
        String sql = "SELECT EXISTS(SELECT name FROM sqlite_master WHERE type='table' AND name='User');";
        ResultSet res = m.stat.executeQuery(sql);
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
            m.stat.execute("INSERT INTO User (login, pass) VALUES ('"+Ulog+"', '"+Upass+"');");
            return true;
        }
        else {
            return false;
        }
    }

    public void SetUserCol(int id,String coll, String val) throws SQLException {
        m.stat.executeUpdate("UPDATE User SET '"+coll+"' = '"+val+"' where id = "+id+";");
    }

    public boolean CheckUser(String Ulog, String Upass) throws SQLException {
        ResultSet res = m.stat.executeQuery("SELECT login, pass FROM User WHERE login = '"+Ulog+"' AND pass = '"+Upass+"';");
        return res.getFetchSize() > 0;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MasterSQL m = new MasterSQL();
        UserSQL a = new UserSQL(m);
        a.AddUser("admin", "admin");
        System.out.print(a.CheckUser("admin", "admin"));
    }
}