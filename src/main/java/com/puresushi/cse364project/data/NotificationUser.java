package com.puresushi.cse364project.data;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Data
@Document
public class NotificationUser {

    @Id
    private String id;
    private String fcmToken;
    private List<NotiTime> timeTable;

    public NotificationUser(String fcmToken) {
        this.fcmToken = fcmToken;
        this.timeTable = new ArrayList<NotiTime>();
    }

    public void addTime(NotiTime newTime) {
        timeTable.add(newTime);
        Comparator<NotiTime> comparator = new Comparator<NotiTime>() {
            @Override
            public int compare(NotiTime o1, NotiTime o2) {
                return o1.getTime() - o2.getTime();
            }
        };
        Collections.sort(timeTable, comparator);
    }

    public void removeTime() {
        this.timeTable = new ArrayList<NotiTime>();
    }
}
