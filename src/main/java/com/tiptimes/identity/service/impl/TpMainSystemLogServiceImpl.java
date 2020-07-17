package com.tiptimes.identity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tiptimes.identity.bo.SystemLogParam;
import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.dao.TpMainSystemLogMapper;
import com.tiptimes.identity.entity.TpMainSystemLog;
import com.tiptimes.identity.service.TpMainSystemLogService;
import com.tiptimes.identity.utils.IpV4Utils;
import com.tiptimes.identity.vo.TpMainSystemLogVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 操作日志模块 Service层
 */
@Service
@Transactional
public class TpMainSystemLogServiceImpl implements TpMainSystemLogService {

    @Resource
    private TpMainSystemLogMapper tpMainSystemLogMapper;

    @Override
    public PageResult<TpMainSystemLogVO> selectList(SystemLogParam systemLogParam) {
        Example example = new Example(TpMainSystemLog.class);
        //参数设置
        setParam(example, systemLogParam);
        if(systemLogParam.getPageNumber() != null && systemLogParam.getPageSize() != null){
            PageHelper.startPage(systemLogParam.getPageNumber(), systemLogParam.getPageSize());
        }
        PageInfo<TpMainSystemLog> pageInfo = new PageInfo<>(tpMainSystemLogMapper.selectByExample(example));
        PageResult<TpMainSystemLogVO> pageResult = new PageResult<>();
        pageResult.setTotal(pageInfo.getTotal());

        List<TpMainSystemLog> list = pageInfo.getList();
        List<TpMainSystemLogVO> systemLogVOList = new ArrayList<>();
        for (TpMainSystemLog tpMainSystemLog : list){
            TpMainSystemLogVO tpMainSystemLogVO = new TpMainSystemLogVO();
            BeanUtils.copyProperties(tpMainSystemLog, tpMainSystemLogVO);
            //转换ip
            tpMainSystemLogVO.setIpAddressStr(IpV4Utils.toIpAddress(tpMainSystemLog.getIpAddress()));
            systemLogVOList.add(tpMainSystemLogVO);
        }
        pageResult.setRows(systemLogVOList);
        return pageResult;
    }

    /**
     * 参数设置
     * @param example
     * @param systemLogParam
     */
    private void setParam(Example example, SystemLogParam systemLogParam){
        Example.Criteria criteria = example.createCriteria();
        // 设置查询条件
        if(StringUtils.isNotBlank(systemLogParam.getModules())){
            criteria.andLike("modules", "%" + systemLogParam.getModules() + "%");
        }
        if(StringUtils.isNotBlank(systemLogParam.getOperateType())){
            criteria.andLike("operateType", "%" + systemLogParam.getOperateType() + "%");
        }
        if(StringUtils.isNotBlank(systemLogParam.getOperatorName())){
            criteria.andLike("operatorName", "%" + systemLogParam.getOperatorName() + "%");
        }
        if(StringUtils.isNotBlank(systemLogParam.getStartTime()) &&
                StringUtils.isNotBlank(systemLogParam.getEndTime())){
            criteria.andBetween("operateTime", systemLogParam.getStartTime(), systemLogParam.getEndTime());
        }
        example.orderBy("operateTime").desc();
    }
}
