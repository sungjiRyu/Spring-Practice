package com.sjryu.book_info.entity;

import org.hibernate.annotations.DynamicInsert;

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
@Table (name = "publish_company")
@DynamicInsert
public class PublishCompanyEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "pc_seq")  private Long pcSeq;
    @Column (name = "pc_name") private String pcName;
    
}
