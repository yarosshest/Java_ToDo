package com.MIREA.ToDo.service;

import com.MIREA.ToDo.entity.Pair;
import com.MIREA.ToDo.entity.User;
import com.MIREA.ToDo.repository.PairRepository;
import com.MIREA.ToDo.repository.UserRepository;
import data.DayParser;
import data.PairParser;
import data.ScheduleTeam;
import data.Schedules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class PairService {

    private static final int COUNT_WEEKDAY = 6;

    @Autowired
    private PairRepository pairRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    static private Schedules schedules = new Schedules();
    @Autowired
    UserService userService;

    public void updatePairs(){
        pairRepository.deleteAll();
        List<String> gr = userRepository.getDistinctByStudygr();
        for (String i : gr){
            String group = i.split(",")[0];
            String univesiti = i.split(",")[1];
            Calendar now = Calendar.getInstance();
            int year = now.get(Calendar.YEAR) % 100;
            int course = year - Integer.parseInt(group.trim().substring(8,10)) + 1;
            ScheduleTeam scheduleTeam = schedules.GetScheduleTeam(univesiti, String.valueOf(course), group);
            for (DayParser dayParser : scheduleTeam.GetListDay()) {
                List<PairParser> pairs = dayParser.GetListPair();
                for (int j = 0; j < COUNT_WEEKDAY; j++) {
                    pairRepository.save(new Pair(group, j, Pair.ODD_WEEK, pairs.get(j)));
                    pairRepository.save(new Pair(group, j, Pair.EVEN_WEEK, pairs.get(j)));
                }
            }
        }
    }
}
