package com.Ashrit.demo.Repository;

import com.Ashrit.demo.Entity.Appointment;
import com.Ashrit.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatient(User patient);
}