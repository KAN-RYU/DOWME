package com.puresushi.cse364project.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BusTimeController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final BusTimeRepository busTimeRepository;

    public BusTimeController(BusTimeRepository busTimeRepository) {this.busTimeRepository = busTimeRepository;}

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
