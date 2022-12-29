package com.sjryu.book_info.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sjryu.book_info.entity.TranslatorInfoEntity;

@Repository
public interface TranslatorInfoRepository extends JpaRepository<TranslatorInfoEntity, Long> {
    TranslatorInfoEntity findByTiSeq(Long tiSeq);
}
