package com.puresushi.cse364project.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealMenuRepository extends MongoRepository<MealMenu, String> {
}
