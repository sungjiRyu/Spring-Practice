package com.sjryu.book_info.entity;

import java.time.LocalDate;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import com.sjryu.book_info.VO.BookAddVO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="book_info")
@DynamicInsert
@Builder
public class BookInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bi_seq") private String biSeq;
    @Column(name = "bi_title") private String biTitle;
    @Column(name = "bi_sub_title") private String biSubTitle;
    @Column(name = "bi_price") private Integer biPrice;

    @Column(name = "bidiscount") @ColumnDefault("0.1")
    private Double biDiscount;

    @Column(name = "bi_point") @ColumnDefault("0.05")
    private Double biPoint;

    @Column(name = "bi_pub_dt") private LocalDate biPubDt;
    // @Column(name = "bi_wi_seq") private Long biWiSeq;
    // @Column(name = "bi_ti_seq") private Long biTiSeq;
    // @Column(name = "bi_pub_seq") private Long biPubSeq;

    // 하나의 작가에 여러 책( 다대 일)
    @ManyToOne @JoinColumn(name = "bi_wi_seq") WriterInfoEntity writer;
    
    @ManyToOne @JoinColumn(name = "bi_ti_seq") TranslatorInfoEntity translator;

    @ManyToOne @JoinColumn(name = "bi_pub_seq") PublishCompanyEntity publisher;
    
public BookInfoEntity (BookAddVO data){
    this.biTitle = data.getTitle();
    this.biSubTitle = data.getSub_title();
    this.biPrice = data.getPrice();
    this.biDiscount = data.getDiscount() / 100.0;
    this.biPoint = data.getPoint() / 100.0;
    this.biPubDt = data.getPub_dt();
    // 데이터가 0이면 선택하지 않은 상태이므로, 아무것도 없다는 의미의 null로 바꿔줘야 함.
    // 그렇지 않으면 0이라는 값은 FK(foregin key)제약조건 위반으로 에러처리됨.
    // this.biWiSeq = data.getWriter()==0 ? null : data.getWriter();
    // this.biTiSeq = data.getTranslator()== 0 ? null : data.getTranslator();
    // this.biPubSeq = data.getPublisherCompany()== 0 ? null : data.getPublisherCompany();
}
    
    
}
