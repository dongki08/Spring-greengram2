package com.green.greengram2.feed.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FeedInsProcDto {
    private int ifeed;
    private List<String> pics;


}
