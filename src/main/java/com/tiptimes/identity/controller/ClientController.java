package com.tiptimes.identity.controller;

import com.tiptimes.identity.common.ErrorConstants;
import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.common.ResponseCodeEnums;
import com.tiptimes.identity.common.ResponseResult;
import com.tiptimes.identity.entity.OauthClientDetails;
import com.tiptimes.identity.qo.ClientRequest;
import com.tiptimes.identity.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 应用管理
 */
@RestController
@RequestMapping("/customer/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    /**
     * 获取列表
     * @param clientRequest
     * @return
     */
    @RequestMapping("/getClientList")
    public PageResult getClientList(@RequestBody ClientRequest clientRequest){
        PageResult<OauthClientDetails> list = clientService.selectClientList(clientRequest);
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

    @RequestMapping("/edit")
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
    @RequestMapping("/add")
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
    @RequestMapping("/del")
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

}
