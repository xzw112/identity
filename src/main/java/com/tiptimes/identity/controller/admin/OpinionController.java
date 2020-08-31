package com.tiptimes.identity.controller.admin;

import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.common.ResponseResult;
import com.tiptimes.identity.entity.Opinion;
import com.tiptimes.identity.qo.OpinionRequest;
import com.tiptimes.identity.service.OpinionService;
import com.tiptimes.identity.vo.OpinionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/opinion")
public class OpinionController {

    @Autowired
    private OpinionService opinionService;

    @RequestMapping(value = "/getOpinionList", method = RequestMethod.POST)
    public PageResult<OpinionVo> getOpinionList(@RequestBody OpinionRequest opinionRequest){
        PageResult<OpinionVo> result = opinionService.selectOpinionList(opinionRequest);
        return result;
    }

    @RequestMapping(value = "/getDetailById", method = RequestMethod.POST)
    public ResponseResult getDetailById(String opinionId){
        Opinion opinion = opinionService.selectDetailById(opinionId);
        return ResponseResult.successWithData(opinion);

    }
}
