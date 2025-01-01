package com.cykim.teamproject.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "fave", name = "board_likes")
@Getter
@Setter

public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`index`")
    private int index;

    @Column(nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private int boardId;
}
