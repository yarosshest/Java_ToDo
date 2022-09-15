package scr.main.java.data.sql;

import java.sql.*;

public class LessonSQL {
    private static Connection c;
    private static Statement stat;
    private static ResultSet res;

    public LessonSQL() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:./scr/main/db/db");
        stat = c.createStatement();
    }

    public int AddLesson(String Uname, String Upars) throws SQLException {
        String sql = "INSERT INTO Lesson (name, pars) VALUES ('" + Uname + "', '" + Upars + "');";
        PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.execute();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return -1;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        LessonSQL a = new LessonSQL();
        System.out.println(a.AddLesson("линал", "1"));

    }

}
