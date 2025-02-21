package com.java.biz;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import com.java.common.JwtToken;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class BizDaoImp implements BizDao {

    private final BizMapper bizMapper;
    private final JwtToken jwtToken;

	@Value("${server.domain1}")
    private String domain;

    @Override
    public List<BizDTO> findList(BizReqDTO bizReqDTO) {
        return bizMapper.findList(bizReqDTO);
    }

    @Override
    public BizDTO findOne(int no) {
        BizDTO bizDTO = bizMapper.findOne(no);
        bizDTO.setApiKeys(bizMapper.findByApiKey(no));
        return bizDTO;
    }

    public boolean update(BizDTO bizDTO) {
        int state = bizMapper.updateBiz(bizDTO);
        if(state == 1) {
            for(BizApiKeyDTO bizApiKeyDTO : bizDTO.getApiKeys()) {
                state += bizMapper.updateApi(bizApiKeyDTO);
            }
            if(state == 3) return true;
        }
        return false;
    }

    public boolean delete(int bizNo) {
        int state = bizMapper.deleteBiz(bizNo);
        state += bizMapper.deleteApi(bizNo);
        if(state == 3) return true;
        return false;
    }

    public boolean create(BizDTO bizDTO) {
        List<BizApiKeyDTO> apiKeys = bizDTO.getApiKeys();
        int state = bizMapper.createBiz(bizDTO);
        if(state == 1) {
            if(apiKeys == null) {
                String key = jwtToken.setToken(bizDTO.getBizNo() + "");
                key = key.split(" ")[1];
                apiKeys = new ArrayList<BizApiKeyDTO>();
                apiKeys.add(BizApiKeyDTO.builder().bizNo(bizDTO.getBizNo()).type("order").url(domain+"/api/order/").key(key).build());
                apiKeys.add(BizApiKeyDTO.builder().bizNo(bizDTO.getBizNo()).type("list").url(domain+"/api/list").key(key).build());
                bizDTO.setApiKeys(apiKeys);
            }
            for(BizApiKeyDTO bizApiKeyDTO : apiKeys) {
                bizApiKeyDTO.setBizNo(bizDTO.getBizNo());
                state += bizMapper.createApi(bizApiKeyDTO);
            }
            if(state == 3) return true;
        }
        return false;
    }

    public BizDTO findByEmail(String email) {
        return bizMapper.findByEmail(email);
    }

    public int checkemail(String email) {
        return bizMapper.checkemail(email);
    }

    public int loginpwdupdate(BizDTO bizDTO) {
        return bizMapper.loginpwdupdate(bizDTO);
    }

}
