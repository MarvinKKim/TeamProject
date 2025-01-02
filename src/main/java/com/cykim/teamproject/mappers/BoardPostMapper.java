package com.cykim.teamproject.mappers;

import com.cykim.teamproject.entities.BoardPostEntity;
import com.cykim.teamproject.vos.PageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardPostMapper {
    List<BoardPostEntity> selectPostsByUserEmail(@Param("userEmail") String userEmail, @Param("pageVo") PageVo pageVo);

    int countPostsByUserEmail(String userEmail);

//    김범수

    BoardPostEntity[] selectBoardPosts();

    int selectBoardPostCount();

    BoardPostEntity[] selectBoardPost(@Param(value = "limitCount") int limitCount,
                                       @Param(value = "offsetCount") int offsetCount);

    int selectBoardPostCountBySearch(@Param(value = "filter") String filter,
                                     @Param(value = "keyword") String keyword);

    BoardPostEntity[] selectBoardPostBySearch(@Param(value = "filter") String filter,
                                               @Param(value = "keyword") String keyword,
                                               @Param(value = "limitCount") int limitCount,
                                               @Param(value = "offsetCount") int offsetCount);

    BoardPostEntity selectBoardPostsByIndex(@Param("index") int index);

    int updateBoardPost(BoardPostEntity board);

    // 좋아요 추가
    int addLike(@Param("postId") int postId, @Param("userEmail") String userEmail);

    // 좋아요 삭제
    int removeLike(@Param("postId") int postId, @Param("userEmail") String userEmail);

    // 좋아요 수 증가
    int incrementLikeCount(@Param("postId") int postId);

    // 좋아요 수 감소
    int decrementLikeCount(@Param("postId") int postId);

    int getLikeCount(@Param("postId") int postId);

    boolean isLiked(@Param("postId") int postId, @Param("userEmail") String userEmail);
}