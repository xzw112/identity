package com.tiptimes.identity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.dao.LoginSystemLogMapper;
import com.tiptimes.identity.entity.LoginSystemLog;
import com.tiptimes.identity.qo.LoginSystemLogRequest;
import com.tiptimes.identity.service.LoginSystemLogService;
import com.tiptimes.identity.vo.LoginSystemLogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginSystemLogServiceImpl implements LoginSystemLogService {
    @Autowired
    private LoginSystemLogMapper loginSystemLogMapper;

    @Override
    public PageResult<LoginSystemLogVo> selectList(LoginSystemLogRequest loginSystemLogRequest) {
        PageHelper.startPage(loginSystemLogRequest.getPageNumber(), loginSystemLogRequest.getPageSize());
        List<LoginSystemLogVo> list = loginSystemLogMapper.selectList(loginSystemLogRequest);
        PageInfo<LoginSystemLogVo> pageInfo = new PageInfo<>(list);
        PageResult<LoginSystemLogVo> pageResult = new PageResult<>();
        pageResult.setTotal(pageInfo.getTotal());
        pageResult.setRows(list);
        return pageResult;
    }
}
