package com.puresushi.cse364project.data;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class BusTime {

    @Id
    private String id;
    private String busId;
    private int time;

    public BusTime(int time, String bus) {
        this.time = time;
        this.busId = bus;
    }
}
