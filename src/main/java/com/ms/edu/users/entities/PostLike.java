package com.ms.edu.users.entities;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "posts_likes")
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long postId;
    private Long likedByUserId;
    private Date date;

    public PostLike() {
    }

    public PostLike(Long postId, Long likedByUserId, Date date) {
        this.postId = postId;
        this.likedByUserId = likedByUserId;
        this.date = date;
    }

    @Override
    public String toString() {
        return "PostLike{" +
                "id=" + id +
                ", postId=" + postId +
                ", likedByUserId=" + likedByUserId +
                ", date=" + date +
                '}';
    }
}
