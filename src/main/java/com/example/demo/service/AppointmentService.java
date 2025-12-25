package com.example.demo.service;

import com.example.demo.entity.Appointment;
import java.time.LocalDate;
import java.util.List;

public interface AppointmentService {
    Appointment scheduleAppointment(Appointment appointment);
    List<Appointment> getAppointmentsByDate(LocalDate date);
    Appointment createAppointment(Long visitorId, Long hostId, Appointment appointment);
    Appointment getAppointment(Long id);
    List<Appointment> getAppointmentsForHost(Long hostId);
    List<Appointment> getAppointmentsForVisitor(Long visitorId);
}