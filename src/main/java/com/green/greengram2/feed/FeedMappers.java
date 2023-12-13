package com.green.greengram2.feed;

import com.green.greengram2.feed.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedMappers {
    int InsFeed(FeedInsProcVo dto);

    int insFeedPics(FeedInsProcDto dto);

    List<FeedSelVo> selFeedAll(FeedSelDto dto);

    Integer selFeed(FeedDelDto dto);

    int delfeed(FeedDelDto dto);
}
