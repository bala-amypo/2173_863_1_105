package com.example.demo.controller;

import com.example.demo.entity.AlertNotification;
import com.example.demo.service.AlertNotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@Tag(name = "Alerts", description = "Alert notifications to hosts")
public class AlertNotificationController {
    
    private final AlertNotificationService alertNotificationService;

    public AlertNotificationController(AlertNotificationService alertNotificationService) {
        this.alertNotificationService = alertNotificationService;
    }

    @PostMapping("/send/{visitLogId}")
    @Operation(summary = "Send alert notification")
    public ResponseEntity<AlertNotification> sendAlert(@PathVariable Long visitLogId) {
        AlertNotification alert = alertNotificationService.sendAlert(visitLogId);
        return ResponseEntity.status(HttpStatus.CREATED).body(alert);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get alert by ID")
    public ResponseEntity<AlertNotification> getAlert(@PathVariable Long id) {
        AlertNotification alert = alertNotificationService.getAlert(id);
        return ResponseEntity.ok(alert);
    }

    @GetMapping
    @Operation(summary = "Get all alerts")
    public ResponseEntity<List<AlertNotification>> getAllAlerts() {
        List<AlertNotification> alerts = alertNotificationService.getAllAlerts();
        return ResponseEntity.ok(alerts);
    }
}