package com.Ashrit.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String specialization;
    private String bio;
    private String name;

    // Store availability as a JSON string
    @Column(columnDefinition = "TEXT")
    private String availabilitySlots;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Doctor() {}

    public Doctor(Long id, String specialization, String bio, String availabilitySlots, User user,String name) {
        this.id = id;
        this.specialization = specialization;
        this.bio = bio;
        this.availabilitySlots = availabilitySlots;
        this.user = user;
        this.name = name;
    }

    // Getters and setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getSpecialization() { return specialization; }

    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public String getBio() { return bio; }

    public void setBio(String bio) { this.bio = bio; }

    public String getAvailabilitySlots() { return availabilitySlots; }

    public void setAvailabilitySlots(String availabilitySlots) { this.availabilitySlots = availabilitySlots; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public String getName() {
    return name;
}
    public void setName(String name) {
        this.name = name;
    }

}
