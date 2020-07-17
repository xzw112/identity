package com.tiptimes.identity.entity;


import lombok.Data;

import javax.persistence.Id;

@Data
public class UserDepartment {

    @Id
    private Integer id;

    private Integer departmentId;

    private String userId;

}