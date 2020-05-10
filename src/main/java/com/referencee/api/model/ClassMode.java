package com.referencee.api.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class ClassMode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer totalTime;
    @ManyToMany
    private List<Duration> durations = new ArrayList<>();

    public ClassMode() {}

    public ClassMode(Integer totalTime, List<Duration> durations) {
        this.totalTime = totalTime;
        this.durations = durations;
    }

    public Integer getId() {
        return id;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    public List<Duration> getDurations() {
        return durations;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    public void setDurations(List<Duration> durations) {
        this.durations = durations;
    }

    @Override
    public String toString() {
        return String.format("%s {id: %d, totalTime: %d, durations: %s}",
                getClass().getSimpleName(), id, totalTime, durations);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (null == o || getClass() != o.getClass()) {
            return false;
        }
        ClassMode classMode = (ClassMode) o;
        return Objects.equals(id, classMode.id) &&
                Objects.equals(totalTime, classMode.totalTime) &&
                Objects.equals(durations, classMode.durations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalTime, durations);
    }
}
