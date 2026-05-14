package com.example.ls6_doctor.Repository;

import com.example.ls6_doctor.Model.Entity.MedialRecord;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedialRecord,Long> {
    boolean existsByPatientIdAndStatus(Long patientId, String status);
    // (Top 10 bệnh nhân mới nhất):
    @Query("SELECT m FROM MedialRecord m WHERE m.status = 'PROCESSING' ORDER BY m.createdAt DESC")
    List<MedialRecord> findTop10LatestProcessing(Pageable pageable);
    // Top 10 bác sĩ giỏi nhất
    @Query(value = "SELECT d.id as doctorId, d.full_name as fullName, COUNT(m.id) as recordCount " +
            "FROM doctor d JOIN medial_records m ON d.id = m.doctor_id " +
            "GROUP BY d.id, d.full_name " +
            "ORDER BY recordCount DESC LIMIT 10", nativeQuery = true)
    List<DoctorStats> getTop10Doctors();
    //Top 10 bệnh nhân tái khám nhiều nhất
    @Query(value = "SELECT p.id as patientId, p.full_name as fullName, COUNT(m.id) as recordCount " +
            "FROM patient p JOIN medical_records m ON p.id = m.patient_id " +
            "GROUP BY p.id, p.full_name " +
            "ORDER BY recordCount DESC LIMIT 10", nativeQuery = true)
    List<PatientStats> getTop10FrequentPatients();
}
