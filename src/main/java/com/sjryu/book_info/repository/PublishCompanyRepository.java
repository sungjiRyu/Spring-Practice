package com.sjryu.book_info.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sjryu.book_info.entity.PublishCompanyEntity;

public interface PublishCompanyRepository extends JpaRepository<PublishCompanyEntity, Long>{
    PublishCompanyEntity findByPcSeq(Long pcSeq);
}
