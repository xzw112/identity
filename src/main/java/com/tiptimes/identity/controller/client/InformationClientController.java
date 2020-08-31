package com.tiptimes.identity.controller.client;


import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.common.ResponseResult;
import com.tiptimes.identity.entity.Information;
import com.tiptimes.identity.qo.InformationRequest;
import com.tiptimes.identity.service.InformationService;
import com.tiptimes.identity.vo.InformationVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer/information")
@Api(description = "消息中心")
public class InformationClientController {

    @Autowired
    private InformationService informationService;

    @RequestMapping(value = "/getInformationList", method = RequestMethod.POST)
    @ApiOperation("获取消息列表")
    public PageResult<InformationVo> getInformationList(@RequestBody InformationRequest informationRequest){
        PageResult<InformationVo> result = informationService.getInformationList(informationRequest);
        return result;
    }

    @RequestMapping(value = "/getInformationDetail", method = RequestMethod.POST)
    @ApiOperation("获取消息详情")
    public ResponseResult getInformationDetail(String id){
        Information information = informationService.selectDetailById(id);
        return ResponseResult.successWithData(information);

    }
}
