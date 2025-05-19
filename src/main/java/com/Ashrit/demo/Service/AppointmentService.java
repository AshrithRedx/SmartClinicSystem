package com.Ashrit.demo.Service;

import com.Ashrit.demo.Entity.Appointment;
import com.Ashrit.demo.Entity.User;
import java.util.List;

import org.springframework.stereotype.Service;
@Service
public interface AppointmentService {
    Appointment book(Appointment appointment);
    List<Appointment> getAppointmentsByUser(User user);
}
