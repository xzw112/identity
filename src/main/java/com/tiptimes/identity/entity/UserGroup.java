package com.tiptimes.identity.entity;


import lombok.Data;

import javax.persistence.Id;

@Data
public class UserGroup {

    @Id
    private Integer id;

    private Integer groupId;

    private String userId;

}