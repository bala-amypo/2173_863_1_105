package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AlertNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private VisitLog visitLog;

    private String sentTo;
    private String alertMessage;
    private LocalDateTime sentAt;

    /* ===== GETTERS ===== */
    public Long getId() { return id; }

    public VisitLog getVisitLog() { return visitLog; }

    public String getSentTo() { return sentTo; }

    public String getAlertMessage() { return alertMessage; }

    public LocalDateTime getSentAt() { return sentAt; }

    /* ===== SETTERS (MISSING ONES) ===== */
    public void setId(Long id) { this.id = id; }

    public void setVisitLog(VisitLog visitLog) {
        this.visitLog = visitLog;
    }

    public void setSentTo(String sentTo) {
        this.sentTo = sentTo;
    }

    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }
}
