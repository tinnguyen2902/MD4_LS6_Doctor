package com.example.ls6_doctor.Controller;

import com.example.ls6_doctor.Model.Entity.Doctor;
import com.example.ls6_doctor.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {
 @Autowired
    //Ket nối service
    private DoctorService doctorService;
    //POST
    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor){
        return new ResponseEntity<>(doctorService.addDoctor(doctor), HttpStatus.CREATED);
    }
    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeDoctor(@PathVariable Long id){
        String result = doctorService.deleteDoctorById(id);
        if(result.equals("SUCCESS")){
            return ResponseEntity.ok("Xóa thành công bác sĩ ID " +id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy bác sĩ có ID" + id);
    }
    //SELECT
    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> list = doctorService.getAllDoctors();
        return ResponseEntity.ok(list);
    }
    //update
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
        Doctor updatedDoctor = doctorService.updateDoctor(id, doctor);
        if (updatedDoctor != null) {
            return ResponseEntity.ok(updatedDoctor);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Không tìm thấy bác sĩ có ID " + id + " để cập nhật.");
    }

}