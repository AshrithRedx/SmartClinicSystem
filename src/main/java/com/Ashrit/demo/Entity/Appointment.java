package com.Ashrit.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private User patient;

    private String time;

    private String status; // BOOKED, RESCHEDULED, CANCELLED
    @Lob
    @Column(name = "prescription", columnDefinition = "TEXT")
    private String prescription;




    public Appointment() {}

    public Appointment(Long id, Doctor doctor, User patient, String time, String status, String prescription) {
        this.id = id;
        this.doctor = doctor;
        this.patient = patient;
        this.time = time;
        this.status = status;
        this.prescription = prescription;
    }

    // Getters and setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Doctor getDoctor() { return doctor; }

    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

    public User getPatient() { return patient; }

    public void setPatient(User patient) { this.patient = patient; }

    public String getTime() { return time; }

    public void setTime(String time) { this.time = time; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public String getPrescription() { return prescription;}

public void setPrescription(String prescription) {this.prescription = prescription;}

}
