package data.sql;

import data.ScheduleTeam;
import data.ScheduleCourse;

import java.sql.SQLException;


public class generator {
    public void run(){
        ScheduleTeam obj = new ScheduleTeam(
                new ScheduleCourse("IIT", "2", "https://webservices.mirea.ru/upload/iblock/348/jtw5jxfxeg97nm59b686315waa3htq92/IIT_2-kurs_22_23_osen_07.10.2022.xlsx")
                , "ИКБО-06-21"
        );
        obj.GetDay(0);
        System.out.println("Diego");
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        generator g = new generator();
        g.run();
    }
}
