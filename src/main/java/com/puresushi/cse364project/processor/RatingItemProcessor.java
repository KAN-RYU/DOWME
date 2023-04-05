package com.puresushi.cse364project.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import com.puresushi.cse364project.domain.Rating;
import com.puresushi.cse364project.model.RatingDetail;


public class RatingItemProcessor implements ItemProcessor<RatingDetail, Rating> {
    private static final Logger log = LoggerFactory.getLogger(RatingItemProcessor.class);

    @Override
    public Rating process(RatingDetail item) throws Exception {

        log.info("processing Rating data.....{}", item);

        Rating transformedRating = new Rating();
        transformedRating.setUserId(item.getuserId());
        transformedRating.setMovieId(item.getmovieId());
        transformedRating.setRating(item.getRating());
        transformedRating.setTimeStamp(item.getTimeStamp());
        return transformedRating;
    }
    
}
