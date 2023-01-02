package com.sjryu.book_info.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity




@Table (name = "student_info")
public class StudentInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stu_seq")       private Long    stuSeq;
    @Column(name = "stu_name")      private String  stuName;
    // @Column(name = "stu_major_seq") private Long    stuMajorSeq;
    @ManyToOne(cascade = CascadeType.ALL) // cascade 속성부여(연계되는 데이터 같이 입력하겠다)
    // @JoinColumn(name = "stu_major_seq") StudentMajorEntity major;
    @JoinColumn(name = "stu_major_seq") StudentMajorEntity major;
}
