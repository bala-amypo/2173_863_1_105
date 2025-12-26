package com.example.demo.service.impl;

import com.example.demo.dto.VisitorDTO;
import com.example.demo.entity.Visitor;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.VisitorRepository;
import com.example.demo.service.VisitorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitorServiceImpl implements VisitorService {

    private final VisitorRepository visitorRepository;

    public VisitorServiceImpl(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    private VisitorDTO mapToDTO(Visitor visitor) {
        return new VisitorDTO(
                visitor.getId(),
                visitor.getFullName(),
                visitor.getPhone(),
                visitor.getEmail(),
                visitor.getIdProofNumber()
        );
    }

    private Visitor mapToEntity(VisitorDTO dto) {
        Visitor visitor = new Visitor();
        visitor.setFullName(dto.getFullName());
        visitor.setPhone(dto.getPhone());
        visitor.setEmail(dto.getEmail());
        visitor.setIdProofNumber(dto.getIdProofNumber());
        return visitor;
    }

    @Override
    public VisitorDTO createVisitor(VisitorDTO visitorDTO) {
        Visitor visitor = mapToEntity(visitorDTO);
        return mapToDTO(visitorRepository.save(visitor));
    }

    @Override
    public VisitorDTO getVisitorById(Long id) {
        Visitor visitor = visitorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Visitor not found"));
        return mapToDTO(visitor);
    }

    @Override
    public List<VisitorDTO> getAllVisitors() {
        return visitorRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VisitorDTO updateVisitor(Long id, VisitorDTO visitorDTO) {
        Visitor visitor = visitorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Visitor not found"));

        visitor.setFullName(visitorDTO.getFullName());
        visitor.setPhone(visitorDTO.getPhone());
        visitor.setEmail(visitorDTO.getEmail());
        visitor.setIdProofNumber(visitorDTO.getIdProofNumber());

        return mapToDTO(visitorRepository.save(visitor));
    }

    @Override
    public void deleteVisitor(Long id) {
        Visitor visitor = visitorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Visitor not found"));
        visitorRepository.delete(visitor);
    }
}
