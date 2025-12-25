package com.example.demo.service;

import com.example.demo.entity.AlertNotification;
import com.example.demo.entity.VisitLog;
import java.util.List;

public interface AlertNotificationService {
    AlertNotification sendAlert(VisitLog visitLog);
    AlertNotification sendAlert(Long visitLogId);
    AlertNotification getAlert(Long id);
    List<AlertNotification> getAllAlerts();
}