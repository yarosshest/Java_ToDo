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

    @GetMapping("/entrance")
    public String Entrance(Model model) {
        model.addAttribute("title", "Вход");
        return "Entrance/Entrance";
    }
    @GetMapping("entrance/registration")
    public String Registration(Model model) {
        model.addAttribute("title", "Регистрация");
        return "Entrance/Registration";
    }

    @PostMapping("/entrance")
    private String LogIn(@RequestParam String email, @RequestParam String password, Model model) throws SQLException, ClassNotFoundException {
        MasterSQL m = new MasterSQL();
        UserSQL a = new UserSQL(m);
        if (a.CheckUser(email, password))
            return "redirect:/";
        else
            return "Entrance/Entrance";
    }
    @PostMapping("entrance/registration")
    private String SignIn(@RequestParam String email, @RequestParam String password, @RequestParam String institute, @RequestParam String group, Model model) throws SQLException, ClassNotFoundException {
        MasterSQL m = new MasterSQL();
        UserSQL a = new UserSQL(m);
        a.AddUser(email, password);
        return "Entrance/Entrance";
    }
}