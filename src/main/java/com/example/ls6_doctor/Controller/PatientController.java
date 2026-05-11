package com.example.ls6_doctor.Controller;

import com.example.ls6_doctor.Model.DTO.PatientResponse;
import com.example.ls6_doctor.Model.Entity.Patient;
import com.example.ls6_doctor.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    // POST: Thêm mới bệnh nhân
    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        return new ResponseEntity<>(patientService.addPatient(patient), HttpStatus.CREATED);
    }

    // DELETE: Xóa bệnh nhân theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> removePatient(@PathVariable Long id) {
        String result = patientService.deletePatientById(id);
        if (result.equals("SUCCESS")) {
            return ResponseEntity.ok("Xóa thành công bệnh nhân có ID: " + id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy bệnh nhân có ID: " + id);
    }
    @GetMapping("/search")
    public ResponseEntity<PatientResponse> searchPatients(
            @RequestParam(defaultValue = "") String patientName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return ResponseEntity.ok(patientService.searchPatients(patientName, page, size));
    }
}