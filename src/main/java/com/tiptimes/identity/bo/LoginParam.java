package com.tiptimes.identity.bo;

import lombok.Data;

@Data
public class LoginParam{

    private String loginName;//账号

    private String loginPassword;//密码

    private String code;//验证码
}
