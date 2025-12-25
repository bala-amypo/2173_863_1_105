package com.example.demo.service.impl;

import com.example.demo.entity.VisitLog;
import com.example.demo.entity.Visitor;
import com.example.demo.entity.Host;
import com.example.demo.repository.VisitLogRepository;
import com.example.demo.repository.VisitorRepository;
import com.example.demo.repository.HostRepository;
import com.example.demo.service.VisitLogService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VisitLogServiceImpl implements VisitLogService {
    private VisitLogRepository visitLogRepository;
    private VisitorRepository visitorRepository;
    private HostRepository hostRepository;
    
    public VisitLogServiceImpl() {}
    
    @Override
    public VisitLog checkInVisitor(Long visitorId, Long hostId, String purpose) {
        Visitor visitor = visitorRepository.findById(visitorId)
            .orElseThrow(() -> new RuntimeException("Visitor not found"));
        Host host = hostRepository.findById(hostId)
            .orElseThrow(() -> new RuntimeException("Host not found"));
            
        VisitLog visitLog = new VisitLog();
        visitLog.setVisitor(visitor);
        visitLog.setHost(host);
        visitLog.setPurpose(purpose);
        visitLog.setCheckInTime(LocalDateTime.now());
        visitLog.setAccessGranted(true);
        visitLog.setAlertSent(false);
        
        return visitLogRepository.save(visitLog);
    }
    
    @Override
    public VisitLog checkOutVisitor(Long visitLogId) {
        VisitLog visitLog = visitLogRepository.findById(visitLogId)
            .orElseThrow(() -> new RuntimeException("VisitLog not found"));
            
        if (visitLog.getCheckInTime() == null) {
            throw new IllegalStateException("Visitor not checked in");
        }
        
        visitLog.setCheckOutTime(LocalDateTime.now());
        return visitLogRepository.save(visitLog);
    }
    
    @Override
    public List<VisitLog> getActiveVisits() {
        return visitLogRepository.findByCheckOutTimeIsNull();
    }
    
    @Override
    public VisitLog getVisitLog(Long id) {
        return visitLogRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("VisitLog not found"));
    }
    
    @Override
    public List<VisitLog> getVisitLogsByHost(Long hostId) {
        return visitLogRepository.findByHostId(hostId);
    }
    
    // Additional READ operations
    public List<VisitLog> getVisitLogsByVisitor(Long visitorId) {
        return visitLogRepository.findByVisitorId(visitorId);
    }
    
    public List<VisitLog> getAllVisitLogs() {
        return visitLogRepository.findAll();
    }
    
    // UPDATE operations
    public VisitLog updateVisitLog(Long id, VisitLog visitLogDetails) {
        VisitLog visitLog = getVisitLog(id);
        visitLog.setPurpose(visitLogDetails.getPurpose());
        visitLog.setAccessGranted(visitLogDetails.getAccessGranted());
        return visitLogRepository.save(visitLog);
    }
    
    // DELETE operations
    public void deleteVisitLog(Long id) {
        VisitLog visitLog = getVisitLog(id);
        visitLogRepository.delete(visitLog);
    }
}