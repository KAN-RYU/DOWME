package com.puresushi.cse364project.common.firebase;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FCMNotificationRequest {
    private String token;
    private String title;
    private String body;

    @Builder
    public FCMNotificationRequest(String token, String title, String body) {
        this.token = token;
        this.title = title;
        this.body = body;
    }
}
