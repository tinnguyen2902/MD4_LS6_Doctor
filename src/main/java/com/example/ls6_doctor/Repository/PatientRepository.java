package com.example.ls6_doctor.Repository;

import com.example.ls6_doctor.Model.Entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    // Tìm kiếm theo tên (không phân biệt hoa thường) và hỗ trợ phân trang
    Page<Patient> findByFullNameContainingIgnoreCase(String fullName, Pageable pageable);

}
