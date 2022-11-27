package com.MIREA.ToDo.service;

import com.MIREA.ToDo.entity.Discipline;
import com.MIREA.ToDo.entity.Pair;
import com.MIREA.ToDo.repository.DisciplineRepository;
import com.MIREA.ToDo.repository.PairRepository;
import com.MIREA.ToDo.repository.UserRepository;
import data.DayParser;
import data.PairParser;
import data.ScheduleTeam;
import data.Schedules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@Service
public class PairService {

    private static final int COUNT_WEEKDAY = 7;

    @Autowired
    private PairRepository pairRepository;
    @Autowired
    private DisciplineRepository disciplineRepository;
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
            int weekday = 0;
            for (DayParser dayParser : scheduleTeam.GetListDay()) {
                List<PairParser> pairs = dayParser.GetListPair();
                for (PairParser pair: pairs) {
                    String discipline;
                    for (int parity : new ArrayList<>(Arrays.asList(Pair.EVEN_WEEK, Pair.ODD_WEEK))){
                        discipline = pair.GetDiscipline(parity);
                        if (!disciplineRepository.existsByDiscipline(discipline))
                            disciplineRepository.save(new Discipline(discipline));
                        Long discipline_id = disciplineRepository.findAllByDiscipline(discipline).getId();
                        pairRepository.save(new Pair(group, weekday, parity, discipline_id, pair));
                    }
                }
                weekday += 1;
            }
        }
    }
}
