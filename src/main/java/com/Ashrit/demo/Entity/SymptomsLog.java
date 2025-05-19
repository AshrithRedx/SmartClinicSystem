package com.Ashrit.demo.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "symptoms_log")
public class SymptomsLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private User patient;

    @Column(length = 1000)
    private String symptomsText;

    private LocalDate date;

    public SymptomsLog() {}

    public SymptomsLog(Long id, User patient, String symptomsText, LocalDate date) {
        this.id = id;
        this.patient = patient;
        this.symptomsText = symptomsText;
        this.date = date;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public User getPatient() { return patient; }

    public void setPatient(User patient) { this.patient = patient; }

    public String getSymptomsText() { return symptomsText; }

    public void setSymptomsText(String symptomsText) { this.symptomsText = symptomsText; }

    public LocalDate getDate() { return date; }

    public void setDate(LocalDate date) { this.date = date; }
}
