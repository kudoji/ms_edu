package com.ms.edu.users.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String secondName;
    private String middleName;
    private String email;

    public User(String firstName, String secondName, String middleName, String email) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
