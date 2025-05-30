package com.Ashrit.demo.Service;

import com.Ashrit.demo.Entity.Doctor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DoctorService {
    Doctor addDoctor(Doctor doctor);
    Doctor updateDoctor(Long id, Doctor doctor);
    void deleteDoctor(Long id);
    List<Doctor> getBySpecialization(String specialization);
    Object uploadPrescription(MultipartFile file, Long appointmentId);

}
