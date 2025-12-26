package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Host;

import java.util.Optional;

public interface HostRepository extends JpaRepository<Host, Long> {

    Optional<Host> findByEmail(String email);
}
