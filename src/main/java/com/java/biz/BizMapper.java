package com.java.biz;

import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BizMapper {


    @Select({"" +
            "SELECT bizNo,bizName,bizType FROM stg_client" +
            ""})
    public List<BizDTO> findList();

}
