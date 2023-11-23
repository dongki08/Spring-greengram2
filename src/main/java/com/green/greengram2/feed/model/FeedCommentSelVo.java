package com.green.greengram2.feed.model;

import lombok.Data;

@Data
public class FeedCommentSelVo {
    private int ifeedComment;
    private String Comment;
    private String createdAt;
    private int writerIuser;
    private String writerNm;
    private String writerPic;
}
