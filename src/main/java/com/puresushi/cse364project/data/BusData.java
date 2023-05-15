package com.puresushi.cse364project.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class BusData {
    private List<Integer> times = new ArrayList<>();
    private String busId;

    public BusData(String busId) {
        this.busId = busId;
    }

    public void addTime(int time) {
        this.times.add(time);
        Collections.sort(this.times);
    }
}
