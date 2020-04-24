package com.referencee.api.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Reference {
    @Id
    private Integer id;
    private String userEmail;
    private Boolean approved;
    private String url;
    private String gcsPath;
    private String gender;
    private String clothing;
    private String pose;

    public Reference() {}

    public Reference(Integer id, String userEmail, Boolean approved, String url,
                     String gcsPath, String gender, String clothing, String pose) {
        this.id = id;
        this.userEmail = userEmail;
        this.approved = approved;
        this.url = url;
        this.gcsPath = gcsPath;
        this.gender = gender;
        this.clothing = clothing;
        this.pose = pose;
    }
}
