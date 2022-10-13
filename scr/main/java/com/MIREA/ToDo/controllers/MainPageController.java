package com.MIREA.ToDo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Controller
public class MainPageController {

    @GetMapping("/main")
    public String MainPage(Model model) {
        model.addAttribute("title", "Главная страница");

        ArrayList<Week> schedule = new ArrayList<>();  // return from UserId.FindUser(uid)
        schedule.add(new Week("MATAN"));
        model.addAttribute("schedule", schedule);

        return "TaskManager/MainPage";
    }
}