package com.example.demo.service;

import com.example.demo.entity.VisitLog;
import java.util.List;

public interface VisitLogService {
    VisitLog checkInVisitor(Long visitorId, Long hostId, String purpose);
    VisitLog checkOutVisitor(Long visitLogId);
    List<VisitLog> getVisitLogsByHost(Long hostId);
    List<VisitLog> getActiveVisits();
    VisitLog getVisitLog(Long id);
}