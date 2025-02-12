package com.java.order;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.util.List;

@Mapper
public interface OrderMapper {


    // 발주 조회
    @Select("SELECT a.orderNo , a.orderStatus, a.orderReqDate ,b.transpStatus , b.departure, b.dstn , b.driverName FROM stg_order a JOIN stg_transp b ON a.orderNo = b.orderNo;")
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


    @Insert("INSERT INTO stg_order (orderNo, bizNo, orderReqDate) " +
            "VALUES (#{orderNo}, #{bizNo}, #{orderReqDate})")
    void insertOrder(OrderDTO order);

    @Insert("INSERT INTO stg_order_item (orderNo, itemCode, qty) " +
            "VALUES (#{orderNo}, #{itemCode}, #{qty})")
    void insertOrderItem(OrderItemDTO orderItem);



}
