package com.sjryu.book_info.entity;

import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Writer_info")
@DynamicInsert
public class WriterInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wi_seq")         private Long   wiSeq; 
    @Column(name = "wi_name")        private String wiName;
    @Column(name = "wi_introduce")   private String wiIntroduce;
    @Column(name = "wi_img")         @ColumnDefault("writer_default.jpg")
    // 일대 다 관계 join
    @OneToMany @JoinColumn(name = "bi_wi_seq") List<BookInfoEntity> books;
    private String Wiimg;
}
