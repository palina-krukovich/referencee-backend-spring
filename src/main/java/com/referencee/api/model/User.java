package com.referencee.api.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String email;
    private Boolean admin = false;
    @OneToMany
    private List<Reference> references = new ArrayList<>();
    @OneToMany
    private List<DrawEvent> drawEvents = new ArrayList<>();

    public User() {}

    public User(String email, Boolean admin) {
        this.email = email;
        this.admin = admin;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public List<Reference> getReferences() {
        return references;
    }

    public List<DrawEvent> getDrawEvents() {
        return drawEvents;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public void setReferences(List<Reference> references) {
        this.references = references;
    }

    public void setDrawEvents(List<DrawEvent> drawEvents) {
        this.drawEvents = drawEvents;
    }

    @Override
    public String toString() {
        return String.format("%s {id: %d, email: %s, admin: %b, references: %s, drawEvents: %s}",
                getClass().getSimpleName(), id, email, admin, references, drawEvents);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (null == o || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(email, user.email) &&
                Objects.equals(admin, user.admin) &&
                Objects.equals(references, user.references) &&
                Objects.equals(drawEvents, user.drawEvents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, admin, references, drawEvents);
    }
}
