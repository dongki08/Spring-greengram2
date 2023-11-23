package com.green.greengram2.feed;

import com.green.greengram2.feed.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedMappers {
    int InsFeed(FeedInsVo dto);

    int insFeedPics(FeedInsProcDto dto);

    List<FeedSelVo> selFeedAll(FeedSelDto dto);

    int selFeed(FeedDelDto dto);

    int delfeed(FeedDelDto dto);
}
