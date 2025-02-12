package com.noticias.notice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class NoticeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String image;
    private String category;
    private String description;
    private boolean recommended;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @JsonIgnore
    private UserEntity user;


    public NoticeEntity() {
    }

    public NoticeEntity(String title, String image, String category, String description, boolean recommended, UserEntity user) {
        this.title = title;
        this.image = image;
        this.category = category;
        this.description = description;
        this.recommended = recommended;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public NoticeEntity setId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public NoticeEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getImage() {
        return image;
    }

    public NoticeEntity setImage(String image) {
        this.image = image;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public NoticeEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public NoticeEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public NoticeEntity setCategory(String category) {
        this.category = category;
        return this;
    }

    public boolean isRecommended() {
        return recommended;
    }

    public NoticeEntity setRecommended(boolean recommended) {
        this.recommended = recommended;
        return this;
    }
}
