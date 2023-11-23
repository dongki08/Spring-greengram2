package com.green.greengram2.feed;

import com.green.greengram2.feed.model.FeedDelDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Stack;

@Mapper
public interface FeedPicsMappers {
    List<String> selFeedPicsAll(int ifeed);

    int delFeedPics(FeedDelDto dto);
}
