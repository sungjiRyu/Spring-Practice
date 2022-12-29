package com.sjryu.book_info.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sjryu.book_info.entity.WriterInfoEntity;

public interface WriterInfoRepository extends JpaRepository <WriterInfoEntity, Long>{
    WriterInfoEntity findByWiSeq(Long wiSeq);
}
