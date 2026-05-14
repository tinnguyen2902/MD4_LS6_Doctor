package com.example.ls6_doctor.Repository;

import com.example.ls6_doctor.Model.Entity.MedialRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedialRecord,Long> {
    boolean existsByPatientIdAndStatus(Long patientId, String status);
}
