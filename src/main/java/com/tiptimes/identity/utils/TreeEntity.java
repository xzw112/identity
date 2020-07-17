package com.tiptimes.identity.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName TreeEntity
 * @Description: 树形结构实体
 * @Author user
 * @Date 2019/10/29
 * @Version V1.0
 **/
@Data
public class TreeEntity {

    private String id;

    private String text;

    private String parentId;

    private String type;

    private HashMap<String, Boolean> state;

    private Integer level;

    private List<TreeEntity> children;
}
