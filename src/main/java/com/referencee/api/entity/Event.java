package com.referencee.api.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Event {
    @Id
    private Integer id;
    private String userEmail;
    private String date;
    private Integer time;

    public Event() {}

    public Event(Integer id, String userEmail, String date, Integer time) {
        this.id = id;
        this.userEmail = userEmail;
        this.date = date;
        this.time = time;
    }
}
