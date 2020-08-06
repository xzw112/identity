package com.tiptimes.identity.vo;

import com.tiptimes.identity.common.Constants;
import lombok.Data;

import java.io.Serializable;

@Data
public class OutUserVo implements Serializable {

    private String userId;

    private String loginName; // 登录账号

    private String loginPassword = Constants.VIRTUAL_PASSWORD;

    private String nickName; // 昵称

    private Integer sex;

    private String createTime;// 注册时间

    private String ipAddr; // 注册ip

    private Integer status; // 状态 (0启用、1禁用)

    private Integer userOutType; //外部用户类型（1普通 2学生)
}
