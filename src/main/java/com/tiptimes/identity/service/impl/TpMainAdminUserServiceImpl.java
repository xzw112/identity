package com.tiptimes.identity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tiptimes.identity.bo.AdminUserParam;
import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.dao.TpMainAdminUserMapper;
import com.tiptimes.identity.entity.TpMainAdminUser;
import com.tiptimes.identity.enums.DataStatus;
import com.tiptimes.identity.service.TpMainAdminUserService;
import com.tiptimes.identity.utils.DateUtil;
import com.tiptimes.identity.vo.TpMainAdminUserVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

/**
 * 后台人员信息模块 Service层
 */
@Service
@Transactional
public class TpMainAdminUserServiceImpl implements TpMainAdminUserService {

    @Resource
    private TpMainAdminUserMapper tpMainAdminUserMapper;

    @Override
    public PageResult<TpMainAdminUserVO> selectPageList(AdminUserParam adminUserParam) {
        if(adminUserParam.getPageNumber() != null && adminUserParam.getPageSize() != null){
            PageHelper.startPage(adminUserParam.getPageNumber(), adminUserParam.getPageSize());
        }
        PageInfo<TpMainAdminUserVO> pageInfo = new PageInfo<>(tpMainAdminUserMapper.selectList(adminUserParam));
        List<TpMainAdminUserVO> list = pageInfo.getList();
        if (list.size() > 0) {
            for (TpMainAdminUserVO tpMainAdminUserVO : list) {
                //转换时间
                if(tpMainAdminUserVO.getUpdateTime() == null){
                    tpMainAdminUserVO.setUpdateTimeStr(DateUtil.dateTimeToStr(tpMainAdminUserVO.getCreateTime()));
                }else{
                    tpMainAdminUserVO.setUpdateTimeStr(DateUtil.dateTimeToStr(tpMainAdminUserVO.getUpdateTime()));
                }
            }
        }
        PageResult<TpMainAdminUserVO> pageResult = new PageResult<>();
        pageResult.setTotal(pageInfo.getTotal());
        pageResult.setRows(list);
        return pageResult;
    }

    @Override
    public int add(TpMainAdminUser tpMainAdminUser) {
        return tpMainAdminUserMapper.insert(tpMainAdminUser);
    }

    @Override
    public int update(TpMainAdminUser tpMainAdminUser) {
        return tpMainAdminUserMapper.updateByPrimaryKeySelective(tpMainAdminUser);
    }

    @Override
    public int batchDel(String[] idArr) {
        for(String id : idArr){
            TpMainAdminUser tpMainAdminUser = new TpMainAdminUser();
            tpMainAdminUser.setId(id);
            tpMainAdminUser.setIsDelete(DataStatus.DELETED.getCode());
            tpMainAdminUserMapper.updateByPrimaryKeySelective(tpMainAdminUser);
        }
        return 1;
    }
}
