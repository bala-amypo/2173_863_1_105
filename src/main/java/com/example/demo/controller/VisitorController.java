package com.example.demo.controller;

import com.example.demo.entity.Visitor;
import com.example.demo.service.VisitorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/visitors")
@Tag(name = "Visitors", description = "Visitor management endpoints")
public class VisitorController {
    
    private final VisitorService visitorService;

    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @PostMapping
    @Operation(summary = "Create a new visitor")
    public ResponseEntity<Visitor> createVisitor(@RequestBody Visitor visitor) {
        Visitor createdVisitor = visitorService.createVisitor(visitor);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVisitor);
    }

    @GetMapping
    @Operation(summary = "Get all visitors")
    public ResponseEntity<List<Visitor>> getAllVisitors() {
        List<Visitor> visitors = visitorService.getAllVisitors();
        return ResponseEntity.ok(visitors);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get visitor by ID")
    public ResponseEntity<Visitor> getVisitor(@PathVariable Long id) {
        Visitor visitor = visitorService.getVisitor(id);
        return ResponseEntity.ok(visitor);
    }
}