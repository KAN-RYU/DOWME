package com.puresushi.cse364project.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationUserRepository extends MongoRepository<NotificationUser, String> {

    NotificationUser findByFcmToken(String fcmToken);
    void deleteByFcmToken(String fcmToken);
}
