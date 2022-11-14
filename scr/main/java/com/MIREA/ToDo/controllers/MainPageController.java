package com.MIREA.ToDo.controllers;

import com.MIREA.ToDo.entity.Day;
import com.MIREA.ToDo.repository.DayRepository;
import com.MIREA.ToDo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;

@Controller
public class MainPageController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DayRepository dayRepository;

    @GetMapping("/main")
    public String MainPage(Model model) {
        model.addAttribute("title", "Главная страница");

        ArrayList<Week> schedule = new ArrayList<>();  // return from UserId.FindUser(uid)
        schedule.add(new Week("MATAN"));
        model.addAttribute("schedule", schedule);

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String group = userRepository.findByUsername(authentication.getName()).getGroup();
//        Optional<Day> day = dayRepository.findByGroup(group);
//        ArrayList<Day> res = new ArrayList<>();
//        day.ifPresent(res::add);
//        model.addAttribute("note", res);

        return "TaskManager/MainPage";
    }
}