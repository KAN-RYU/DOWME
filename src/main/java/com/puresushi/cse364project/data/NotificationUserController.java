package com.puresushi.cse364project.data;

import com.puresushi.cse364project.common.firebase.FCMNotificationRequest;
import com.puresushi.cse364project.common.firebase.FCMService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity registerUser(@RequestBody String token) {
        if(notificationUserRepository.findByFcmToken(token) == null) {
            notificationUserRepository.save(new NotificationUser(token));
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity logout(@RequestBody String token) {

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


}
