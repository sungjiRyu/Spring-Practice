package com.sjryu.book_info.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sjryu.book_info.entity.AdminAccountEntity;

@Repository
public interface AdminAccountRepository extends JpaRepository <AdminAccountEntity, Long> {
    // 아이디 중복검사 (select count(*) from admin_account where ai_id = aiId;)
    Integer countByAiId(String aiId); 
    // Integer countByAiName(String aiName);
    // select * from admin_account where ai_id = {aiId} and aiPwd = {aiPwd}
    AdminAccountEntity findByAiIdAndAiPwd(String aiId, String aiPwd);
    AdminAccountEntity findByAiSeq(Long aiSeq);
    // 페이징처리
    Page<AdminAccountEntity> findAll(Pageable pageable);
}
