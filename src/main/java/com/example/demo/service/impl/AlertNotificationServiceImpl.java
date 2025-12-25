package com.example.demo.service.impl;

import com.example.demo.entity.AlertNotification;
import com.example.demo.entity.VisitLog;
import com.example.demo.repository.AlertNotificationRepository;
import com.example.demo.repository.VisitLogRepository;
import com.example.demo.service.AlertNotificationService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlertNotificationServiceImpl implements AlertNotificationService {
    private AlertNotificationRepository alertRepository;
    private VisitLogRepository visitLogRepository;
    
    public AlertNotificationServiceImpl() {}
    

    public AlertNotification createAlert(AlertNotification alert) {
        alert.setSentAt(LocalDateTime.now());
        return alertRepository.save(alert);
    }
    
    @Override
    public AlertNotification sendAlert(Long visitLogId) {
        VisitLog visitLog = visitLogRepository.findById(visitLogId)
            .orElseThrow(() -> new RuntimeException("VisitLog not found"));
            
        if (alertRepository.findByVisitLogId(visitLogId).isPresent()) {
            throw new IllegalArgumentException("Alert already sent");
        }
        
        AlertNotification alert = new AlertNotification();
        alert.setVisitLog(visitLog);
        alert.setSentTo(visitLog.getHost().getEmail());
        alert.setAlertMessage("Visitor " + visitLog.getVisitor().getFullName() + " has checked in");
        alert.setSentAt(LocalDateTime.now());
        
        AlertNotification saved = alertRepository.save(alert);
        
        visitLog.setAlertSent(true);
        visitLogRepository.save(visitLog);
        
        return saved;
    }
    

    @Override
    public AlertNotification getAlert(Long id) {
        return alertRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Alert not found"));
    }
    
    @Override
    public List<AlertNotification> getAllAlerts() {
        return alertRepository.findAll();
    }
    
    public AlertNotification getAlertByVisitLogId(Long visitLogId) {
        return alertRepository.findByVisitLogId(visitLogId)
            .orElseThrow(() -> new RuntimeException("Alert not found for visit log"));
    }
    

    public AlertNotification updateAlert(Long id, AlertNotification alertDetails) {
        AlertNotification alert = getAlert(id);
        alert.setAlertMessage(alertDetails.getAlertMessage());
        alert.setSentTo(alertDetails.getSentTo());
        return alertRepository.save(alert);
    }
    

    public void deleteAlert(Long id) {
        AlertNotification alert = getAlert(id);
        alertRepository.delete(alert);
    }
    
    @Override
    public AlertNotification sendAlert(VisitLog visitLog) {
        return sendAlert(visitLog.getId());
    }
}