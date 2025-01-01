package com.cykim.teamproject.services;

import com.cykim.teamproject.entities.BoardPostEntity;
import com.cykim.teamproject.mappers.BoardPostMapper;
import com.cykim.teamproject.vos.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardPostService {
    private final BoardPostMapper boardPostMapper;

    @Autowired
    public BoardPostService(BoardPostMapper boardPostMapper) {
        this.boardPostMapper = boardPostMapper;
    }

//    // 좋아요 수 증가
//    public boolean incrementLike(int postId) {
//        int updatedRows = boardPostMapper.incrementLikeCount(postId);
//        return updatedRows > 0; // 업데이트 성공 여부 반환
//    }
//
//    // 좋아요 수 감소 (추가 요구 시 사용)
//    public boolean decrementLike(int postId) {
//        int updatedRows = boardPostMapper.decrementLikeCount(postId);
//        return updatedRows > 0;
//    }
//
//    // 게시물 조회
//    public BoardPostEntity getPostById(int postId) {
//        return boardPostMapper.selectPostById(postId);
//    }

    // 로그인된 사용자의 이메일을 가져오는 메서드
    private String getLoggedInUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName(); // 로그인된 사용자의 이메일
    }

    @Transactional
    public boolean addLike(int postId) {
        // 로그인된 사용자의 이메일을 가져옴
        String userEmail = getLoggedInUserEmail();

        int insertedRows = boardPostMapper.addLike(postId, userEmail);
        if (insertedRows > 0) {
            boardPostMapper.incrementLikeCount(postId);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean removeLike(int postId) {
        // 로그인된 사용자의 이메일을 가져옴
        String userEmail = getLoggedInUserEmail();

        int deletedRows = boardPostMapper.removeLike(postId, userEmail);
        if (deletedRows > 0) {
            boardPostMapper.decrementLikeCount(postId);
            return true;
        }
        return false;
    }

    public List<BoardPostEntity> getPostsByUserEmail(String userEmail, PageVo pageVo) {
        return boardPostMapper.selectPostsByUserEmail(userEmail, pageVo);
    }

    public int countPostsByUserEmail(String userEmail) {
        return boardPostMapper.countPostsByUserEmail(userEmail);
    }
}