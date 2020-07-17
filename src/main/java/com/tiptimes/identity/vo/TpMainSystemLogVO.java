package com.tiptimes.identity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;

@Data
public class TpMainSystemLogVO {

    private String id;

    private String operatorName;

    private String operatorNumber;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date operateTime;

    private String modules;

    private String operateType;

    private String operateTerminal;

    private String operateDetail;

    private String ipAddressStr;

    private String remark;

}