package com.tiptimes.identity.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import javax.persistence.Id;
import java.util.Date;

@Data
public class TpMainSystemLog {

    @Id
    private String id;

    private String operatorName;

    private String operatorNumber;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date operateTime;

    private String modules;

    private String operateType;

    private String operateTerminal;

    private String operateDetail;

    private Integer ipAddress;

    private String remark;

}