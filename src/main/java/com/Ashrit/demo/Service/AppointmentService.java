// Remove the @Service annotation from interface
package com.Ashrit.demo.Service;

import com.Ashrit.demo.Entity.Appointment;
import com.Ashrit.demo.Entity.User;

import java.util.List;

public interface AppointmentService {
    Appointment book(Appointment appointment);
    List<Appointment> getAppointmentsByUser(User user);
    Appointment uploadPrescription(Long appointmentId, String prescription);
    String getPrescriptionBase64ByAppointmentId(Long appointmentId);
    List<Appointment> getAllAppointments();
    void cancelAppointment(Long appointmentId);

}
