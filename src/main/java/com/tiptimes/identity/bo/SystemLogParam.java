package com.tiptimes.identity.bo;

import lombok.Data;

@Data
public class SystemLogParam extends BaseParam{

    private String modules;//模块

    private String operateType;//操作类型

    private String operatorName;//操作人员

    private String startTime;//开始时间

    private String endTime;//结束时间
}
