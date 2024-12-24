package com.cykim.teamproject.services;

import com.cykim.teamproject.entities.BoardPostEntity;
import com.cykim.teamproject.mappers.BoardPostMapper;
import com.cykim.teamproject.vos.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardPostService {
    private final BoardPostMapper boardPostMapper;

    @Autowired
    public BoardPostService(BoardPostMapper boardPostMapper) {
        this.boardPostMapper = boardPostMapper;
    }

    public List<BoardPostEntity> getPostsByUserEmail(String userEmail, PageVo pageVo) {
        return boardPostMapper.selectPostsByUserEmail(userEmail, pageVo);
    }

    public int countPostsByUserEmail(String userEmail) {
        return boardPostMapper.countPostsByUserEmail(userEmail);
    }
}