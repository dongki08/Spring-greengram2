package com.green.greengram2.feed.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class FeedCommentDelDto {
    private int ifeedComment;
    private int loginedIuser;
}
