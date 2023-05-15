package com.puresushi.cse364project.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceUserRepository extends MongoRepository<AttendanceUser, String> {
    AttendanceUser findByGuid(String guid);
    AttendanceUser findByUserId(Long userId);
}
