package com.green.greengram2.feed;

import com.green.greengram2.ResVo;
import com.green.greengram2.feed.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedMappers mappers;
    private final FeedPicsMappers picsMappers;
    private final FeedFavMappers feedFavMappers;
    private final FeedCommentMapper feedCommentMapper;


    public ResVo InsFeed(FeedInsDto dto) {
        if(dto.getPics().size() == 0) {
            return new ResVo(2);
        }
        FeedInsProcVo dto1 = new FeedInsProcVo(dto);

        int feedAffectedRows = mappers.InsFeed(dto1);
        if(feedAffectedRows == 0 || dto1.getIfeed() == 0) {
            return new ResVo(0);
        }
        FeedInsProcDto dto2 = new FeedInsProcDto(dto1.getIfeed(), dto.getPics());
        int feedPicsAffectedRows = mappers.insFeedPics(dto2);
        if(feedPicsAffectedRows != dto.getPics().size()) {
            return new ResVo(3);
        }
        return new ResVo(dto1.getIfeed());
    }

    public List<FeedSelVo> getFeedAll(FeedSelDto dto) {
        List<FeedSelVo> list = mappers.selFeedAll(dto);
        for(FeedSelVo vo : list) {
            List<String> pics = picsMappers.selFeedPicsAll(vo.getIfeed());
            vo.setPics(pics);

            List<FeedCommentSelVo> comments = feedCommentMapper.selCommentAll(FeedCommentSelDto.builder()
                    .ifeed(vo.getIfeed())
                    .startIdx(0)
                    .rowCount(4)
                    .build());

            if(comments.size() == 4) {
                vo.setIsMoreComment(1);
                comments.remove(comments.size() - 1);
            }
            vo.setComments(comments);
        }
        return list;
    }

    public ResVo toggleFav(FeedFavDto dto) {
        int result = feedFavMappers.delfeedfav(dto);
        if(result == 0) {
            feedFavMappers.insfeedfav(dto);
            return new ResVo(1);
        }
        return new ResVo(0);
    }

    public ResVo feedcomment(FeedCommentInsDto dto) {
        FeedCommentInsProcDto pDto = FeedCommentInsProcDto.builder()
                .ifeed(dto.getIfeed())
                .iuser(dto.getIuser())
                .comment(dto.getComment())
                .build();
        feedCommentMapper.feedcomment(pDto);
        return new ResVo(pDto.getIfeedComment());
    }

    public List<FeedCommentSelVo> getCommentAll(int ifeed) {
        return feedCommentMapper.selCommentAll(FeedCommentSelDto.builder()
                .ifeed(ifeed)
                .startIdx(4)
                .rowCount(9999)
                .build());
    }

    public ResVo delfeedcomment(FeedCommentDelDto dto) {
        int affectedRows = feedCommentMapper.delComment(dto);
        return new ResVo(affectedRows);
    }

    public ResVo delFeed(FeedDelDto dto) {
        // 셀렉트를 통해 DB에 저장된 값이 일치하는지 확인했습니다.
        Integer ifeed = mappers.selFeed(dto);
        // 0. 쓴 글이 아닐 때
        if(ifeed == null) {
            return new ResVo(0);
        }
        feedCommentMapper.delFeedcomment(dto);
        feedFavMappers.delFeedFav(dto);
        picsMappers.delFeedPics(dto);
        // 1. feed에 외래키가 걸려있기에 사진, 코맨트, 좋아요를 먼저 삭제
        mappers.delfeed(dto);
        // 2. 피드삭제

        return new ResVo(1);
    }
}
