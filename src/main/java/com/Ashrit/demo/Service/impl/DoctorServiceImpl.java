package com.Ashrit.demo.Service.impl;

import com.Ashrit.demo.Entity.Doctor;
import com.Ashrit.demo.Repository.DoctorRepository;
import com.Ashrit.demo.Service.DoctorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> getBySpecialization(String specialization) {
        return doctorRepository.findBySpecialization(specialization);
    }
}
