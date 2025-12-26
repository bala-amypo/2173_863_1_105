package com.example.demo.controller;

import com.example.demo.model.Host;
import com.example.demo.service.HostService;
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
    public Host create(@RequestBody Host host) {
        return hostService.createHost(host);
    }

    @GetMapping
    public List<Host> getAll() {
        return hostService.getAllHosts();
    }

    @GetMapping("/{id}")
    public Host get(@PathVariable Long id) {
        return hostService.getHost(id);
    }
}
