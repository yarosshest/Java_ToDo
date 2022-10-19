package data.sql;

import data.ScheduleTeam;
import data.ScheduleCourse;

import java.sql.SQLException;


public class generator {
    public void run(){
        ScheduleTeam obj = new ScheduleTeam(
                new ScheduleCourse("IIT", "2", "https://webservices.mirea.ru/upload/iblock/46a/9yjndqsdw1eib3dfubn2rn2h1l3ios0p/IIT_2-kurs_22_23_osen_07.10.2022.xlsx")
                , "ИКБО-06-21"
        );
        System.out.println("Diego");
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        generator g = new generator();
        g.run();
    }
}
