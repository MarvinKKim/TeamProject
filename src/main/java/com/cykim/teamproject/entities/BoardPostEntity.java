package com.cykim.teamproject.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Getter
@Setter
public class BoardPostEntity {
    @Id
    @GeneratedValue
    private Long index;
    private String title;
    private String content;
    private String userEmail;
    private String userNickname;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime deletedAt;
    private int view;

//    private UserEntity user;
}
