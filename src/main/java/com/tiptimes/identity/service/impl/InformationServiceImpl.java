package com.tiptimes.identity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.dao.InformationMapper;
import com.tiptimes.identity.entity.Information;
import com.tiptimes.identity.qo.InformationRequest;
import com.tiptimes.identity.qo.InformationStatusRequest;
import com.tiptimes.identity.service.InformationService;
import com.tiptimes.identity.utils.CurrentUserUtil;
import com.tiptimes.identity.utils.UUIDUtil;
import com.tiptimes.identity.vo.InformationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class InformationServiceImpl implements InformationService {
    @Autowired
    private InformationMapper informationMapper;

    @Override
    public PageResult<InformationVo> getInformationList(InformationRequest informationRequest) {
        PageHelper.startPage(informationRequest.getPageNumber(),informationRequest.getPageSize());
        List<InformationVo> list = informationMapper.getInformationList(informationRequest);
        PageInfo<InformationVo> pageInfo = new PageInfo<>(list);
        PageResult result = new PageResult();
        result.setRows(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public Information selectDetailById(String id) {
        return informationMapper.selectDetailById(id);
    }

    @Override
    public int deleteById(String id) {
        return informationMapper.deleteById(id);
    }

    @Override
    public int insert(Information information) {
        information.setCreateTime(new Timestamp(System.currentTimeMillis()));
        if (information.getIsRelease() == 1){
            information.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        }
        information.setId(UUIDUtil.getUUID());
        information.setCreateUser(CurrentUserUtil.getCurrentUserId());
        return informationMapper.insert(information);
    }

    @Override
    public int updateById(Information information) {
        information.setUpdateUser(CurrentUserUtil.getCurrentUserId());
        return informationMapper.updateById(information);
    }

    @Override
    public int updateDel(String id) {
        return informationMapper.updateDel(id);
    }

    @Override
    public int updateUnDel(String id) {
        return informationMapper.updateUnDel(id);
    }

    @Override
    public int updateInformationStatus(InformationStatusRequest informationStatusRequest) {
        if (informationStatusRequest.getRelease().equals(1)) {
            informationStatusRequest.setIsRelease(0);
            informationStatusRequest.setUpdateTime(null);
        }
        if (informationStatusRequest.getRelease().equals(0)) {
            informationStatusRequest.setIsRelease(1);
            informationStatusRequest.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        }
        int num = informationMapper.updateInformationStatus(informationStatusRequest);
        return num;
    }
}
