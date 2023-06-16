package com.puresushi.cse364project.data;

import com.puresushi.cse364project.common.firebase.FCMNotificationRequest;
import com.puresushi.cse364project.common.firebase.FCMService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class NotificationUserController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final FCMService fcmService;
    private final NotificationUserRepository notificationUserRepository;

    public NotificationUserController(NotificationUserRepository notificationUserRepository, FCMService fcmService) {
        this.notificationUserRepository = notificationUserRepository;
        this.fcmService = fcmService;
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody NotiData notiData) {
        log.info(notiData.getTimes().toString());
        final String token = notiData.getToken();
        NotificationUser user = notificationUserRepository.findByFcmToken(token);
        if(user == null) {
            user = new NotificationUser(token);
        }
        else {
            user.removeTime();
        }
        for (int i = 0; i < notiData.getTimes().size(); i++) {
            user.addTime(new NotiTime(notiData.getTimes().get(i),
                    notiData.getLectureNames().get(i)));
        }
        notificationUserRepository.save(user);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/unsubscribe")
    public ResponseEntity unsubscribe(@RequestBody String token) {
        notificationUserRepository.deleteByFcmToken(token);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/test")
    public ResponseEntity pushTest() {
        List<NotificationUser> tokenList = notificationUserRepository.findAll();
        for (NotificationUser notificationUser: tokenList) {
            FCMNotificationRequest fcmNotificationRequest = FCMNotificationRequest.builder()
                    .title("Test Push")
                    .token(notificationUser.getFcmToken())
                    .body("Test One")
                    .build();
            try {
                fcmService.send(fcmNotificationRequest);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/sendpush")
    public ResponseEntity sendNotification(@RequestParam int time) {
        log.info(String.valueOf(time));
        List<NotificationUser> userList = notificationUserRepository.findAll();
        for (NotificationUser notificationUser: userList) {
            List<NotiTime> timeTable = notificationUser.getTimeTable();
            for(NotiTime notiTime: timeTable) {
                int hour = notiTime.getTime()/100 - time/100;
                int minute = notiTime.getTime()%100 - time%100;
                if (hour * 60 + minute > 10) {
                    break;
                }
                if (notiTime.getTime() - time < 0) {
                    continue;
                }
                FCMNotificationRequest fcmNotificationRequest = FCMNotificationRequest.builder()
                        .title("Time to Check Attendance!")
                        .token(notificationUser.getFcmToken())
                        .body(notiTime.getLectureName())
                        .build();
                try {
                    fcmService.send(fcmNotificationRequest);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ResponseEntity.ok().build();
    }


}
