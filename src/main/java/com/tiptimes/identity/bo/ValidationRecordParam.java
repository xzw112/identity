package com.tiptimes.identity.bo;

import lombok.Data;

@Data
public class ValidationRecordParam extends BaseParam{

    private String certificateNo;//身份验证使用

    private String regYear;

    private String keyWord;

    private String validationUser;

}
