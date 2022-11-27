package com.MIREA.ToDo.controllers;

import com.MIREA.ToDo.entity.Pair;
import com.MIREA.ToDo.repository.PairRepository;
import com.MIREA.ToDo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Controller
public class MainPageController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PairRepository pairRepository;

    @GetMapping("/main")
    public String MainPage(Model model) {
        model.addAttribute("title", "Главная страница");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String group = userRepository.findByUsername(authentication.getName()).getGroup();
        List<Pair> pairs = pairRepository.findAllByStudygr(group);
        ArrayList<Pair> res = (ArrayList<Pair>) pairs;

        ArrayList<Pair> monday = new ArrayList<>();
        ArrayList<Pair> tuesday = new ArrayList<>();
        ArrayList<Pair> wednesday = new ArrayList<>();
        ArrayList<Pair> thursday = new ArrayList<>();
        ArrayList<Pair> friday = new ArrayList<>();
        ArrayList<Pair> saturday = new ArrayList<>();
        int week = GetWeek();
        for (Pair pair : res) {
            if (pair.getParity() == week) {
                switch (pair.getWeekday()) {
                    case 0:
                        monday.add(pair);
                        break;
                    case 1:
                        tuesday.add(pair);
                        break;
                    case 2:
                        wednesday.add(pair);
                        break;
                    case 3:
                        thursday.add(pair);
                        break;
                    case 4:
                        friday.add(pair);
                        break;
                    case 5:
                        saturday.add(pair);
                        break;
                }
            }
        }
        monday.sort(Pair::compareTo);
        tuesday.sort(Pair::compareTo);
        wednesday.sort(Pair::compareTo);
        thursday.sort(Pair::compareTo);
        friday.sort(Pair::compareTo);
        saturday.sort(Pair::compareTo);

        model.addAttribute("monday", monday);
        model.addAttribute("tuesday", tuesday);
        model.addAttribute("wednesday", wednesday);
        model.addAttribute("thursday", thursday);
        model.addAttribute("friday", friday);
        model.addAttribute("saturday", saturday);


        return "TaskManager/MainPage";
    }

    private int GetWeek() {
        Calendar start_ed;
        Calendar now = Calendar.getInstance();
        int cur_month = now.get(Calendar.MONTH);
        if (cur_month >= 1 && cur_month <= 7) {  // TODO: when does the academic year of the second semester begin
            start_ed = new GregorianCalendar(Calendar.YEAR, Calendar.FEBRUARY, 1);
        } else {
            start_ed = new GregorianCalendar(Calendar.YEAR, Calendar.SEPTEMBER, 1);
        }
        return (now.get(Calendar.WEEK_OF_YEAR) - start_ed.get(Calendar.WEEK_OF_YEAR)) % 2;
    }
}