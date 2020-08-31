package com.tiptimes.identity.qo;

import lombok.Data;

import java.io.Serializable;

@Data
public class OpinionRequest extends BaseReauest implements Serializable {

    private String userId;

    private String opinionId;

    private Integer opinionType;

    private String opinionTitle;

}
