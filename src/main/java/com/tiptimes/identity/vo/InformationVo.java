package com.tiptimes.identity.vo;

import com.tiptimes.identity.entity.Information;
import lombok.Data;

import java.io.Serializable;

@Data
public class InformationVo extends Information implements Serializable {

    private String userName;
    private String loginName;
    private String createTimeStr;
    private String releaseTime; // 发布时间
}
