package com.MIREA.ToDo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Вход");
        return "Home";
    }
    @GetMapping("/main")
    public String MainPage(Model model) {
        model.addAttribute("title", "Главная страница");
        return "MainPage";
    }

}