package com.tiptimes.identity.controller;

import com.tiptimes.identity.annotation.SystemLog;
import com.tiptimes.identity.common.*;
import com.tiptimes.identity.entity.OauthClientDetails;
import com.tiptimes.identity.qo.ClientRequest;
import com.tiptimes.identity.service.ClientService;
import com.tiptimes.identity.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 应用管理
 */
@RestController
@RequestMapping("/customer/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    /**
     * 获取列表--应用管理用
     * @param clientRequest
     * @return
     */
    @RequestMapping("/getClientList")
    public PageResult getClientList(@RequestBody ClientRequest clientRequest){
        PageResult<OauthClientDetails> list = clientService.selectClientList(clientRequest);
        if (list.getRows() != null && list.getRows().size() > 0) {
            List<OauthClientDetails> rowList = list.getRows();
            for (int i = 0; i < rowList.size(); i++) {
                rowList.get(i).setCreateTimeStr(DateUtil.dateMinuteToStr(rowList.get(i).getCreateTime()));
            }
            list.setRows(rowList);
        }
        return list;
    }

    /**
     * 获取列表 --应用授权用
     * @param clientRequest
     * @return
     */
    @RequestMapping("/getClientListByType")
    public PageResult getClientListByType(@RequestBody ClientRequest clientRequest){
        PageResult<OauthClientDetails> list = clientService.selectClientListByType(clientRequest);
        if (list.getRows() != null && list.getRows().size() > 0) {
            List<OauthClientDetails> rowList = list.getRows();
            for (int i = 0; i < rowList.size(); i++) {
                rowList.get(i).setCreateTimeStr(DateUtil.dateMinuteToStr(rowList.get(i).getCreateTime()));
            }
            list.setRows(rowList);
        }
        return list;
    }

    /**
     * 获取详情
     * @param clientId
     * @return
     */
    @RequestMapping("/getClientDetail")
    public ResponseResult getClientDetail(String clientId){
        ResponseResult result = new ResponseResult();
        OauthClientDetails details = clientService.selectDetail(clientId);
        if (details != null) {
            result.setData(details);
            result.setCode(ResponseCodeEnums.SUCCESS.getCode());
            result.setMessage(ErrorConstants.SELECT_OK);
        } else {
            result.setCode(ResponseCodeEnums.FAILURE.getCode());
            result.setMessage(ErrorConstants.SELECT_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @SystemLog(operateType = OperateTypeConstant.MODIFY, operateDetail = "编辑应用", moduleName = "应用-应用列表")
    public ResponseResult edit(@RequestBody OauthClientDetails oauthClientDetails){
        ResponseResult result = new ResponseResult();
        int num = clientService.updateById(oauthClientDetails);
        if (num > 0) {
            result.setCode(ResponseCodeEnums.SUCCESS.getCode());
            result.setMessage(ErrorConstants.SAVE_OK);
        } else {
            result.setCode(ResponseCodeEnums.FAILURE.getCode());
            result.setMessage(ErrorConstants.SAVE_ERROR);
        }
        return result;
    }

    /**
     * 添加应用
     * @param oauthClientDetails
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @SystemLog(operateType = OperateTypeConstant.ADD, operateDetail = "添加应用", moduleName = "应用-应用列表")
    public ResponseResult add(@RequestBody OauthClientDetails oauthClientDetails){
        ResponseResult result = new ResponseResult();
        int num = clientService.insert(oauthClientDetails);
        if (num > 0) {
            result.setCode(ResponseCodeEnums.SUCCESS.getCode());
            result.setMessage(ErrorConstants.SAVE_OK);
        } else {
            result.setCode(ResponseCodeEnums.FAILURE.getCode());
            result.setMessage(ErrorConstants.SAVE_ERROR);
        }
        return result;
    }

    /**
     * 删除应用
     * @param clientId
     * @return
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @SystemLog(operateType = OperateTypeConstant.DELETE, operateDetail = "删除应用", moduleName = "应用-应用列表")
    public ResponseResult del(String[] clientId){
        ResponseResult result = new ResponseResult();
        int num = clientService.del(clientId);
        if (num > 0) {
            result.setCode(ResponseCodeEnums.SUCCESS.getCode());
            result.setMessage(ErrorConstants.DELETE_OK);
        } else {
            result.setCode(ResponseCodeEnums.FAILURE.getCode());
            result.setMessage(ErrorConstants.DELETE_ERROR);
        }
        return result;
    }

    /**
     * 查询内部应用数量
     * @return
     */
    @GetMapping(value = "/selectInClientCount")
    public ResponseResult selectInClientCount(){
        int num = clientService.selectInClientCount();
        return ResponseResult.successWithData(num);
    }

    /**
     * 查询外部应用数量
     * @return
     */
    @GetMapping(value = "/selectOutClientCount")
    public ResponseResult selectOutClientCount(){
        int num = clientService.selectOutClientCount();
        return ResponseResult.successWithData(num);
    }

}
