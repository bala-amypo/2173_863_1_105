package com.example.demo.controller;

import com.example.demo.entity.Visitor;
import com.example.demo.service.VisitorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/visitors")
public class VisitorController {
    private final VisitorService visitorService;
    
    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }
    
    @PostMapping
    public ResponseEntity<Visitor> createVisitor(@RequestBody Visitor visitor) {
        Visitor created = visitorService.registerVisitor(visitor);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping
    public ResponseEntity<List<Visitor>> getAllVisitors() {
        List<Visitor> visitors = visitorService.getAllVisitors();
        return ResponseEntity.ok(visitors);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Visitor> getVisitor(@PathVariable Long id) {
        Visitor visitor = visitorService.getVisitor(id);
        return ResponseEntity.ok(visitor);
    }
}