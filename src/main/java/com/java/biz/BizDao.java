package com.java.biz;
import java.util.List;

public interface BizDao {
    public List<BizDTO> findList();

    int create(BizDTO biz);

    BizDTO findOne(int no);
}
