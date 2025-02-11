package com.noticias.notice.entity;

import com.noticias.notice.enums.RoleEnum;
import jakarta.persistence.*;

@Entity
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    public RoleEntity() {
    }

    public RoleEntity(RoleEnum role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public RoleEntity setId(int id) {
        this.id = id;
        return this;
    }

    public RoleEnum getRole() {
        return role;
    }

    public RoleEntity setRole(RoleEnum role) {
        this.role = role;
        return this;
    }
}
