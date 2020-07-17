package com.tiptimes.identity.bo;

import lombok.Data;

@Data
public class AdminUserParam extends BaseParam{

    private String loginPassword;//原始登录密码

    private String newPassword;//新密码

    private String id;//用户id

    private String enterpriseId;//单位id
}
