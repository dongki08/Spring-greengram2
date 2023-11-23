package com.green.greengram2.feed.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class FeedCommentInsProcDto {
    private int ifeedComment;
    private int ifeed;
    private int iuser;
    private String comment;
}
