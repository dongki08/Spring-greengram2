package com.green.greengram2.user;

import com.green.greengram2.ResVo;
import com.green.greengram2.user.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Tag(name="유저 API", description = "인증 관련")
public class UserControllor {
    private final UserService service;

    @Operation(summary = "인증", description = "아이디/비번을 활용한 인증처리")
    @Parameters(value = {
            @Parameter(name="uid", description = "아이디"),
            @Parameter(name="upw", description = "비밀번호")
    })
    @PostMapping("/signin")
    public UserSigninVo postuserSignin(@RequestBody UserSigninDto dto){
        log.info("dto : {}", dto);
        return service.userSignin(dto);
    }
    // /api/user/signin
    // "uid" : "ddd",
    // "upw" : "aaa"

    @Operation(summary = "회원가입", description = "회원가입 처리")
    @PostMapping("/signup")
    @Parameters(value = {
            @Parameter(name="uid", description = "아이디"),
            @Parameter(name="upw", description = "비밀번호"),
            @Parameter(name="nm", description = "이름"),
            @Parameter(name="pic", description = "프로필사진")
    })
    public ResVo postUserSignup(@RequestBody UserSignupDto dto){
        log.info("dto: {}",dto);
        int result = service.signup(dto);

        return new ResVo(result);
    }
    // /api/user/signup
    //  "uid" : "ddd",
    //  "upw" : "aaa",
    //  "nm" : "동현",
    //  "pic" : "dasd"


    @GetMapping
    @Operation(summary = "유저 정보", description = "프로필 화면에서 사용할 프로필")
    public UserInfoVo getUserInfo(@RequestParam("target_iuser") int targetIuser) {
        log.info("targetIuser : {}", targetIuser);
        return service.userinfovo(targetIuser);
    }
    ///api/user?target_iuser=3

    //수정 성공 result : 1, 수정 실패 result : 0
    @PatchMapping("/pic")
    public ResVo patchUserProfile(@RequestBody UserPatchPicDto dto) {
        return service.userpatch(dto);
    }

    //  /api/user/pic
    //  "iuser" : 3,
    //  "pic" : "dasff"
}