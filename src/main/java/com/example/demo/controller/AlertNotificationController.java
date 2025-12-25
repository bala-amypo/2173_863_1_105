package com.example.demo.controller;

import com.example.demo.entity.AlertNotification;
import com.example.demo.service.AlertNotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/alerts")
public class AlertNotificationController {
    private final AlertNotificationService alertService;
    
    public AlertNotificationController(AlertNotificationService alertService) {
        this.alertService = alertService;
    }
    
    @PostMapping("/send/{visitLogId}")
    public ResponseEntity<AlertNotification> sendAlert(@PathVariable Long visitLogId) {
        AlertNotification alert = alertService.sendAlert(visitLogId);
        return ResponseEntity.ok(alert);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AlertNotification> getAlert(@PathVariable Long id) {
        AlertNotification alert = alertService.getAlert(id);
        return ResponseEntity.ok(alert);
    }
    
    @GetMapping
    public ResponseEntity<List<AlertNotification>> getAllAlerts() {
        List<AlertNotification> alerts = alertService.getAllAlerts();
        return ResponseEntity.ok(alerts);
    }
}