package com.example.demo.controller;

import com.example.demo.entity.VisitLog;
import com.example.demo.service.VisitLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/visits")
@Tag(name = "Visit Logs", description = "Check-in and check-out operations")
public class VisitLogController {
    
    private final VisitLogService visitLogService;

    public VisitLogController(VisitLogService visitLogService) {
        this.visitLogService = visitLogService;
    }

    @PostMapping("/checkin/{visitorId}/{hostId}")
    @Operation(summary = "Check in visitor")
    public ResponseEntity<VisitLog> checkInVisitor(@PathVariable Long visitorId, 
        @PathVariable Long hostId,
    @RequestBody Map<String, String> request) {
        String purpose = request.get("purpose");
        VisitLog visitLog = visitLogService.checkInVisitor(visitorId, hostId, purpose);
        return ResponseEntity.status(HttpStatus.CREATED).body(visitLog);
    }

    @PostMapping("/checkout/{visitLogId}")
    @Operation(summary = "Check out visitor")
    public ResponseEntity<VisitLog> checkOutVisitor(@PathVariable Long visitLogId) {
        VisitLog visitLog = visitLogService.checkOutVisitor(visitLogId);
        return ResponseEntity.ok(visitLog);
    }

    @GetMapping("/active")
    @Operation(summary = "Get all active visits")
    public ResponseEntity<List<VisitLog>> getActiveVisits() {
        List<VisitLog> activeVisits = visitLogService.getActiveVisits();
        return ResponseEntity.ok(activeVisits);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get visit log by ID")
    public ResponseEntity<VisitLog> getVisitLog(@PathVariable Long id) {
        VisitLog visitLog = visitLogService.getVisitLog(id);
        return ResponseEntity.ok(visitLog);
    }
}