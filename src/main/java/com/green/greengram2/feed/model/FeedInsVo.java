package com.green.greengram2.feed.model;

import lombok.Data;

@Data
public class FeedInsVo {
    private int ifeed;
    private int iuser;
    private String contents;
    private String location;

    public FeedInsVo(FeedInsDto dto) {
        iuser = dto.getIuser();
        contents = dto.getContents();
        location = dto.getLocation();
    }
}
