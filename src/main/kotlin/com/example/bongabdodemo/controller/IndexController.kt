package com.example.bongabdodemo.controller

import com.example.bongabdodemo.utils.BongabdoCalculation.getBanglaAcademyBongabdo
import com.example.bongabdodemo.utils.BongabdoCalculation.getCalendarFromString
import com.example.bongabdodemo.utils.BongabdoCalculation.getDateString
import com.example.bongabdodemo.utils.BongabdoCalculation.getDrikShiddhantaBongabdo
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@Controller
@RequestMapping(path = [""])
class IndexController {
    @GetMapping
    fun indexPage(model: Model): String {
        model.addAttribute("gregorianDate", getDateString(Calendar.getInstance()));
        return "index"
    }

    @PostMapping
    fun convert(@RequestParam("gregorianDate") gregorianDate: String, model: Model): String {
        model.addAttribute("gregorianDate", gregorianDate);

        val gregorianDateInCalendar = getCalendarFromString(gregorianDate)
        model.addAttribute("banglaAcademyOutput", getBanglaAcademyBongabdo(gregorianDateInCalendar));
        model.addAttribute("drikShiddhantaOutput", getDrikShiddhantaBongabdo(gregorianDateInCalendar));
        model.addAttribute("conversionAvailable", true);
        return "index";
    }
}