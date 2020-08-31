package com.tiptimes.identity.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TreeEntity implements Serializable {

    private Integer id;

    private Integer parentId;

    private String text;

    private List<TreeEntity> children;
}
