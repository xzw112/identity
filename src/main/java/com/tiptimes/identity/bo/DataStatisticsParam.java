package com.tiptimes.identity.bo;

import lombok.Data;

@Data
public class DataStatisticsParam extends BaseParam{

    private String regYear;//注册年度

    private String parentId;//父级id

    private String enterpriseId;//注册单位id

    private Integer sex;

    private Integer age;

    private String regItem;//项目

}
