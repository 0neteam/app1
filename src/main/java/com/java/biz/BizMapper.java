package com.java.biz;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

@Mapper
public interface BizMapper {


    @Select({
            "<script>",
            "SELECT bizNo, bizName, bizType FROM stg_client",
            "<where>",
            "   <choose>",
            "       <when test='searchOption = \"1\"'>",
            "           AND bizName LIKE CONCAT('%', #{keyword}, '%')",
            "       </when>",
            "       <when test='searchOption = \"2\"'>",
            "           AND CAST(bizNo AS CHAR) LIKE CONCAT('%', #{keyword}, '%')",
            "       </when>",
            "       <when test='searchOption = \"3\"'>",
            "           AND bizType LIKE CONCAT('%', #{keyword}, '%')",
            "       </when>",
            "   </choose>",
            "</where>",
            "</script>"
    })
    public List<BizDTO> findList();

//    @Select({"<script>" +
//            "SELECT bizNo,bizName,bizType FROM stg_client" +
//            "where true" +
//            "<choose>" +
//            "<when test='searchOption==\"1\"'>" +
//            "and bizName like concat('%', #{keyword} ,'%')" +
//            "</when>" +
//            "<when test='searchOption==\"2\"'>" +
//            "and bizNo like concat('%', #{keyword} ,'%')" +
//            "</when>" +
//            "<when test='searchOption==\"3\"'>" +
//            "and bizType like concat('%', #{keyword} ,'%')" +
//            "</when>" +
//            "</choose>" +
//            "</script>"})
//    public List<BizDTO> findList();

    @SelectKey(statementType = StatementType.PREPARED, statement = "select last_insert_id() as no", keyProperty = "bizNo", before = false, resultType = int.class)
    //@Insert("INSERT INTO 'stg_client'")
    int create(BizDTO biz);

    @Select("SELECT * " +
            "FROM stg_client " +
            "LEFT JOIN stg_api_key " +
            "ON stg_client.bizNo = stg_api_key.bizNo " +
            "AND stg_api_key.`type` = 'order' " +
            "WHERE stg_client.bizNo = #{no}")
    BizDTO findOne(int no);
}
