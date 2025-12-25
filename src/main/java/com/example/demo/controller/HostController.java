package com.example.demo.controller;

import com.example.demo.entity.Host;
import com.example.demo.service.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/hosts")
public class HostController {
    private final HostService hostService;
    
    public HostController(HostService hostService) {
        this.hostService = hostService;
    }
    
    @PostMapping
    public ResponseEntity<Host> createHost(@RequestBody Host host) {
        Host created = hostService.createHost(host);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping
    public ResponseEntity<List<Host>> getAllHosts() {
        List<Host> hosts = hostService.getAllHosts();
        return ResponseEntity.ok(hosts);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Host> getHost(@PathVariable Long id) {
        Host host = hostService.getHost(id);
        return ResponseEntity.ok(host);
    }
}