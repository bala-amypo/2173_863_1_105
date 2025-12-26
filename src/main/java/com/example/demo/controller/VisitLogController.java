package com.example.demo.controller;

import com.example.demo.model.VisitLog;
import com.example.demo.service.VisitLogService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/visits")
public class VisitLogController {

    private final VisitLogService visitLogService;

    public VisitLogController(VisitLogService visitLogService) {
        this.visitLogService = visitLogService;
    }

    @PostMapping("/checkin/{visitorId}/{hostId}")
    public VisitLog checkIn(
            @PathVariable Long visitorId,
            @PathVariable Long hostId,
            @RequestParam String purpose) {

        return visitLogService.checkInVisitor(visitorId, hostId, purpose);
    }

    @PostMapping("/checkout/{visitLogId}")
    public VisitLog checkOut(@PathVariable Long visitLogId) {
        return visitLogService.checkOutVisitor(visitLogId);
    }

    @GetMapping("/active")
    public List<VisitLog> active() {
        return visitLogService.getActiveVisits();
    }

    @GetMapping("/{id}")
    public VisitLog get(@PathVariable Long id) {
        return visitLogService.getVisitLog(id);
    }
}
