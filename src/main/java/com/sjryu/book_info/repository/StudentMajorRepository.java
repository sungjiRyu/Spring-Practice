package com.sjryu.book_info.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sjryu.book_info.entity.StudentMajorEntity;

@Repository
public interface StudentMajorRepository extends JpaRepository <StudentMajorEntity, Long> {
    
}
