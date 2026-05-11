package com.example.ls6_doctor.Service;

import com.example.ls6_doctor.Model.Entity.Patient;
import com.example.ls6_doctor.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}