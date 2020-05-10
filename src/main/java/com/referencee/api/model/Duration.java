package com.referencee.api.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Duration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer duration;
    @ManyToMany
    private List<ClassMode> classModes = new ArrayList<>();
    public Duration() {}

    public Duration(Integer duration) {
        this.duration = duration;
    }

    public Integer getId() {
        return id;
    }

    public Integer getDuration() {
        return duration;
    }

    public List<ClassMode> getClassModes() {
        return classModes;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setClassModes(List<ClassMode> classModes) {
        this.classModes = classModes;
    }

    @Override
    public String toString() {
        return String.format("%s {id: %d, duration: %d, classModes: %s}",
                getClass().getSimpleName(), id, duration, classModes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (null == o || getClass() != o.getClass()) {
            return false;
        }
        Duration duration = (Duration) o;
        return Objects.equals(id, duration.id) &&
                Objects.equals(duration, duration.duration) &&
                Objects.equals(classModes, duration.classModes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, duration, classModes);
    }
}
