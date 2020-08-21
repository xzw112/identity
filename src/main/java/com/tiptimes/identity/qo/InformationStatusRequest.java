package com.tiptimes.identity.qo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class InformationStatusRequest implements Serializable {

    private String id;

    private Integer release;

    private Integer isRelease;

    private Date updateTime;


}
