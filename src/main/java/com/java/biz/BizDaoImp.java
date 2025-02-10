package com.java.biz;

import java.util.List;

public class BizDaoImp implements BizDao {

    BizMapper bizMapper;

    @Override
    public List<BizDTO> findList() {
        return bizMapper.findList();
    }
}
