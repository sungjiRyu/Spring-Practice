package com.sjryu.book_info.VO;

import java.time.LocalDate;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookAddVO {
private String      title;               
private LocalDate   pub_dt;              
private String      sub_title;           
private Long        writer;           // seq번호가 들어옴   
private Long        translator;       // seq번호가 들어옴   
private Long        publisherCompany; // seq번호가 들어옴   
private Integer     price;               
private Integer     discount;            
private Integer     point;               
}
