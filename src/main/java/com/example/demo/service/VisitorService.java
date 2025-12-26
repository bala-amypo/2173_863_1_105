package com.example.demo.service;

import com.example.demo.dto.VisitorDTO;

import java.util.List;

public interface VisitorService {

    VisitorDTO createVisitor(VisitorDTO visitorDTO);
    VisitorDTO getVisitorById(Long id);
    List<VisitorDTO> getAllVisitors();
    VisitorDTO updateVisitor(Long id, VisitorDTO visitorDTO);
    void deleteVisitor(Long id);
}
