package com.java.biz;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BizDaoImp implements BizDao {

    private final BizMapper bizMapper;

    @Override
    public List<BizDTO> findList() {
        return bizMapper.findList();
    }
}
