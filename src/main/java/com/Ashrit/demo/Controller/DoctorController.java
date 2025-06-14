package com.Ashrit.demo.Controller;

import com.Ashrit.demo.Entity.Doctor;
import com.Ashrit.demo.Service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getBySpecialist(@RequestParam String specialist) {
        return ResponseEntity.ok(doctorService.getBySpecialization(specialist));
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @PostMapping("/prescriptions/upload")
    public ResponseEntity<?> uploadPrescription(@RequestParam MultipartFile file, @RequestParam Long appointmentId) {
        return ResponseEntity.ok(doctorService.uploadPrescription(file, appointmentId));
    }
}