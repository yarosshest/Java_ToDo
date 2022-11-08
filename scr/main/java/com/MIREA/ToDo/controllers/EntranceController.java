package com.MIREA.ToDo.controllers;

import com.MIREA.ToDo.entity.User;
import com.MIREA.ToDo.repository.DayRepository;
import com.MIREA.ToDo.service.UserService;
import data.ScheduleTeam;
import data.Schedules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;

@Controller
public class EntranceController {
    @Autowired
    private UserService userService;
    @Autowired
    static private Schedules schedules = new Schedules();
    @Autowired
    private DayRepository dayRepository;
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
        ScheduleTeam scheduleTeam = schedules.GetScheduleTeam(institute, String.valueOf(now.get(Calendar.YEAR)), group);
        // TODO: adding DAY in db

        if (!userService.saveUser(u)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "Login/Registration";
        }
        return "redirect:/login";
    }
}