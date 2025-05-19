package com.Ashrit.demo.Controller;

import com.Ashrit.demo.Entity.Appointment;
import com.Ashrit.demo.Service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/book")
    public ResponseEntity<?> book(@RequestBody Appointment appointment) {
        return ResponseEntity.ok(appointmentService.book(appointment));
    }
}