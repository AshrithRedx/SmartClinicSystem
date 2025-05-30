package com.Ashrit.demo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.Ashrit.demo.Entity.Doctor;
import com.Ashrit.demo.Service.AppointmentService;
import com.Ashrit.demo.Service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
     private final DoctorService doctorService;
    private final AppointmentService appointmentService;

    public AdminController(DoctorService doctorService, AppointmentService appointmentService) {
        this.doctorService = doctorService;
        this.appointmentService = appointmentService;
    }

    @PostMapping("/doctor/add")
    public ResponseEntity<?> addDoctor(@RequestBody Doctor doctor) {
        return ResponseEntity.ok(doctorService.addDoctor(doctor));
    }

    @PutMapping("/doctor/update/{id}")
    public ResponseEntity<?> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
        return ResponseEntity.ok(doctorService.updateDoctor(id, doctor));
    }

    @DeleteMapping("/doctor/delete/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.ok("Doctor deleted successfully");
    }

    @GetMapping("/appointments")
    public ResponseEntity<?> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @DeleteMapping("/appointment/cancel/{id}")
    public ResponseEntity<?> cancelAppointment(@PathVariable Long id) {
        appointmentService.cancelAppointment(id);
        return ResponseEntity.ok("Appointment canceled");
    }
}
