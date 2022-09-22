package java.data.sql;

import java.sql.*;

public class LessonSQL {

    private final MasterSQL m;

    public LessonSQL(MasterSQL master){
        m = master;
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
