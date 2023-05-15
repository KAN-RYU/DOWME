package com.puresushi.cse364project.data;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class AttendanceUser {

    @Id
    private String id;
    private String guid;
    private Long userId;
    private List<Integer> attendanceList;

    public AttendanceUser(String guid, Long userId) {
        this.guid = guid;
        this.userId = userId;
        this.attendanceList = new ArrayList<>();
    }

    public void AddLecture(Integer lecture){
        attendanceList.add(lecture);
    }
}
