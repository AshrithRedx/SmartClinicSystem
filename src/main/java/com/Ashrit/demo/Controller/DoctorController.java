package com.Ashrit.demo.Controller;


import com.Ashrit.demo.Entity.Doctor;
import com.Ashrit.demo.Service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
}