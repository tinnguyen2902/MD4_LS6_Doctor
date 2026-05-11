package com.example.ls6_doctor.Service;

import com.example.ls6_doctor.Model.Entity.Doctor;
import com.example.ls6_doctor.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    // kết nối với repository
    private DoctorRepository doctorRepository;
    public Doctor addDoctor(Doctor doctor){
        return doctorRepository.save(doctor);
    }
    public String deleteDoctorById(Long id){
        if(doctorRepository.existsById(id)){
            doctorRepository.deleteById(id);
            return "SUCCESS";
        }
        return "NOT_FOUND";
    }
    //LS2: update + select
    // select
    public List<Doctor> getAllDoctors(){
        return doctorRepository.findAll();
    }
    // update
    public Doctor updateDoctor(Long id,Doctor doctorRequest){
        // tìm doctor
        return doctorRepository.findById(id).map(existingDoctor -> {
            existingDoctor.setFullName(doctorRequest.getFullName());
            existingDoctor.setSpecialization(doctorRequest.getSpecialization());
            existingDoctor.setExperienceYears(doctorRequest.getExperienceYears());
            existingDoctor.setDoctorCode(doctorRequest.getDoctorCode());
            //lưu lại
            return doctorRepository.save(existingDoctor);
        }).orElse(null); // trả về null nếu không tìm thấy
    }
}