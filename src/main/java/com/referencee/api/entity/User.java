package com.referencee.api.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class User {
    @Id
    private String email;
    private Boolean admin;

    public User() {}

    public User(String email, Boolean admin) {
        this.email = email;
        this.admin = admin;
    }
}
