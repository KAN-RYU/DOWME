package com.puresushi.cse364project.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusTimeRepository extends MongoRepository<BusTime, String> {
    List<BusTime> findByBusId(String busId);
    List<BusTime> findByOrderByTimeAsc();
    BusTime findByBusIdByTime(String busId, int time);
}
