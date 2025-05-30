package com.Ashrit.demo.Service.impl;

import com.Ashrit.demo.Entity.Appointment;
import com.Ashrit.demo.Entity.User;
import com.Ashrit.demo.Repository.AppointmentRepository;
import com.Ashrit.demo.Service.AppointmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    // ✅ Manual constructor
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment book(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAppointmentsByUser(User user) {
        return appointmentRepository.findByPatient(user);
    }
    @Override
    public Appointment uploadPrescription(Long appointmentId, String prescription) {
    Appointment appointment = appointmentRepository.findById(appointmentId)
            .orElseThrow(() -> new RuntimeException("Appointment not found"));

    appointment.setPrescription(prescription);
    appointment.setStatus("COMPLETED"); // Optional
    return appointmentRepository.save(appointment);
}
    @Override
public String getPrescriptionBase64ByAppointmentId(Long appointmentId) {
    Appointment appointment = appointmentRepository.findById(appointmentId)
        .orElseThrow(() -> new RuntimeException("Appointment not found"));
    
    return appointment.getPrescription(); // This is the Base64 string
}

@Override
public List<Appointment> getAllAppointments() {
    return appointmentRepository.findAll();
}

@Override
public void cancelAppointment(Long appointmentId) {
    appointmentRepository.deleteById(appointmentId);
}


}
