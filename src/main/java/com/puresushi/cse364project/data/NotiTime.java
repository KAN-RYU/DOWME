package com.puresushi.cse364project.data;

import lombok.Data;

@Data
public class NotiTime {
    private int time;
    private String lectureName;

    public NotiTime(int time, String lectureName) {
        this.time = time;
        this.lectureName = lectureName;
    }
}
