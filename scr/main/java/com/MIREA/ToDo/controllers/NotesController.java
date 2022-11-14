package com.MIREA.ToDo.controllers;

import com.MIREA.ToDo.entity.Note;
import com.MIREA.ToDo.repository.NoteRepository;
import com.MIREA.ToDo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class NotesController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NoteRepository noteRepository;

    @GetMapping("/main/notes")
    public String Notes(Model model) {
        model.addAttribute("title", "Заметки");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long uid = userRepository.findByUsername(authentication.getName()).getId();
        Iterable<Note> notes = noteRepository.findAllByIdOwn(uid);
        model.addAttribute("notes", notes);
        return "TaskManager/Notes";
    }
    @GetMapping("/main/notes/add")
    public String AddNotes(Model model) {
        model.addAttribute("title", "Добавть заметку");
        return "TaskManager/NotesAdd";
    }

    @PostMapping("/main/notes/add")
    public String AddNote(@RequestParam String title, @RequestParam String text, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long uid = userRepository.findByUsername(authentication.getName()).getId();
        Note node = new Note(uid, title, text);
        noteRepository.save(node);
        return "redirect:/main/notes";
    }

    @GetMapping("/main/notes/{note_id}")
    public String NoteDetails(@PathVariable(value = "note_id") long id, Model model) {
        if (!noteRepository.existsById(id)) {
            return "redirect:/main/notes";
        }
        model.addAttribute("title", "Запись");

        Optional<Note> note = noteRepository.findById(id);
        ArrayList<Note> res = new ArrayList<>();
        note.ifPresent(res::add);
        model.addAttribute("note", res);
        return "TaskManager/NoteDetails";
    }

    @GetMapping("/main/notes/{note_id}/edit")
    public String EditNotes(@PathVariable(value = "note_id") long id, Model model) {
        if (!noteRepository.existsById(id)) {
            return "redirect:/main/notes";
        }
        model.addAttribute("title", "Редоктирование записи");

        Optional<Note> note = noteRepository.findById(id);
        ArrayList<Note> res = new ArrayList<>();
        note.ifPresent(res::add);
        model.addAttribute("note", res);
        return "TaskManager/NoteEdit";
    }

    @PostMapping("/main/notes/{note_id}/edit")
    public String EditNote(@PathVariable(value = "note_id") long id, @RequestParam String title, @RequestParam String text, Model model) {
        Note note = noteRepository.findById(id).orElseThrow();
        note.setTitle(title);
        note.setText(text);
        noteRepository.save(note);
        return "redirect:/main/notes";
    }

    @PostMapping("/main/notes/{note_id}/remove")
    public String RemoveNote(@PathVariable(value = "note_id") long id, Model model) {
        Note note = noteRepository.findById(id).orElseThrow();
        noteRepository.delete(note);
        return "redirect:/main/notes";
    }
}
