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

    @Override
    public int create(BizDTO biz) {
        return bizMapper.create(biz);
    }

    @Override
    public BizDTO findOne(int no) {
        return bizMapper.findOne(no);
    }
}
