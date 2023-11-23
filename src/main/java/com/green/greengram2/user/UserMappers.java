package com.green.greengram2.user;

import com.green.greengram2.user.model.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMappers {
    int usersignup(UserInfoProcVo dto);

    UserSigninProcVo seluserbyid(String uid);

    UserInfoVo userinfovo(int targetIuser);

    int userpatch(UserPatchPicDto dto);
}
