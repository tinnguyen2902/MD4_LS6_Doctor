package com.example.ls6_doctor.Model.DTO;

import lombok.Data;

@Data
public class MedicalRecorRequest {
    private Long patientId;
    private Long doctorId;
    private String diagnosis;
    private String treatment;

    public MedicalRecorRequest() {
    }

    public MedicalRecorRequest(Long patientId, Long doctorId, String diagnosis, String treatment) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }
}