package com.tiptimes.identity.controller.admin;

import com.tiptimes.identity.common.ErrorConstants;
import com.tiptimes.identity.common.ResponseCodeEnums;
import com.tiptimes.identity.common.ResponseResult;
import com.tiptimes.identity.entity.OauthClientDetails;
import com.tiptimes.identity.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

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
}
