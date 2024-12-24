package com.cykim.teamproject.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
//
//@Getter
//@Setter
//@EqualsAndHashCode(of = {"index"})
//public class CommentEntity {
//    private int index;
//    private int postId;
//    private Integer commentId;
//    private String comment;
//    private String userEmail;
//    private String userNickname;
//    private LocalDateTime createdAt;
//    private LocalDateTime updateAt;
//    private LocalDateTime isDeleted;
//}

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(schema = "fave", name = "board_comments")
@Getter
@Setter
public class BoardCommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`index`")
    private int index;

//    @ManyToOne(fetch = FetchType.LAZY)  // 게시글과 연관 관계 설정
//    @JoinColumn(name = "post_id", referencedColumnName = "index", nullable = false)  // post_id는 PostEntity의 index와 매핑
//    private BoardPostEntity post;  // PostEntity와의 관계


    @Column(name = "comment")
    private String comment;

    @Column(name = "user_email", length = 50)
    private String userEmail;

    @Column(name = "user_nickname", length = 10)
    private String userNickname;

    @Column(name = "create_at")
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_deleted")
    private LocalDateTime isDeleted;
}
