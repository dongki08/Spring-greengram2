package com.green.greengram2.user;

import com.green.greengram2.ResVo;
import com.green.greengram2.user.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMappers mappers;

    //1 성공, 2 아디없음, 3 비번틀림
    public UserSigninVo userSignin(UserSigninDto dto) {
        UserSigninProcVo procVo = mappers.seluserbyid(dto.getUid());
        UserSigninVo vo = new UserSigninVo();
        if(procVo == null) {
            vo.setResult(2);
            return vo;
        }
        String savedPw = procVo.getUpw(); //DB에서 가져온 비밀번호
        boolean comparedPw = BCrypt.checkpw(dto.getUpw(), savedPw);
        vo.setResult(3);

        if(comparedPw) {
            vo.setResult(1);
            vo.setIuser(procVo.getIuser());
            vo.setNm(procVo.getNm());
            vo.setPic(procVo.getPic());
        }
        return vo;
    }

    public int signup(UserSignupDto dto){
        String hashedPw = BCrypt.hashpw(dto.getUpw(),BCrypt.gensalt());
        log.info("hashedPw : {}", hashedPw);

        UserInfoProcVo procVo = UserInfoProcVo.builder()
                .uid(dto.getUid())
                .upw(hashedPw)
                .nm(dto.getNm())
                .pic(dto.getPic())
                .build();
        int affectedRows = mappers.usersignup(procVo);
        if(affectedRows == 0 ){
            return 0;
        }

        return procVo.getIuser();
    }
    public UserInfoVo userinfovo(int targetIuser) {
        return mappers.userinfovo(targetIuser);
    }

    public ResVo userpatch(UserPatchPicDto dto) {
        int result = mappers.userpatch(dto);
        return new ResVo(result);
    }
}
