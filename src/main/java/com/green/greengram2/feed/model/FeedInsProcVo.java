package com.green.greengram2.feed.model;

import lombok.Data;

@Data
public class FeedInsProcVo {
    private int ifeed;
    private int iuser;
    private String contents;
    private String location;

    public FeedInsProcVo(FeedInsDto dto) {
        iuser = dto.getIuser();
        contents = dto.getContents();
        location = dto.getLocation();
    }
}
