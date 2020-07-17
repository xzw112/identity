package com.tiptimes.identity.entity;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

@Data
public class DictionaryType {

    @Id
    private Integer id;

    private Integer parentId;

    private String name;

    private Integer order;

    private Date createTime;

    private Date updateTime;

    private String createUser;

    private String updateUser;

    private Integer isDel;

    private Integer status;

}