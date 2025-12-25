package com.example.demo.service.impl;

import com.example.demo.entity.Visitor;
import com.example.demo.repository.VisitorRepository;
import com.example.demo.service.VisitorService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VisitorServiceImpl implements VisitorService {
    private final VisitorRepository visitorRepository;
    
    public VisitorServiceImpl(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }
    

    public Visitor createVisitor(Visitor visitor) {
        visitor.setCreatedAt(LocalDateTime.now());
        return visitorRepository.save(visitor);
    }
    
    @Override
    public Visitor registerVisitor(Visitor visitor) {
        return createVisitor(visitor);
    }
    

    @Override
    public Visitor getVisitor(Long id) {
        return visitorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Visitor not found"));
    }
    
    @Override
    public List<Visitor> getAllVisitors() {
        return visitorRepository.findAll();
    }
    

    public Visitor updateVisitor(Long id, Visitor visitorDetails) {
        Visitor visitor = getVisitor(id);
        visitor.setFullName(visitorDetails.getFullName());
        visitor.setEmail(visitorDetails.getEmail());
        visitor.setPhone(visitorDetails.getPhone());
        visitor.setIdProofNumber(visitorDetails.getIdProofNumber());
        return visitorRepository.save(visitor);
    }
    

    public void deleteVisitor(Long id) {
        Visitor visitor = getVisitor(id);
        visitorRepository.delete(visitor);
    }
}