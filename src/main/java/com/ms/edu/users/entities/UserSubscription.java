package com.ms.edu.users.entities;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "users_subscriptions")
public class UserSubscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private Long subscriberId;
    private Date date;

    public UserSubscription() {
    }

    public UserSubscription(Long userId, Long subscriberId, Date date) {
        this.userId = userId;
        this.subscriberId = subscriberId;
        this.date = date;
    }

    @Override
    public String toString() {
        return "UserSubscription{" +
                "id=" + id +
                ", userId=" + userId +
                ", subscriberId=" + subscriberId +
                ", date=" + date +
                '}';
    }
}
