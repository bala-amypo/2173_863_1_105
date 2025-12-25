package com.example.demo.controller;

import com.example.demo.entity.Appointment;
import com.example.demo.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;
    
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }
    
    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        Appointment created = appointmentService.scheduleAppointment(appointment);
        return ResponseEntity.ok(created);
    }
    
    @PostMapping("/{visitorId}/{hostId}")
    public ResponseEntity<Appointment> createAppointment(
            @PathVariable Long visitorId,
            @PathVariable Long hostId,
            @RequestBody Appointment appointment) {
        Appointment created = appointmentService.createAppointment(visitorId, hostId, appointment);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping("/date/{date}")
    public ResponseEntity<List<Appointment>> getAppointmentsByDate(@PathVariable LocalDate date) {
        List<Appointment> appointments = appointmentService.getAppointmentsByDate(date);
        return ResponseEntity.ok(appointments);
    }
    
    @GetMapping("/host/{hostId}")
    public ResponseEntity<List<Appointment>> getAppointmentsForHost(@PathVariable Long hostId) {
        List<Appointment> appointments = appointmentService.getAppointmentsForHost(hostId);
        return ResponseEntity.ok(appointments);
    }
    
    @GetMapping("/visitor/{visitorId}")
    public ResponseEntity<List<Appointment>> getAppointmentsForVisitor(@PathVariable Long visitorId) {
        List<Appointment> appointments = appointmentService.getAppointmentsForVisitor(visitorId);
        return ResponseEntity.ok(appointments);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointment(@PathVariable Long id) {
        Appointment appointment = appointmentService.getAppointment(id);
        return ResponseEntity.ok(appointment);
    }
}