package com.java.order;


import org.apache.ibatis.annotations.*;
import org.springframework.core.annotation.Order;

import com.java.biz.BizApiKeyDTO;

import java.util.List;

@Mapper
public interface OrderMapper {


    // 발주 조회
    @Select("SELECT a.orderNo, a.bizNo, a.orderReqDate, a.dstn, a.orderStatus, b.adr FROM stg_order a JOIN stg_client b ON a.bizNo = b.bizNo ORDER BY a.orderNo DESC")
    public List<OrderDTO> orderInquiry();
    // 발주 조회 검색
    @Select({
            "<script>",
            "SELECT a.orderNo, a.orderStatus, a.orderReqDate, b.transpStatus, b.departure, b.dstn, b.driverName",
            "FROM stg_order a",
            "JOIN stg_transp b ON a.orderNo = b.orderNo",
            "<where>",
            "<if test='searchTerm != null and searchTerm != \"\"'>",
            "AND (a.orderNo LIKE CONCAT('%', #{searchTerm}, '%')",
            "OR a.orderStatus LIKE CONCAT('%', #{searchTerm}, '%')",
            "OR b.departure LIKE CONCAT('%', #{searchTerm}, '%')",
            "OR b.dstn LIKE CONCAT('%', #{searchTerm}, '%')",
            "OR b.driverName LIKE CONCAT('%', #{searchTerm}, '%'))",
            "</if>",
            "</where>",
            "</script>"
    })
    public List<OrderDTO> searchOrders(@Param("searchTerm") String searchTerm);


    @Insert("INSERT INTO stg_order (bizNo, dstn, orderStatus, daliDate, orderReqDate) " +
            "VALUES (#{bizNo}, #{dstn}, #{orderStatus}, #{daliDate}, NOW())")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "orderNo", resultType = Integer.class, before = false)
    void insertOrderAndReturnOrderNo(OrderDTO order);  // orderNo를 OrderDTO 객체에 채우기


    @Insert("INSERT INTO stg_order_item (orderNo, itemCode, qty, dueDate) " +
            "VALUES (#{orderNo}, #{itemCode}, #{qty}, #{dueDate})")
    void insertOrderItem(OrderItemDTO orderItem);

    @Select("select bizNo, bizName from stg_client where bizType = '제조'")
    public List<OrderBizDTO> findByBiz();

    @Select("select * from stg_api_key where type = 'list' and useYn = 'Y' and bizNo = #{bizNo}")
    public BizApiKeyDTO findByBizApi(int bizNo);

}
