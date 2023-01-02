package com.sjryu.book_info.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "student_major")
public class StudentMajorEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "major_seq")  private Long majorSeq;
    @Column(name = "major_name") private String majorName;
    
}
