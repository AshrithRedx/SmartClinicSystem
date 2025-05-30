package com.Ashrit.demo.Service.impl;

import com.Ashrit.demo.Entity.Appointment;
import com.Ashrit.demo.Entity.Doctor;
import com.Ashrit.demo.Repository.AppointmentRepository;
import com.Ashrit.demo.Repository.DoctorRepository;
import com.Ashrit.demo.Service.DoctorService;
import org.springframework.web.multipart.MultipartFile;
import java.util.Base64;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository, AppointmentRepository appointmentRepository) {
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
    }
    @Override
    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor updateDoctor(Long id, Doctor updatedDoctor) {
        Doctor existingDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        existingDoctor.setName(updatedDoctor.getName());
        existingDoctor.setSpecialization(updatedDoctor.getSpecialization());

        return doctorRepository.save(existingDoctor);
    }

    @Override
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
    

    @Override
    public List<Doctor> getBySpecialization(String specialization) {
        return doctorRepository.findBySpecialization(specialization);
    }


    @Override
    public String uploadPrescription(MultipartFile file, Long appointmentId) {
        try {
            Appointment appointment = appointmentRepository.findById(appointmentId)
                    .orElseThrow(() -> new RuntimeException("Appointment not found"));

            // Convert the file to base64 or store the file path (you can change this logic)
            byte[] bytes = file.getBytes();
            String base64Encoded = Base64.getEncoder().encodeToString(bytes);

            appointment.setPrescription(base64Encoded); // Store file as base64 string
            appointmentRepository.save(appointment);

            return "Prescription uploaded successfully!";
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload prescription", e);
        }
    }
}
