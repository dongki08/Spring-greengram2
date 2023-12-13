package com.green.greengram2.feed;

import com.green.greengram2.ResVo;
import com.green.greengram2.feed.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.MissingFormatArgumentException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/feed")
@Tag(name = "피드", description = "피드 관련 처리")
public class FeedControllor {
    private final FeedService service;

    @Operation(summary = "피드 등록", description = "피드 등록 처리")
    @Parameters(value = {
            @Parameter(name="iuser", description = "작성자pk")
            , @Parameter(name="contents", description = "내용")
            , @Parameter(name="location", description = "위치")
            , @Parameter(name="pics", description = "사진")
    })
    @PostMapping
    public ResVo postFeed(@RequestBody FeedInsDto dto) {
        log.info("dto : {}", dto);
        return service.InsFeed(dto);
    }

    @GetMapping
    public List<FeedSelVo> getFeedAll(int page, int loginedIuser
            , @RequestParam(required = false, defaultValue = "0") int targetIuser) {
        log.info("targetIuser : {}", targetIuser);
        final int ROW_COUNT = 30;

        return service.getFeedAll(FeedSelDto.builder()
                .loginedIuser(loginedIuser)
                .targetIuser(targetIuser)
                .startIdx((page-1)*ROW_COUNT)
                .rowCount(ROW_COUNT)
                .build());
    }

    @DeleteMapping
    public ResVo delFeed(FeedDelDto dto) {
        log.info("dto : {}", dto);
        return service.delFeed(dto);
    }

    //insert : 1, delete : 0
    @GetMapping("/fav")
    @Operation(summary = "좋아요 처리", description = "Toggle로 처리함")
    @Parameters(value = {
            @Parameter(name="ifeed", description = "feed pk")
            , @Parameter(name="iuser", description = "로그인한 유저 pk")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "좋아요 처리 : result(1), 좋아요 취소 : result(2)")
    })
    public ResVo toggleFav(FeedFavDto dto) {
        log.info("dto : {}", dto);
        return service.toggleFav(dto);
    }

    @PostMapping("/comment")
    public ResVo feedcomment(@RequestBody FeedCommentInsDto dto) {
        return service.feedcomment(dto);
    }

    @GetMapping("/comment")
    public List<FeedCommentSelVo> getCommentAll(int ifeed) {
        return service.getCommentAll(ifeed);
    }

    @DeleteMapping("/comment")
    public ResVo delComment(@RequestParam("ifeed_comment") int ifeedComment
            , @RequestParam("logined_iuser") int loginedIuser) {
        log.info("ifeedComment : {}, loginedIuser : {}", ifeedComment, loginedIuser);
        FeedCommentDelDto dto1 = FeedCommentDelDto.builder()
                .ifeedComment(ifeedComment)
                .loginedIuser(loginedIuser)
                .build();
        return service.delfeedcomment(dto1);
    }
}
