package com.example.demo.controller;

import com.example.demo.model.Visitor;
import com.example.demo.service.VisitorService;
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
    public Visitor create(@RequestBody Visitor visitor) {
        return visitorService.createVisitor(visitor);
    }

    @GetMapping
    public List<Visitor> getAll() {
        return visitorService.getAllVisitors();
    }

    @GetMapping("/{id}")
    public Visitor get(@PathVariable Long id) {
        return visitorService.getVisitor(id);
    }
}
