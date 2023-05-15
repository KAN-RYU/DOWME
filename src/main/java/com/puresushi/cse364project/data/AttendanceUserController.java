package com.puresushi.cse364project.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class AttendanceUserController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final AttendanceUserRepository attendanceUserRepository;

    public AttendanceUserController(AttendanceUserRepository attendanceUserRepository) {this.attendanceUserRepository = attendanceUserRepository;}

    // Add User
    @PostMapping("/attendance")
    public AttendanceUser newAttendanceUser(@RequestBody AttendanceUser newUser) {
        return attendanceUserRepository.save(newUser);
    }

    // Add Lecture
    @PostMapping("/attendance/{userId}/{lecture}")
    public AttendanceUser addLectureToUser(@PathVariable Long userId, @PathVariable int lecture) {
        AttendanceUser u = attendanceUserRepository.findByUserId(userId);
        u.AddLecture(lecture);
        return u;
    }

    // Get User
    @GetMapping("/attendance/{userId}")
    public AttendanceUser getUser(@PathVariable Long userId) {
        AttendanceUser u = attendanceUserRepository.findByUserId(userId);
        return u;
    }
}
