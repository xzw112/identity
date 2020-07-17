package com.tiptimes.identity.bo;

import lombok.Data;

@Data
public class SysMsgParam extends BaseParam{

    private String currentUserId;

    private String receiveObj;

    private Integer status;

    private Integer id;

}
