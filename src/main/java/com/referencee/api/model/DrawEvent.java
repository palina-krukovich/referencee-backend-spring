package com.referencee.api.model;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class DrawEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    private User user;
    private Date date = Date.valueOf(LocalDate.now());
    private Integer duration;
    private Boolean classMode;

    public DrawEvent() {}

    public DrawEvent(User user, Date date, Integer duration, Boolean classMode) {
        this.user = user;
        this.date = date;
        this.duration = duration;
        this.classMode = classMode;
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Date getDate() {
        return date;
    }

    public Integer getDuration() {
        return duration;
    }

    public Boolean getClassMode() {
        return classMode;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setClassMode(Boolean classMode) {
        this.classMode = classMode;
    }

    @Override
    public String toString() {
        return String.format("%s {id: %d, user: %s, date: %s, duration: %d, classMode: %b}",
                getClass().getSimpleName(), id, user, date, duration, classMode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (null == o || getClass() != o.getClass()) {
            return false;
        }
        DrawEvent drawEvent = (DrawEvent) o;
        return Objects.equals(id, drawEvent.id) &&
                Objects.equals(user, drawEvent.user) &&
                Objects.equals(date, drawEvent.date) &&
                Objects.equals(duration, drawEvent.duration) &&
                Objects.equals(classMode, drawEvent.classMode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, date, duration, classMode);
    }
}
