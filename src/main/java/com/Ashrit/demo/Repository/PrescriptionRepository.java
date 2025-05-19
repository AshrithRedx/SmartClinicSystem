package com.Ashrit.demo.Repository;

import com.Ashrit.demo.Entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
}