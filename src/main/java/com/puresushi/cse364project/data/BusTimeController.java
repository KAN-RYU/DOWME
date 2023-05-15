package com.puresushi.cse364project.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class BusTimeController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final BusTimeRepository busTimeRepository;

    public BusTimeController(BusTimeRepository busTimeRepository) {this.busTimeRepository = busTimeRepository;}

    // Get all Bus ids
    @GetMapping("/bus")
    public List<String> getBusIds() {
        List<String> result = new ArrayList<>();
        List<BusTime> busTimeList = busTimeRepository.findAll();

        for (BusTime busTime: busTimeList) {
            result.add(busTime.getBusId());
        }

        Set<String> set = new HashSet<>(result);
        result.clear();
        result.addAll(set);

        return result;
    }

    // Get Timetable of input bus ID
    @GetMapping("/bus/{busId}")
    public BusData getBusData(@PathVariable String busId) {
        List<BusTime> busTimeList = busTimeRepository.findByBusId(busId);
        BusData result = new BusData(busId);
        for (BusTime busTime: busTimeList) {
            result.addTime(busTime.getTime());
        }
        return result;
    }



}
