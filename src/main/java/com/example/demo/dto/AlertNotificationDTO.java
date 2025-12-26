package com.example.demo.dto;

import java.time.LocalDateTime;

public class AlertNotificationDTO {

    private Long id;
    private Long visitLogId;
    private String sentTo;
    private String alertMessage;
    private LocalDateTime sentAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVisitLogId() {
        return visitLogId;
    }

    public void setVisitLogId(Long visitLogId) {
        this.visitLogId = visitLogId;
    }

    public String getSentTo() {
        return sentTo;
    }

    public void setSentTo(String sentTo) {
        this.sentTo = sentTo;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }
}
