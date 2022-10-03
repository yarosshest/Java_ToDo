package com.MIREA.ToDo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    @GetMapping("/")
    public String MainPage(Model model) {
        model.addAttribute("title", "Главная страница");
        return "MainPage";
    }

    @GetMapping("/notes")
    public String Notes(Model model) {
        model.addAttribute("title", "Заметки");
        return "Notes";
    }
}