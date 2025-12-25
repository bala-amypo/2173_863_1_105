package com.example.demo.controller;

import com.example.demo.entity.VisitLog;
import com.example.demo.service.VisitLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/visits")
public class VisitLogController {
    private final VisitLogService visitLogService;
    
    public VisitLogController(VisitLogService visitLogService) {
        this.visitLogService = visitLogService;
    }
    
    @PostMapping("/check-in")
    public ResponseEntity<VisitLog> checkIn(@RequestParam Long visitorId, 
                                          @RequestParam Long hostId, 
                                          @RequestParam String purpose) {
        VisitLog visitLog = visitLogService.checkInVisitor(visitorId, hostId, purpose);
        return ResponseEntity.ok(visitLog);
    }
    
    @PostMapping("/checkin/{visitorId}/{hostId}")
    public ResponseEntity<VisitLog> checkInVisitor(@PathVariable Long visitorId,
                                                 @PathVariable Long hostId,
                                                 @RequestBody String purpose) {
        VisitLog visitLog = visitLogService.checkInVisitor(visitorId, hostId, purpose);
        return ResponseEntity.ok(visitLog);
    }
    
    @PostMapping("/check-out/{visitorId}")
    public ResponseEntity<VisitLog> checkOut(@PathVariable Long visitorId) {
        VisitLog visitLog = visitLogService.checkOutVisitor(visitorId);
        return ResponseEntity.ok(visitLog);
    }
    
    @PostMapping("/checkout/{visitLogId}")
    public ResponseEntity<VisitLog> checkOutVisitor(@PathVariable Long visitLogId) {
        VisitLog visitLog = visitLogService.checkOutVisitor(visitLogId);
        return ResponseEntity.ok(visitLog);
    }
    
    @GetMapping("/active")
    public ResponseEntity<List<VisitLog>> getActiveVisits() {
        List<VisitLog> activeVisits = visitLogService.getActiveVisits();
        return ResponseEntity.ok(activeVisits);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<VisitLog> getVisitLog(@PathVariable Long id) {
        VisitLog visitLog = visitLogService.getVisitLog(id);
        return ResponseEntity.ok(visitLog);
    }
}