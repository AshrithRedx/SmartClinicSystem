package com.Ashrit.demo.Controller;

import com.Ashrit.demo.Entity.Appointment;
import com.Ashrit.demo.Service.AppointmentService;
import com.Ashrit.demo.Service.UserService;
import com.Ashrit.demo.Entity.User;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final UserService userService;

    public AppointmentController(AppointmentService appointmentService, UserService userService) {
        this.appointmentService = appointmentService;
         this.userService = userService;
    }

    @PreAuthorize("hasRole('PATIENT')")
    @PostMapping("/book")
    public ResponseEntity<?> book(@RequestBody Appointment appointment) {
        return ResponseEntity.ok(appointmentService.book(appointment));
    }

    @PreAuthorize("hasRole('PATIENT')")
    @GetMapping("/user/{id}")
    public ResponseEntity<?> viewPatientAppointments(@PathVariable Long id) {
        User user = userService.findById(id); // create this method if missing
        return ResponseEntity.ok(appointmentService.getAppointmentsByUser(user));
        
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping("/doctor/{id}")
    public ResponseEntity<?> viewDoctorAppointments(@PathVariable Long id) {
        User user = userService.findById(id); // create this method if missing
        return ResponseEntity.ok(appointmentService.getAppointmentsByUser(user));

    }

    @PreAuthorize("hasAnyRole('DOCTOR', 'PATIENT')")
    @GetMapping("/prescriptions/{appointmentId}")
    public ResponseEntity<?> getPrescription(@PathVariable Long appointmentId) {
    String base64Image = appointmentService.getPrescriptionBase64ByAppointmentId(appointmentId);
    return ResponseEntity.ok().body(base64Image);
}

    @PreAuthorize("hasRole('DOCTOR')")
@PostMapping("/prescriptions/upload")
public ResponseEntity<?> uploadPrescription(@RequestParam Long appointmentId, @RequestParam String base64Prescription) {
    Appointment updated = appointmentService.uploadPrescription(appointmentId, base64Prescription);
    return ResponseEntity.ok("Prescription uploaded for appointment ID: " + updated.getId());
}


    
}