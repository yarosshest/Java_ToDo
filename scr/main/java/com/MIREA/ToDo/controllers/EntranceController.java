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
public class EntranceController {
    @GetMapping("/")
    public String FacePage(Model model) {
        model.addAttribute("title", "Добро пожаловать");
        return "FacePage";
    }
    /*
    @GetMapping("/login")
    public String Entrance(Model model) {
        model.addAttribute("title", "Вход");
        return "Login/Login";
    }

    @GetMapping("/login/registration")
    public String Registration(Model model) {
        model.addAttribute("title", "Регистрация");
        return "login/registration";
    }

    @PostMapping("/login")
    private String LogIn(@RequestParam String email, @RequestParam String password, Model model) throws SQLException, ClassNotFoundException {
        MasterSQL m = new MasterSQL();
        UserSQL a = new UserSQL(m);
        if (a.CheckUser(email, password))
            return "redirect:/main/";
        else
            return "redirect:/login";
    }

    */
    @GetMapping("/registration")
    public String Registration(Model model) {
        model.addAttribute("title", "Регистрация");
        return "Login/Registration";
    }
    @PostMapping("/registration")
    private String SignIn(@RequestParam String email, @RequestParam String password, Model model) throws SQLException, ClassNotFoundException {
        MasterSQL m = new MasterSQL();
        UserSQL a = new UserSQL(m);
        a.AddUser(email, password);
        return "redirect:/login";
    }
}