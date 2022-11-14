package com.MIREA.ToDo.controllers;

import com.MIREA.ToDo.entity.Day;
import com.MIREA.ToDo.entity.Pair;
import com.MIREA.ToDo.entity.Week;
import com.MIREA.ToDo.repository.DayRepository;
import com.MIREA.ToDo.repository.PairRepository;
import com.MIREA.ToDo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MainPageController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PairRepository pairRepository;

    @GetMapping("/main")
    public String MainPage(Model model) {
        model.addAttribute("title", "Главная страница");

        ArrayList<Week> schedule = new ArrayList<>();  // return from UserId.FindUser(uid)
        schedule.add(new Week("MATAN"));
        model.addAttribute("schedule", schedule);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String group = userRepository.findByUsername(authentication.getName()).getGroup();
        Optional<Pair> pairs = pairRepository.findAllByGroup(group);
        ArrayList<Pair> res = new ArrayList<>();
        pairs.ifPresent(res::add);
        //TODO: add attribute in model
//        model.addAttribute("pairs", res);

        return "TaskManager/MainPage";
    }
}