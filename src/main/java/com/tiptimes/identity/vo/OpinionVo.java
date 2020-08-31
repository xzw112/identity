package com.tiptimes.identity.vo;

import com.tiptimes.identity.entity.Opinion;
import lombok.Data;

import java.io.Serializable;

@Data
public class OpinionVo extends Opinion implements Serializable {

    private String createTimeStr;

    private String userName;
}
