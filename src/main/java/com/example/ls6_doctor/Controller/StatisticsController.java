package com.example.ls6_doctor.Controller;


import com.example.ls6_doctor.Model.Entity.MedialRecord;
import com.example.ls6_doctor.Repository.DoctorStats;
import com.example.ls6_doctor.Repository.PatientStats;
import com.example.ls6_doctor.Service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stats")
public class StatisticsController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @GetMapping("/latest-patients")
    public ResponseEntity<List<MedialRecord>> getLatest() {
        return ResponseEntity.ok(medicalRecordService.getLatestPatients());
    }

    @GetMapping("/top-doctors")
    public ResponseEntity<List<DoctorStats>> getTopDoctors() {
        return ResponseEntity.ok(medicalRecordService.getTopDoctors());
    }

    @GetMapping("/frequent-patients")
    public ResponseEntity<List<PatientStats>> getFrequent() {
        return ResponseEntity.ok(medicalRecordService.getTopFrequentPatients());
    }
}