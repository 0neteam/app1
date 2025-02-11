package com.java.biz;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

@Mapper
public interface BizMapper {


    @Select({"<script>" +
            "SELECT bizNo,bizName,bizType FROM stg_client" +
            "</script>"})
    public List<BizDTO> findList();

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
