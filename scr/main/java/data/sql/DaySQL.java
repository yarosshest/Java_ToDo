package data.sql;

import java.sql.*;

public class DaySQL {
    private final MasterSQL m;

    public DaySQL (MasterSQL master) throws ClassNotFoundException, SQLException {
        m = master;
        String sql = "CREATE TABLE IF NOT exists Day\n" +
                "(\n" +
                "    id       INTEGER not null primary key autoincrement,\n" +
                "    date     TEXT    not null,\n" +
                "    group_id INTEGER references \"Group\"(id)\n" +
                ");";
    }

    public int AddDay(String Udate, int group) throws SQLException {
        String sql = "INSERT INTO Day (date, group_id) VALUES ('" + Udate + "', '" + group + "');";
        PreparedStatement ps = m.c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        } else {
            return -1;
        }
    }

    public int GetId(String date, int group) throws SQLException {
        ResultSet res = m.stat.executeQuery(
                "SELECT id FROM Day WHERE date = '"+date+"' AND group_id ='"+group+"';");
        while(res.next())
            return res.getInt("id");
        return -1;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MasterSQL m = new MasterSQL();
        DaySQL a = new DaySQL(m);
        GroupSQL b = new GroupSQL(m);
        a.AddDay("12.09.2022", b.GetID("ИКБО-06-21"));
    }

}
