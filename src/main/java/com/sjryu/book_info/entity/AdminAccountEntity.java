package com.sjryu.book_info.entity;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="admin_account")
@Builder
@DynamicInsert // 입력되지않은 null 값에 default 값을 추가해줌
public class AdminAccountEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "ai_seq")                          private Long aiSeq;
    @Column (name = "ai_id")                           private String aiId;
    @Column (name = "ai_pwd")                          private String aiPwd;
    @Column (name = "ai_name")                         private String aiName;
    @Column (name = "ai_grade")  @ColumnDefault("1")   private Integer aiGrade;
    @Column (name = "ai_status") @ColumnDefault("0")   private Integer aiStatus;
}
