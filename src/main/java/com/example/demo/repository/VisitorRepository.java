package com.example.demo.repository;

import com.example.demo.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {
    Optional<Visitor> findByPhone(String phone);
    Optional<Visitor> findByEmail(String email);
}