package com.MIREA.ToDo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class NotesController {

    @GetMapping("/main/{uid}/notes")
    public String Notes(@PathVariable(value="uid") String uid, Model model) {
        model.addAttribute("title", "Заметки");
        return "TaskManager/Notes";
    }
}
