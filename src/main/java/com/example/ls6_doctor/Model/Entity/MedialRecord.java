package com.example.ls6_doctor.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "medical_records") // Đặt tên bảng rõ ràng trong DB
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedialRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String diagnosis;

    private String status;

    private LocalDateTime createdAt;

    // Mối quan hệ với Doctor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id") // Đây là nơi tạo ra setDoctor(Doctor doctor)
    private Doctor doctor;

    // Mối quan hệ với Patient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id") // Đây là nơi tạo ra setPatient(Patient patient)
    private Patient patient;

    /**
     * Tự động chạy trước khi dữ liệu được chèn vào database lần đầu
     */
    @PrePersist
    protected void onCreate() {
        if (this.status == null) {
            this.status = "PROCESSING"; // Mặc định trạng thái
        }
        this.createdAt = LocalDateTime.now();
    }
}