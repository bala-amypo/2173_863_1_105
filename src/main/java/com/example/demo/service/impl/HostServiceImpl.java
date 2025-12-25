package com.example.demo.service.impl;

import com.example.demo.entity.Host;
import com.example.demo.repository.HostRepository;
import com.example.demo.service.HostService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class HostServiceImpl implements HostService {
    private HostRepository hostRepository;
    
    public HostServiceImpl() {}
    

    @Override
    public Host createHost(Host host) {
        host.setCreatedAt(LocalDateTime.now());
        return hostRepository.save(host);
    }
    

    @Override
    public Host getHost(Long id) {
        return hostRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Host not found"));
    }
    
    @Override
    public List<Host> getAllHosts() {
        return hostRepository.findAll();
    }
    
    public Host getHostByEmail(String email) {
        return hostRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Host not found with email: " + email));
    }
    

    public Host updateHost(Long id, Host hostDetails) {
        Host host = getHost(id);
        host.setHostName(hostDetails.getHostName());
        host.setFullname(hostDetails.getFullname());
        host.setEmail(hostDetails.getEmail());
        host.setDepartment(hostDetails.getDepartment());
        host.setPhone(hostDetails.getPhone());
        return hostRepository.save(host);
    }
    

    public void deleteHost(Long id) {
        Host host = getHost(id);
        hostRepository.delete(host);
    }
}