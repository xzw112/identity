package com.tiptimes.identity.controller.client;

import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.common.ResponseResult;
import com.tiptimes.identity.entity.Opinion;
import com.tiptimes.identity.qo.OpinionRequest;
import com.tiptimes.identity.service.OpinionService;
import com.tiptimes.identity.vo.OpinionVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer/opinion")
@Api(description = "意见反馈")
public class OpinionClientController {

    @Autowired
    private OpinionService opinionService;

    @RequestMapping(value = "/getOpinionList", method = RequestMethod.POST)
    @ApiOperation("获取登录人的意见反馈记录")
    public PageResult<OpinionVo> getOpinionList(@RequestBody OpinionRequest opinionRequest){
        PageResult<OpinionVo> result = opinionService.selectOpinionList(opinionRequest);
        return result;
    }

    @RequestMapping(value = "/getOpinionDetail", method = RequestMethod.POST)
    @ApiOperation("获取意见详情")
    public ResponseResult getOpinionDetail(String opinionId){
        Opinion opinion = opinionService.selectDetailById(opinionId);
        return ResponseResult.successWithData(opinion);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation("新增意见反馈")
    public ResponseResult insert(@RequestBody Opinion opinion){
        int num = opinionService.insert(opinion);
        return ResponseResult.successWithData(num);
    }
}
