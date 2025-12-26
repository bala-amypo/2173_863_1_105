package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class VisitLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Visitor visitor;

    @ManyToOne
    private Host host;

    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;

    private String purpose;
    private Boolean accessGranted;
    private Boolean alertSent = false;

    /* ===== GETTERS ===== */
    public Long getId() { return id; }

    public Visitor getVisitor() { return visitor; }

    public Host getHost() { return host; }

    public LocalDateTime getCheckInTime() { return checkInTime; }

    public LocalDateTime getCheckOutTime() { return checkOutTime; }

    public String getPurpose() { return purpose; }

    public Boolean getAccessGranted() { return accessGranted; }

    public Boolean getAlertSent() { return alertSent; }

    /* ===== SETTERS (INCLUDING MISSING ONE) ===== */
    public void setId(Long id) { this.id = id; }

    public void setVisitor(Visitor visitor) { this.visitor = visitor; }

    public void setHost(Host host) { this.host = host; }

    public void setCheckInTime(LocalDateTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public void setCheckOutTime(LocalDateTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public void setAccessGranted(Boolean accessGranted) {
        this.accessGranted = accessGranted;
    }

    public void setAlertSent(Boolean alertSent) {
        this.alertSent = alertSent;
    }
}
