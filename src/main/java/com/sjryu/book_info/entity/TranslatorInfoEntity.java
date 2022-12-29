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
@Table (name = "translator_info")
@DynamicInsert
public class TranslatorInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "ti_seq") private Long tiSeq;
    @Column (name = "ti_name") private String tiName;
    @Column (name = "ti_introduce") private String tiIntroduce;
    @Column (name = "ti_img") private String tiImg;
    
}
