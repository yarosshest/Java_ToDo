package com.MIREA.ToDo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPage {

    @GetMapping("/?email=")
    public String MainPage(Model model) {
        model.addAttribute("title", "pepe");
        return "MainPage";
    }
}
