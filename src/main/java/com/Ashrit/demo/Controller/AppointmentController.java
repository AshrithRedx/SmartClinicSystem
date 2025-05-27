package com.Ashrit.demo.Controller;

import com.Ashrit.demo.Entity.Appointment;
import com.Ashrit.demo.Service.AppointmentService;
import com.Ashrit.demo.Service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PreAuthorize("hasRole('PATIENT')")
    @PostMapping("/book")
    public ResponseEntity<?> book(@RequestBody Appointment appointment) {
        return ResponseEntity.ok(appointmentService.book(appointment));
    }

    @PreAuthorize("hasRole('PATIENT')")
    @GetMapping("/user/{id}")
    public ResponseEntity<?> viewPatientAppointments(@PathVariable Long id) {
        User user = UserService.findById(id); // create this method if missing
        return ResponseEntity.ok(appointmentService.getAppointmentsByUser(user));
        
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping("/doctor/{id}")
    public ResponseEntity<?> viewDoctorAppointments(@PathVariable Long id) {
        User user = UserService.findById(id); // create this method if missing
        return ResponseEntity.ok(appointmentService.getAppointmentsByUser(user));

    }
}