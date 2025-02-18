package com.java.order;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderBizDTO {
    
    private int bizNo;
    private String bizName;

}
