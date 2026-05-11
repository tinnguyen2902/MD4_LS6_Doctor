package com.example.ls6_doctor.Service;

import com.example.ls6_doctor.Model.DTO.PatientResponse;
import com.example.ls6_doctor.Model.Entity.Patient;
import com.example.ls6_doctor.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public String deletePatientById(Long id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            return "SUCCESS";
        }
        return "NOT_FOUND";
    }
    public PatientResponse searchPatients(String patientName, int page, int size) {
        // PageRequest: trang bắt đầu từ 0, sắp xếp A-Z
        Pageable pageable = PageRequest.of(page, size, Sort.by("fullName").ascending());

        // Gọi Repository để lấy dữ liệu
        Page<Patient> patientPage = patientRepository.findByFullNameContainingIgnoreCase(patientName, pageable);

        return PatientResponse.builder()
                .data(patientPage.getContent())
                .totalPage(patientPage.getTotalPages())
                .totalElement(patientPage.getTotalElements())
                .currentPage(page)
                .build();
    }
}