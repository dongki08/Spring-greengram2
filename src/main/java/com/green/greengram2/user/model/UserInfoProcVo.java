package com.green.greengram2.user.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoProcVo {
    private int iuser;
    private String uid;
    private String upw;
    private String nm;
    private String pic;

}
