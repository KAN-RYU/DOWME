package com.puresushi.cse364project.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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
        Collections.sort(result);

        return result;
    }

    // Get Timetable of input bus ID
    @GetMapping("/bus/times/{busId}")
    public BusData getBusData(@PathVariable String busId) {
        List<BusTime> busTimeList = busTimeRepository.findByBusId(busId);
        BusData result = new BusData(busId);
        for (BusTime busTime: busTimeList) {
            result.addTime(busTime.getTime());
        }
        return result;
    }

    // Get 10 Bus IDs depart soon
    @GetMapping("/bus/depart/{currentTime}")
    public List<BusTime> getDepartBus(@PathVariable int currentTime) {
        List<BusTime> busTimeSortedList = busTimeRepository.findByOrderByTimeAsc();
        List<BusTime> result = new ArrayList<>();
        int cnt = 0;
        for (BusTime busTime: busTimeSortedList) {
            if (busTime.getTime() > currentTime) {
                result.add(busTime);
                cnt++;
            }
            if (cnt >= 10) {
                break;
            }
        }

        return result;
    }



}
