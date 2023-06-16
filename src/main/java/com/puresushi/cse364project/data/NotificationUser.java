package com.puresushi.cse364project.data;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class NotificationUser {

    @Id
    private String id;
    private String fcmToken;

    public NotificationUser(String fcmToken) {
        this.fcmToken = fcmToken;
    }
}
