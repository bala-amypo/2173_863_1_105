 package com.example.demo.controller;

import com.example.demo.model.Appointment;
import com.example.demo.service.AppointmentService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/{visitorId}/{hostId}")
    public Appointment create(
            @PathVariable Long visitorId,
            @PathVariable Long hostId,
            @RequestBody Appointment appointment) {

        return appointmentService.createAppointment(visitorId, hostId, appointment);
    }

    @GetMapping("/{id}")
    public Appointment get(@PathVariable Long id) {
        return appointmentService.getAppointment(id);
    }

    @GetMapping("/host/{hostId}")
    public List<Appointment> byHost(@PathVariable Long hostId) {
        return appointmentService.getAppointmentsForHost(hostId);
    }

    @GetMapping("/visitor/{visitorId}")
    public List<Appointment> byVisitor(@PathVariable Long visitorId) {
        return appointmentService.getAppointmentsForVisitor(visitorId);
    }
}
