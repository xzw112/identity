package com.tiptimes.identity.controller.admin;

import com.tiptimes.identity.bo.SystemLogParam;
import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.qo.LoginSystemLogRequest;
import com.tiptimes.identity.service.LoginSystemLogService;
import com.tiptimes.identity.service.TpMainSystemLogService;
import com.tiptimes.identity.vo.LoginSystemLogVo;
import com.tiptimes.identity.vo.TpMainSystemLogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 操作日志
 */
@RestController
@RequestMapping("/admin/systemLog")
public class SystemLogController {

    @Autowired
    private TpMainSystemLogService tpMainSystemLogService;

    @Autowired
    private LoginSystemLogService loginSystemLogService;

    /**
     * 获取操作日志列表
     * @param systemLogParam
     * @return
     */
    @PostMapping("/getList")
    public PageResult<TpMainSystemLogVO> getList(@RequestBody SystemLogParam systemLogParam) {
        return tpMainSystemLogService.selectList(systemLogParam);
    }

    @PostMapping("/getLoginLogList")
    public PageResult<LoginSystemLogVo> getLoginLogList(@RequestBody LoginSystemLogRequest loginSystemLogRequest){
        PageResult<LoginSystemLogVo> result = loginSystemLogService.selectList(loginSystemLogRequest);
        return result;
    }
}
