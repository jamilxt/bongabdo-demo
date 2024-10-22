package com.example.bongabdodemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;

import static com.example.bongabdodemo.utils.BongabdoCalculation.*;

@Controller
@RequestMapping("")
public class IndexController {
    @GetMapping
    public String indexPage(Model model) {
        model.addAttribute("gregorianDate", getDateString(Calendar.getInstance()));
        return "index";
    }

    @PostMapping
    public String convert(@RequestParam("gregorianDate") String gregorianDate, Model model) {
        model.addAttribute("gregorianDate", gregorianDate);

        var gregorianDateInCalendar = getCalendarFromString(gregorianDate);
        model.addAttribute("banglaAcademyOutput", getBanglaAcademyBongabdo(gregorianDateInCalendar));
        model.addAttribute("drikShiddhantaOutput", getDrikShiddhantaBongabdo(gregorianDateInCalendar));
        model.addAttribute("conversionAvailable", true);
        return "index";
    }
}
