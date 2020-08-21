package com.tiptimes.identity.service;

import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.qo.LoginSystemLogRequest;
import com.tiptimes.identity.vo.LoginSystemLogVo;

public interface LoginSystemLogService {

    PageResult<LoginSystemLogVo> selectList(LoginSystemLogRequest loginSystemLogRequest);
}
