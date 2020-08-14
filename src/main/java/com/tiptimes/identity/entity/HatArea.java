package com.tiptimes.identity.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class HatArea implements Serializable {
    private Long rid;

    private String areaId;

    private String area;

    private String father;

}