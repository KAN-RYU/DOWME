package com.puresushi.cse364project.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import com.puresushi.cse364project.domain.User;
import com.puresushi.cse364project.model.UserDetail;


public class UserItemProcessor implements ItemProcessor<UserDetail, User> {
    private static final Logger log = LoggerFactory.getLogger(UserItemProcessor.class);

    @Override
    public User process(UserDetail item) throws Exception {

        log.info("processing User data.....{}", item);

        User transformedUser = new User();
        transformedUser.setUserId(item.getuserId());
        transformedUser.setGender(item.getGender());
        transformedUser.setAge(item.getAge());
        transformedUser.setZipCode(item.getZipCode());
        return transformedUser;
    }
    
}
