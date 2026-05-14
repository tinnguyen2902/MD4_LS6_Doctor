package com.example.ls6_doctor.Service;

import com.example.ls6_doctor.Model.DTO.MedicalRecorRequest;
import com.example.ls6_doctor.Model.Entity.MedialRecord;
import com.example.ls6_doctor.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService {
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private DoctorRepository doctorRepository; // Dùng để tìm bác sĩ từ ID

    @Autowired
    private PatientRepository patientRepository; // Dùng để tìm bệnh nhân từ ID

    public String createMedicalRecord(MedicalRecorRequest request) {
        // 1. Logic kiểm tra: Bệnh nhân có hồ sơ chưa DONE (đang PROCESSING) không?
        boolean isProcessing = medicalRecordRepository.existsByPatientIdAndStatus(request.getPatientId(), "PROCESSING");

        if (isProcessing) {
            return "Bệnh nhân này hiện đang có hồ sơ điều trị chưa hoàn thành";
        }

        // 2. Nếu ổn, tiến hành tạo mới
        MedialRecord record = new MedialRecord();
        record.setDiagnosis(request.getDiagnosis());

        // Tìm và gán Doctor/Patient từ database
        record.setDoctor(doctorRepository.findById(request.getDoctorId()).orElse(null));
        record.setPatient(patientRepository.findById(request.getPatientId()).orElse(null));

        medicalRecordRepository.save(record);
        return "Tạo hồ sơ bệnh án thành công!";
    }
    // LS6

    public List<MedialRecord> getLatestPatients() {
        // Sử dụng PageRequest để giới hạn 10 bản ghi cho JPQL
        return medicalRecordRepository.findTop10LatestProcessing(PageRequest.of(0, 10));
    }

    public List<DoctorStats> getTopDoctors() {
        return medicalRecordRepository.getTop10Doctors();
    }

    public List<PatientStats> getTopFrequentPatients() {
        return medicalRecordRepository.getTop10FrequentPatients();
    }
}