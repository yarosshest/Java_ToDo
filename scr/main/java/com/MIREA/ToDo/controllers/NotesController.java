package com.MIREA.ToDo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NotesController {
    @GetMapping("/main/notes")
    public String Notes(Model model) {
        model.addAttribute("title", "Заметки");
        return "TaskManager/Notes";
    }
}
