package com.referencee.api.model;

import com.referencee.api.model.enumeration.Clothing;
import com.referencee.api.model.enumeration.Gender;
import com.referencee.api.model.enumeration.Pose;
import javax.persistence.*;
import java.util.Objects;

@Entity
public class Reference {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    private User user;
    private Boolean approved;
    private String url;
    private String gcsPath;
    private Gender gender;
    private Clothing clothing;
    private Pose pose;

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Boolean getApproved() {
        return approved;
    }

    public String getUrl() {
        return url;
    }

    public String getGcsPath() {
        return gcsPath;
    }

    public Gender getGender() {
        return gender;
    }

    public Clothing getClothing() {
        return clothing;
    }

    public Pose getPose() {
        return pose;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setGcsPath(String gcsPath) {
        this.gcsPath = gcsPath;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setClothing(Clothing clothing) {
        this.clothing = clothing;
    }

    public void setPose(Pose pose) {
        this.pose = pose;
    }

    @Override
    public String toString() {
        return String.format("%s {id: %d, user: %s, approved: %b, url: %s, gcsPath: %s, gender: %s, clothing: %s, pose: %s}",
                getClass().getSimpleName(), id, user, approved, url, gcsPath, gender, clothing, pose);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (null == o || getClass() != o.getClass()) {
            return false;
        }
        Reference reference = (Reference) o;
        return Objects.equals(id, reference.id) &&
                Objects.equals(user, reference.user) &&
                Objects.equals(approved, reference.approved) &&
                Objects.equals(url, reference.url) &&
                Objects.equals(gcsPath, reference.gcsPath) &&
                Objects.equals(gender, reference.gender) &&
                Objects.equals(clothing, reference.clothing) &&
                Objects.equals(pose, reference.pose);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, approved, url, gcsPath, gender, clothing, pose);
    }
}

