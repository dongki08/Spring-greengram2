package com.green.greengram2.feed.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Builder
public class FeedCommentInsDto {
    private int ifeed;
    private int iuser;
    private String comment;
}
