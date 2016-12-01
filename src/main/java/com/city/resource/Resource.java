package com.city.resource;

import com.city.service.CityEventService;
import com.city.util.CampgroundConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class Resource {

    @Autowired
    private CityEventService cityEventService;

    @Autowired
    private CampgroundConfig campgroundConfig;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getCityEventToday(Model model) {
     /*   String date = "11-04-2016"; //getCurrentDate();
        model.addAttribute("date", date);
        model.addAttribute("weeksEvents", cityEventService.getWeekAheadEvents(date));
        model.addAttribute("cityActivityList", cityEventService.getDaysActivities(date));

        //   model.addAttribute("weeksActivities", cityEventService.getWeekAheadActivities(date));
        model.addAttribute("prevLink", cityEventService.getPrevDateLink(date));
        model.addAttribute("nextLink", cityEventService.getNextDateLink(date));
        */
        return "city";
    }

    @RequestMapping(value = "{year}/{week_number}", method = RequestMethod.GET)
    public String getCityEventDate(@PathVariable("year") String year, @PathVariable("week_number") String weekNumber, Model model) {
        model.addAttribute("days", cityEventService.getWeekDays(year, weekNumber));
        model.addAttribute("weeksEvents", cityEventService.getWeekEvents(year, weekNumber));
        model.addAttribute("weekActivities", cityEventService.getWeekActivities(year, weekNumber));
        model.addAttribute("prevLink", year + "/" + (Integer.parseInt(weekNumber) - 1));//cityEventService.getPrevDateLink(year, weekNumber));
        model.addAttribute("nextLink",  year + "/" + (Integer.parseInt(weekNumber) + 1));//cityEventService.getNextDateLink(year, weekNumber));
        return "city";
    }

    private String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }
}