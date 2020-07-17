package com.tiptimes.identity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tiptimes.identity.bo.RoleParam;
import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.dao.TpMainRoleMapper;
import com.tiptimes.identity.entity.TpMainRole;
import com.tiptimes.identity.enums.DataStatus;
import com.tiptimes.identity.service.TpMainRoleService;
import com.tiptimes.identity.utils.DateUtil;
import com.tiptimes.identity.vo.TpMainRoleVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

/**
 * 角色管理模块 Service层
 */
@Service
@Transactional
public class TpMainRoleServiceImpl implements TpMainRoleService {

    @Resource
    private TpMainRoleMapper tpMainRoleMapper;

    @Override
    public PageResult<TpMainRoleVO> selectPageList(RoleParam roleParam) {
        if(roleParam.getPageNumber() != null && roleParam.getPageSize() != null){
            PageHelper.startPage(roleParam.getPageNumber(), roleParam.getPageSize());
        }
        PageInfo<TpMainRoleVO> pageInfo = new PageInfo<>(tpMainRoleMapper.selectList(roleParam));
        List<TpMainRoleVO> list = pageInfo.getList();
        if (list.size() > 0) {
            for (TpMainRoleVO tpMainRoleVO : list) {
                //转换时间
                if(tpMainRoleVO.getUpdateTime() == null){
                    tpMainRoleVO.setUpdateTimeStr(DateUtil.dateTimeToStr(tpMainRoleVO.getCreateTime()));
                }else{
                    tpMainRoleVO.setUpdateTimeStr(DateUtil.dateTimeToStr(tpMainRoleVO.getUpdateTime()));
                }
            }
        }
        PageResult<TpMainRoleVO> pageResult = new PageResult<>();
        pageResult.setTotal(pageInfo.getTotal());
        pageResult.setRows(list);
        return pageResult;
    }

    @Override
    public int add(TpMainRole tpMainRole) {
        return tpMainRoleMapper.insert(tpMainRole);
    }

    @Override
    public int update(TpMainRole tpMainRole) {
        return tpMainRoleMapper.updateByPrimaryKeySelective(tpMainRole);
    }

    @Override
    public int batchDel(String[] idArr) {
        for(String id : idArr){
            TpMainRole tpMainRole = new TpMainRole();
            tpMainRole.setId(id);
            tpMainRole.setIsDelete(DataStatus.DELETED.getCode());
            tpMainRoleMapper.updateByPrimaryKeySelective(tpMainRole);
        }
        return 1;
    }
}
