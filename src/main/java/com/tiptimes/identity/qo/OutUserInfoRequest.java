package com.tiptimes.identity.qo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class OutUserInfoRequest implements Serializable {

    @ApiModelProperty("用户Id")
    private String id;
    @ApiModelProperty("用户姓名")
    private String userName;
    @ApiModelProperty("性别 （1男 0女）")
    private Integer sex;
    @ApiModelProperty("民族")
    private String nation;
    @ApiModelProperty("省")
    private Integer province;
    @ApiModelProperty("市")
    private Integer city;
    @ApiModelProperty("区县")
    private Integer county;
    @ApiModelProperty("邮政编码")
    private String postCode;
    @ApiModelProperty("详细地址")
    private String userAddress;
    @ApiModelProperty("联系方式")
    private String userContact;
    @ApiModelProperty("出生日期")
    private String birth;
    @ApiModelProperty("学历")
    private String education;
}
