package com.Ashrit.demo.Service;

import com.Ashrit.demo.Entity.Doctor;
import java.util.List;

public interface DoctorService {
    Doctor saveDoctor(Doctor doctor);
    List<Doctor> getBySpecialization(String specialization);
}
