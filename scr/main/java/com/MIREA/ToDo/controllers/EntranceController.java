package com.MIREA.ToDo.controllers;

import com.MIREA.ToDo.entity.Pair;
import com.MIREA.ToDo.entity.User;
import com.MIREA.ToDo.repository.PairRepository;
import com.MIREA.ToDo.service.UserService;
import data.DayParser;
import data.PairParser;
import data.ScheduleTeam;
import data.Schedules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;
import java.util.List;

@Controller
public class EntranceController {
    private static final int COUNT_WEEKDAY = 7;
    @Autowired
    private UserService userService;
    @Autowired
    static private Schedules schedules = new Schedules();
    @Autowired
    private PairRepository pairRepository;
    @GetMapping("/")
    public String FacePage(Model model) {
        model.addAttribute("title", "Добро пожаловать");
        return "FacePage";
    }

    @GetMapping("/registration")
    public String Registration(Model model) {
        model.addAttribute("title", "Регистрация");
        return "Login/Registration";
    }

    @PostMapping("/registration")
    private String addUser(@RequestParam String email, @RequestParam String password, @RequestParam String institute, @RequestParam String group, Model model){
        User u = new User(email,password, institute, group);

        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR) % 100;
        int course = year - Integer.parseInt(group.trim());
        ScheduleTeam scheduleTeam = schedules.GetScheduleTeam(institute, String.valueOf(course), group);
        for (DayParser dayParser : scheduleTeam.GetListDay()) {
            List<PairParser> pairs = dayParser.GetListPair();
            for (int i = 0; i < COUNT_WEEKDAY; i++) {
                pairRepository.save(new Pair(group, i, Pair.ODD_WEEK, pairs.get(i)));
                pairRepository.save(new Pair(group, i, Pair.EVEN_WEEK, pairs.get(i)));
            }
        }

        if (!userService.saveUser(u)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "Login/Registration";
        }
        return "redirect:/login";
    }
}