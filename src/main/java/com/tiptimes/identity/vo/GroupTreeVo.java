package com.tiptimes.identity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroupTreeVo implements Serializable {

    private Integer id;
    private String parent;
    private String text;
}
