package com.tiptimes.identity.bo;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class AthleteInfoParam extends BaseParam{

    private String id;//申请id

    private Integer queryCurrentTable;

    private Integer type;

    private String regYear;//注册年度

    private String athleteId;//运动员id

    private Integer auditStatus;//审核状态

    private Integer status;//状态 1首次注册  2年度确认 3重新注册

    private Integer isReport;//是否上报

    private Integer isReported;//是否已经上报

    private String returnReason;//退回原因

    private String updateUser;

    private Date updateTime;

    private Integer isAgreementExpire;//协议到期

    private String enterpriseId;//人员所属单位

    //筛选项
    private String keyWord;//姓名/证件号

    private Integer sex;//性别

    private String regItem;//注册项目

    private String regSubItem;//注册项目子类别

    private String term;//兼项

    private String subTerm;//兼项子类别

    private String behalfUnit;//代表单位

    private String behalfSubUnit;//子代表单位

    private Integer certificateType;//证件类型

    private Integer isForeignProvince;//是否外省

    private Integer statusSelect;//状态 1首次/重新注册  2年度确认

    private Integer age;//年龄

    private List<String> itemIds;//中心用户项目权限集合

}
