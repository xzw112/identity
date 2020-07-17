package com.tiptimes.identity.entity;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
public class DictionaryValue implements Serializable {

    @Id
    private Integer id;

    private Integer dictionaryId;

    private String name;

    private Integer order;

    private Integer isDel;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private String createUser;

    private String updateUser;
}
