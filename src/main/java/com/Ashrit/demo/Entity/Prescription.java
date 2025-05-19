package com.Ashrit.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "prescriptions")
public class Prescription {

    @Id
    private Long appointmentId;

    private String fileUrl;

    public Prescription() {}

    public Prescription(Long appointmentId, String fileUrl) {
        this.appointmentId = appointmentId;
        this.fileUrl = fileUrl;
    }

    public Long getAppointmentId() { return appointmentId; }

    public void setAppointmentId(Long appointmentId) { this.appointmentId = appointmentId; }

    public String getFileUrl() { return fileUrl; }

    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }
}
