package com.puresushi.cse364project.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusTimeRepository extends MongoRepository<BusTime, String> {
}
