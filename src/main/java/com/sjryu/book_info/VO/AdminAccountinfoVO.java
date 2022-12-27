package com.sjryu.book_info.VO;

import com.sjryu.book_info.entity.AdminAccountEntity;

import lombok.Data;

@Data
public class AdminAccountinfoVO {
    private Long seq;
    private String id;
    private String name;
    private Integer grade;
    private Integer status;
    public AdminAccountinfoVO(AdminAccountEntity entity){
        this.seq = entity.getAiSeq();
        this.id = entity.getAiId();
        this.name = entity.getAiName();
        this.grade = entity.getAiGrade();
        this.status = entity.getAiStatus();
    }
}
