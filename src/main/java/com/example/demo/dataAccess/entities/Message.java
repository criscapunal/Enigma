package com.example.demo.dataAccess.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;

    private String alien;

    private String type;

    private Boolean isValid;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public Message setId(long id) {
        this.id = id;
        return this;
    }

    public String getText() {
        return text;
    }

    public Message setText(String text) {
        this.text = text;
        return this;
    }

    public String getAlien() {
        return alien;
    }

    public Message setAlien(String alien) {
        this.alien = alien;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getValid() {
        return isValid;
    }

    public Message setValid(Boolean valid) {
        isValid = valid;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    @PrePersist
    private void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    @PreUpdate
    private void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }

}
