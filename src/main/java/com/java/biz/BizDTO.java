package com.java.biz;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BizDTO {

    private int bizNo;
    private String bizNum;
    private String bizName;
    private String bizTel;
    private String bizFax;
    private String email;
    private String ceo;
    private String bizType;
    private String zipCode;
    private String adr;
    private String adrDetail;
    private char useYN;
    private String regDate;
}
