package com.green.greengram2.user.model;


import lombok.Data;

@Data
public class UserSigninVo {
    private int result; //1: 성공, 2: 아이디없음, 3: 비밀번호 틀림
    private int iuser;
    private String nm;
    private String pic;
}
