package com.Ashrit.demo.Repository;

import com.Ashrit.demo.Entity.SymptomsLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SymptomsLogRepository extends JpaRepository<SymptomsLog, Long> {
}