package com.MIREA.ToDo.controllers;

import com.MIREA.ToDo.entity.User;
import com.MIREA.ToDo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

import data.sql.MasterSQL;
import data.sql.UserSQL;

@Controller
public class EntranceController {

    @Autowired
    private UserService userService;
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
    private String addUser(@RequestParam String email, @RequestParam String password, @RequestParam String institute, @RequestParam String group, Model model){
        User u = new User(email,password, institute, group);
        if (!userService.saveUser(u)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }
        return "redirect:/login";
    }
}