package com.puresushi.cse364project.data;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
public class NotiData {

    private String token;
    private List<Integer> times;
    private List<String> lectureNames;

    public NotiData(String token, List<Integer> times, List<String> lectureNames) {
        this.token = token;
        this.times = times;
        this.lectureNames = lectureNames;
    }
}
