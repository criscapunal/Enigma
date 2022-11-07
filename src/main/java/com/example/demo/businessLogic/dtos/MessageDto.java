package com.example.demo.businessLogic.dtos;

import com.example.demo.dataAccess.entities.Message;

import java.time.LocalDateTime;

public class MessageDto {
    private Long id;

    private String text;

    private String alien;

    private Boolean isValid;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public MessageDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getText() {
        return text;
    }

    public MessageDto setText(String text) {
        this.text = text;
        return this;
    }

    public String getAlien() {
        return alien;
    }

    public MessageDto setAlien(String alien) {
        this.alien = alien;
        return this;
    }

    public Boolean getValid() {
        return isValid;
    }

    public MessageDto setValid(Boolean valid) {
        isValid = valid;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public MessageDto setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public MessageDto setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public static MessageDto mapper(Message message) {
        return (new MessageDto())
                .setId(message.getId())
                .setAlien(message.getAlien())
                .setText(message.getText())
                .setCreatedAt(message.getCreatedAt())
                .setUpdatedAt(message.getUpdatedAt());
    }
}
