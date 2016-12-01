package com.city.service;

import com.city.Model.CityEvent;
import com.city.dao.Dao;
import com.city.util.CampgroundConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;
import java.text.SimpleDateFormat;
import java.text.ParseException;

@Service
public class CityEventService {

    @Autowired
    private Dao dao;

    @Autowired
    private CampgroundConfig campgroundConfig;

    // city (region) -> date -> list of city events
    private Map<String, Map<String, List<CityEvent>>> cityToDateToEventMap = new HashMap<>();

    private static ObjectMapper objectMapper = new ObjectMapper();

    SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

    Calendar cal = Calendar.getInstance();

    // server load
    public void loadAllEventsIntoMemory() throws Exception {
        // build
        dao.getAllCityEvents().forEach(cityEvent -> {
            if(!cityToDateToEventMap.containsKey(cityEvent.getCityKey())) {
                cityToDateToEventMap.put(cityEvent.getCityKey(), new HashMap<>());
            }
            if(!cityToDateToEventMap.get(cityEvent.getCityKey()).containsKey(cityEvent.getDate())) {
                cityToDateToEventMap.get(cityEvent.getCityKey()).put(cityEvent.getDate(), new ArrayList<>());
            }
            cityToDateToEventMap.get(cityEvent.getCityKey()).get(cityEvent.getDate()).add(cityEvent);
        });

        List<Week> dates = dao.getWeekDates();
        IntStream.range(1, dates.size()).forEach(i -> {
            if(i >= 40) {
                String year = dates.get(i)
                        .getDate()
                        .substring(6);
                if(!weekToDateMap.containsKey(year)) {
                    weekToDateMap.put(year, new HashMap<>());
                }
                weekToDateMap.get(year).put(Integer.toString(i % 52), dates.get(i));
            }
        });
    }


    // sorted map of date -> list of events
    public Map<String, List<CityEvent>> getWeekEvents(String year, String weekNumber) {
        String date = getWeekDate(year, weekNumber);
        Map<String, List<CityEvent>> dateToEventSortedMap = new LinkedHashMap<>();

        try {
            cal.setTime(dateFormat.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 7; i++) {
            String nextDate = dateFormat.format(cal.getTime());
            if(cityToDateToEventMap.get("boise").containsKey(nextDate)) {
                String aday = cityToDateToEventMap.get("boise").get(nextDate).get(0).getDay();
                if(!dateToEventSortedMap.containsKey(nextDate)) {
                    dateToEventSortedMap.put(aday, new ArrayList<>());
                }
                cityToDateToEventMap.get("boise").get(nextDate).forEach(event -> {
                    dateToEventSortedMap.get(aday).add(event);
                });
            }
            cal.add(Calendar.DATE, 1);
        }
        return dateToEventSortedMap;
    }

    // test
    public String getDailyCityEventsJSON(String city, String date) {
        return buildJSON(cityToDateToEventMap.get(city).get(date));
    }

    private String buildJSON(List<CityEvent> cityEvents) {
        String jsonString;
        try {
            jsonString = objectMapper.writeValueAsString(cityEvents);
        } catch (IOException e) {
            jsonString = "fail";
        }
        return jsonString;
    }
}