package com.resappgcp.restgcp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

//Java POJO class
@Entity
@Table(name="session")
public class Session {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="id")
//    private int id;

    // define fields
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist(){
        //this.id = UUID.randomUUID();
        createdAt = LocalDateTime.now();
    }

    @Column(name = "mood_summary")
    private String moodSummary;

    @Column(name = "dominant_emotion")
    private String dominantEmotion;

    @Column(name = "marking_status")
    private String markingStatus;

    public Session() {}

    public Session(String moodSummary,
                   String dominantEmotion,
                   String markingStatus) {
        this.moodSummary = moodSummary;
        this.dominantEmotion = dominantEmotion;
        this.markingStatus = markingStatus;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getMoodSummary() {
        return moodSummary;
    }

    public void setMoodSummary(String moodSummary) {
        this.moodSummary = moodSummary;
    }

    public String getDominantEmotion() {
        return dominantEmotion;
    }

    public void setDominantEmotion(String dominantEmotion) {
        this.dominantEmotion = dominantEmotion;
    }

    public String getMarkingStatus() {
        return markingStatus;
    }

    public void setMarkingStatus(String markingStatus) {
        this.markingStatus = markingStatus;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", moodSummary='" + moodSummary + '\'' +
                ", dominantEmotion='" + dominantEmotion + '\'' +
                ", markingStatus='" + markingStatus + '\'' +
                '}';
    }
}