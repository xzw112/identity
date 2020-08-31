package com.tiptimes.identity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.dao.OpinionMapper;
import com.tiptimes.identity.entity.Opinion;
import com.tiptimes.identity.qo.OpinionRequest;
import com.tiptimes.identity.service.OpinionService;
import com.tiptimes.identity.utils.UUIDUtil;
import com.tiptimes.identity.vo.OpinionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class OpinionServiceImpl implements OpinionService {

    @Autowired
    private OpinionMapper opinionMapper;

    @Override
    public PageResult<OpinionVo> selectOpinionList(OpinionRequest opinionRequest) {
        PageHelper.startPage(opinionRequest.getPageNumber(), opinionRequest.getPageSize());
        List<OpinionVo> list = opinionMapper.selectOpinionList(opinionRequest);
        PageInfo<OpinionVo> pageInfo = new PageInfo<>(list);
        PageResult<OpinionVo> result = new PageResult<>();
        result.setTotal(pageInfo.getTotal());
        result.setRows(list);
        return result;
    }

    @Override
    public int insert(Opinion opinion) {
        opinion.setId(UUIDUtil.getUUID());
        opinion.setCreateTime(new Timestamp(System.currentTimeMillis()));
        int num = opinionMapper.insert(opinion);
        return num;
    }

    @Override
    public Opinion selectDetailById(String id) {
        return opinionMapper.selectDetailById(id);
    }
}
