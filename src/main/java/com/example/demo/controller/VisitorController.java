package com.example.demo.controller;

import com.example.demo.dto.VisitorDTO;
import com.example.demo.service.VisitorService;
import org.springframework.http.*;
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
    public ResponseEntity<VisitorDTO> create(@RequestBody VisitorDTO dto) {
        return new ResponseEntity<>(visitorService.createVisitor(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisitorDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(visitorService.getVisitorById(id));
    }

    @GetMapping
    public ResponseEntity<List<VisitorDTO>> getAll() {
        return ResponseEntity.ok(visitorService.getAllVisitors());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VisitorDTO> update(
            @PathVariable Long id,
            @RequestBody VisitorDTO dto) {
        return ResponseEntity.ok(visitorService.updateVisitor(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        visitorService.deleteVisitor(id);
        return ResponseEntity.ok("Visitor deleted successfully");
    }
}
