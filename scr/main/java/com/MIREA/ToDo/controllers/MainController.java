package com.MIREA.ToDo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

import data.sql.MasterSQL;
import data.sql.UserSQL;

@Controller
public class MainController {

    @GetMapping("/")
    public String Home(Model model) {
        model.addAttribute("title", "Вход");
        return "home";
    }
    @PostMapping("/")
    private String SignIn(@RequestParam String email, @RequestParam String password, Model model) throws SQLException, ClassNotFoundException {
        MasterSQL m = new MasterSQL();
        UserSQL a = new UserSQL(m);
        if (a.CheckUser(email, password))
            return "MainPage";
        else
            return "home";
    }
}