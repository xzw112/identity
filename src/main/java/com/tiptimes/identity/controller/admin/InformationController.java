package com.tiptimes.identity.controller.admin;

import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.common.ResponseResult;
import com.tiptimes.identity.entity.Information;
import com.tiptimes.identity.qo.InformationRequest;
import com.tiptimes.identity.qo.InformationStatusRequest;
import com.tiptimes.identity.service.InformationService;
import com.tiptimes.identity.vo.InformationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/information")
public class InformationController {

    @Autowired
    private InformationService informationService;

    @RequestMapping(value = "/getInformationList", method = RequestMethod.POST)
    public PageResult<InformationVo> getInformationList(@RequestBody InformationRequest informationRequest){
        PageResult<InformationVo> result = informationService.getInformationList(informationRequest);
        return result;
    }

    @RequestMapping(value = "/updateInformationStatus", method = RequestMethod.POST)
    public ResponseResult updateInformationStatus(@RequestBody InformationStatusRequest informationStatusRequest){
        int num = informationService.updateInformationStatus(informationStatusRequest);
        return ResponseResult.successWithData(num);
    }

    @RequestMapping(value = "/updateInformation", method = RequestMethod.POST)
    public ResponseResult updateInformation(@RequestBody Information information){
        int num = informationService.updateById(information);
        return ResponseResult.successWithData(num);
    }

    @RequestMapping(value = "/updateDel", method = RequestMethod.POST)
    public ResponseResult updateDel(String id){
        int num = informationService.updateDel(id);
        if (num > 0) {
            return ResponseResult.successWithData(num);
        } else {
            return ResponseResult.error("删除失败！");
        }
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public ResponseResult del(String id){
        int num = informationService.deleteById(id);
        return ResponseResult.successWithData(num);
    }

    @RequestMapping(value = "/unDel", method = RequestMethod.POST)
    public ResponseResult unDel(String id){
        int num = informationService.updateUnDel(id);
        return ResponseResult.successWithData(num);
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseResult add(@RequestBody Information information){
        int num = informationService.insert(information);
        return ResponseResult.successWithData(num);
    }


}
