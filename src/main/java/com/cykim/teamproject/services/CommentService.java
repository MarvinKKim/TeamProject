package com.cykim.teamproject.services;

import com.cykim.teamproject.entities.CommentEntity;
import com.cykim.teamproject.mappers.CommentMapper;
import com.cykim.teamproject.results.article.ArticleResult;
import com.cykim.teamproject.results.comment.DeleteCommentResult;
import com.cykim.teamproject.results.comment.ModifyCommentResult;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService {
    private final CommentMapper commentMapper;

    public CommentService(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }


    /// ///////////////////////////////

    public ModifyCommentResult modifyComment(int index, String content) {
        if (index < 1 || content == null || content.isEmpty() || content.length() > 100) {
            return ModifyCommentResult.FAILURE;
        }
        CommentEntity comment = this.commentMapper.selectCommentByIndex(index);
        if (comment == null || comment.getIsDeleted() != null) {
            return ModifyCommentResult.FAILURE;
        }
        comment.setComment(content);
        comment.setUpdateAt(LocalDateTime.now());
        return this.commentMapper.updateComment(comment) > 0
                ? ModifyCommentResult.SUCCESS
                : ModifyCommentResult.FAILURE;
    }


    /// //////////////////////////////////////////////////////////////

    // 댓글 삭제 기능
    public DeleteCommentResult deleteComment(int index) {
        if (index < 1) {
            return DeleteCommentResult.FAILURE; // 유효하지 않은 index
        }
        CommentEntity comment = this.commentMapper.selectCommentByIndex(index);
        if (comment == null) {
            return DeleteCommentResult.FAILURE; // 댓글이 없음
        }
        if (comment.getIsDeleted() != null) {
            return DeleteCommentResult.FAILURE; // 이미 삭제된 댓글
        }
        comment.setIsDeleted(LocalDateTime.now()); // 삭제 시간 설정
        return this.commentMapper.updateComment(comment) > 0
                ? DeleteCommentResult.SUCCESS // 성공적으로 업데이트됨
                : DeleteCommentResult.FAILURE; // 업데이트 실패
    }

    // 댓글 불러오기 기능
    public CommentEntity[] getCommentsByPostId(int articleIndex) {
        if (articleIndex < 1) {
            return new CommentEntity[0];
        }
        CommentEntity[] commentEntities = this.commentMapper.selectCommentsByPostId(articleIndex);
        if (commentEntities == null || commentEntities.length == 0) {
            return new CommentEntity[0];
        }
        return commentEntities;
    }

    // 댓글 작성기능
    public ArticleResult writeComment(CommentEntity comment) {
        if (comment == null ||
                comment.getComment() == null || comment.getComment().isEmpty() || comment.getComment().length() > 1000) {
            System.out.println(comment.getComment());
            return ArticleResult.FAILURE;
        }
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdateAt(null);
        comment.setIsDeleted(null);
        comment.setUserEmail("yellow6480@gmail.com");
        comment.setUserNickname("관리자");
        return commentMapper.insertComment(comment) > 0 ? ArticleResult.SUCCESS : ArticleResult.FAILURE;
    }

    /// ////////////////////////////////////
    public ArticleResult saveReplyComment(int parentCommentId, String content) {
        if (parentCommentId < 1 || content == null || content.isEmpty() || content.length() > 100) {
            return ArticleResult.FAILURE;
        }
        CommentEntity parentComment = this.commentMapper.selectCommentByIndex(parentCommentId);
        if (parentComment == null || parentComment.getIsDeleted() != null) {
            return ArticleResult.FAILURE;
        }
        CommentEntity replyComment = new CommentEntity();
        replyComment.setPostId(parentComment.getPostId());
        replyComment.setCommentId(parentCommentId);
        replyComment.setComment(content);
        replyComment.setCreatedAt(LocalDateTime.now());
        replyComment.setUpdateAt(null);
        replyComment.setIsDeleted(null);
        replyComment.setUserEmail("yellow6480@gmail.com");
        replyComment.setUserNickname("관리자");
        return this.commentMapper.insertComment(replyComment) > 0 ? ArticleResult.SUCCESS : ArticleResult.FAILURE;
    }

    public CommentEntity[] getRepliesByParentId(int parentCommentId) {
        if (parentCommentId < 1) {
            return new CommentEntity[0];
        }
        return this.commentMapper.selectRepliesByParentId(parentCommentId);
    }
}
