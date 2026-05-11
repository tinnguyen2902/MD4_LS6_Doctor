package com.example.ls6_doctor.Model.DTO;

import com.example.ls6_doctor.Model.Entity.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientResponse {
    private List<Patient> data;
    private int totalPage;
    private long totalElement;
    private int currentPage;
}