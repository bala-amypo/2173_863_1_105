package com.example.demo.service.impl;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.Visitor;
import com.example.demo.entity.Host;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.VisitorRepository;
import com.example.demo.repository.HostRepository;
import com.example.demo.service.AppointmentService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private AppointmentRepository appointmentRepository;
    private VisitorRepository visitorRepository;
    private HostRepository hostRepository;
    
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, 
                                VisitorRepository visitorRepository, 
                                HostRepository hostRepository) {
        this.appointmentRepository = appointmentRepository;
        this.visitorRepository = visitorRepository;
        this.hostRepository = hostRepository;
    }
    
    @Override
    public Appointment createAppointment(Long visitorId, Long hostId, Appointment appointment) {
        if (appointment.getAppointmentDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("appointmentDate cannot be past");
        }
        
        Visitor visitor = visitorRepository.findById(visitorId)
            .orElseThrow(() -> new RuntimeException("Visitor not found"));
        Host host = hostRepository.findById(hostId)
            .orElseThrow(() -> new RuntimeException("Host not found"));
            
        appointment.setVisitor(visitor);
        appointment.setHost(host);
        appointment.setStatus("SCHEDULED");
        
        return appointmentRepository.save(appointment);
    }
    
    @Override
    public Appointment getAppointment(Long id) {
        return appointmentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }
    
    @Override
    public List<Appointment> getAppointmentsForHost(Long hostId) {
        return appointmentRepository.findByHostId(hostId);
    }
    
    @Override
    public List<Appointment> getAppointmentsForVisitor(Long visitorId) {
        return appointmentRepository.findByVisitorId(visitorId);
    }
    

    public Appointment updateAppointment(Long id, Appointment appointmentDetails) {
        Appointment appointment = getAppointment(id);
        if (appointmentDetails.getAppointmentDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("appointmentDate cannot be past");
        }
        appointment.setAppointmentDate(appointmentDetails.getAppointmentDate());
        appointment.setPurpose(appointmentDetails.getPurpose());
        appointment.setStatus(appointmentDetails.getStatus());
        return appointmentRepository.save(appointment);
    }
    
    public Appointment updateAppointmentStatus(Long id, String status) {
        Appointment appointment = getAppointment(id);
        appointment.setStatus(status);
        return appointmentRepository.save(appointment);
    }
    

    public void deleteAppointment(Long id) {
        Appointment appointment = getAppointment(id);
        appointmentRepository.delete(appointment);
    }
    
    public void cancelAppointment(Long id) {
        updateAppointmentStatus(id, "CANCELLED");
    }
    
    @Override
    public Appointment scheduleAppointment(Appointment appointment) {
        appointment.setStatus("SCHEDULED");
        return appointmentRepository.save(appointment);
    }
    
    @Override
    public List<Appointment> getAppointmentsByDate(LocalDate date) {
        return appointmentRepository.findByAppointmentDate(date);
    }
}