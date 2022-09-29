package data.sql;

import java.sql.*;

public class LessonSQL {

    private final MasterSQL m;

    public LessonSQL(MasterSQL master) throws SQLException {

        m = master;
        String sql = "CREATE TABLE IF NOT exists Lesson\n" +
                "(\n" +
                "    id      INTEGER not null primary key autoincrement,\n" +
                "    name    TEXT    not null,\n" +
                "    teacher TEXT,\n" +
                "    pars    TEXT    not null,\n" +
                "    day_id  INTEGER not null references day(id)\n" +
                ");";
        m.stat.execute(sql);
    }

    public int AddLesson(String Uname, String Upars, int DayId) throws SQLException {
        String sql = "INSERT INTO Lesson (name, pars, day_id) VALUES ('" + Uname + "', '" + Upars + "', '" + DayId + "');";
        PreparedStatement ps = m.c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.execute();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return -1;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MasterSQL m = new MasterSQL();
        LessonSQL a = new LessonSQL(m);
        System.out.println(a.AddLesson("линал", "1", 1));
    }

}
