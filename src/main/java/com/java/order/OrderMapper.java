package com.java.order;


import org.apache.ibatis.annotations.*;
import org.springframework.core.annotation.Order;

import com.java.biz.BizApiKeyDTO;

import java.util.List;

@Mapper
public interface OrderMapper {


    // 발주 조회
    @Select("SELECT a.orderNo, a.bizNo, a.orderReqDate, a.dstn, a.orderStatus FROM stg_order a JOIN stg_client b ON a.bizNo = b.bizNo ORDER BY a.orderNo DESC")
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


    @Insert("INSERT INTO stg_order (bizNo, dstn, orderStatus, deliDate, orderReqDate) " +
            "VALUES (#{bizNo}, #{dstn}, #{orderStatus}, #{deliDate}, NOW())")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "orderNo", resultType = Integer.class, before = false)
    int insertOrderAndReturnOrderNo(OrderDTO order);  // orderNo를 OrderDTO 객체에 채우기


    @Insert("INSERT INTO stg_order_item (orderNo, itemCode, itemName, qty) " +
            "VALUES (#{orderNo}, #{itemCode}, #{itemName}, #{qty})")
    int insertOrderItem(OrderItemDTO orderItem);

    @Select("select bizNo, bizName from stg_client where useYn = 'Y' and bizType = '제조'")
    public List<OrderBizDTO> findByBiz();

    @Select("select * from stg_api_key where type = #{type} and useYn = 'Y' and bizNo = #{bizNo}")
    public BizApiKeyDTO findByBizApi(BizApiKeyDTO bizApiKeyDTO);

    @Select("select * from stg_order where orderNo = #{orderNo}")
    public OrderDTO findByOrderNo(int orderNo);

    @Update("UPDATE stg_order SET orderStatus = #{orderStatus} WHERE orderNo = #{orderNo}")
    public int orderEdit(OrderDTO order);

    @Insert(" INSERT INTO stg_incoming (orderItemNo, qty, status) "
           +" SELECT orderItemNo, 0 AS qty, '미입고' AS status "
           +" FROM stg_order_item WHERE orderNo = #{orderNo} ")
    public int orderSyncIncoming(int orderNo);

}
