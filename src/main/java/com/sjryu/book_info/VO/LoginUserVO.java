package com.sjryu.book_info.VO;

import com.sjryu.book_info.entity.AdminAccountEntity;

import lombok.Builder;
import lombok.Data;

@Data
public class LoginUserVO {
    private String id;
    private String name;
    private Integer grade;
    private Integer status;
    public LoginUserVO(AdminAccountEntity account){
        this.id = account.getAiId();
        this.name = account.getAiName();
        this.grade = account.getAiGrade();
        this.status = account.getAiStatus();
    }
    
}
