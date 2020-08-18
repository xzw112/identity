package com.tiptimes.identity.vo;

import com.tiptimes.identity.entity.TpMainAdminUser;
import lombok.Data;

import java.io.Serializable;

@Data
public class WXCallBackVo implements Serializable {

    private String access_token;

    private TpMainAdminUser userInfo;
}
