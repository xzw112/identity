package com.tiptimes.identity.service;

import com.tiptimes.identity.bo.SystemLogParam;
import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.vo.TpMainSystemLogVO;

/**
 * 操作日志 Service接口
 */
public interface TpMainSystemLogService {

    /**
     * 通过条件查询数据
      * @param systemLogParam
     * @return
     */
    PageResult<TpMainSystemLogVO> selectList(SystemLogParam systemLogParam);
}