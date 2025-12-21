package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "visit_logs")
public class VisitLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "visitor_id", nullable = false)
    private Visitor visitor;

    @ManyToOne
    @JoinColumn(name = "host_id", nullable = false)
    private Host host;

    @Column(nullable = false)
    private LocalDateTime checkInTime;

    private LocalDateTime checkOutTime;

    private String purpose;

    @Column(nullable = false)
    private Boolean accessGranted = true;

    @Column(nullable = false)
    private Boolean alertSent = false;

    @OneToOne(mappedBy = "visitLog", cascade = CascadeType.ALL)
    private AlertNotification alertNotification;

    @PrePersist
    protected void onCreate() {
        if (checkInTime == null) {
            checkInTime = LocalDateTime.now();
        }
        if (accessGranted == null) {
            accessGranted = true;
        }
        if (alertSent == null) {
            alertSent = false;
        }
    }

    public VisitLog() {}

    public VisitLog(Visitor visitor, Host host, String purpose) {
        this.visitor = visitor;
        this.host = host;
        this.purpose = purpose;
        this.accessGranted = true;
        this.alertSent = false;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Visitor getVisitor() { return visitor; }
    public void setVisitor(Visitor visitor) { this.visitor = visitor; }

    public Host getHost() { return host; }
    public void setHost(Host host) { this.host = host; }

    public LocalDateTime getCheckInTime() { return checkInTime; }
    public void setCheckInTime(LocalDateTime checkInTime) { this.checkInTime = checkInTime; }

    public LocalDateTime getCheckOutTime() { return checkOutTime; }
    public void setCheckOutTime(LocalDateTime checkOutTime) { this.checkOutTime = checkOutTime; }

    public String getPurpose() { return purpose; }
    public void setPurpose(String purpose) { this.purpose = purpose; }

    public Boolean getAccessGranted() { return accessGranted; }
    public void setAccessGranted(Boolean accessGranted) { this.accessGranted = accessGranted; }

    public Boolean getAlertSent() { return alertSent; }
    public void setAlertSent(Boolean alertSent) { this.alertSent = alertSent; }

    public AlertNotification getAlertNotification() { return alertNotification; }
    public void setAlertNotification(AlertNotification alertNotification) { this.alertNotification = alertNotification; }
}