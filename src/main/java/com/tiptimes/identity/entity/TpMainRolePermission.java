package com.tiptimes.identity.entity;

import lombok.Data;
import javax.persistence.Id;

@Data
public class TpMainRolePermission {

    @Id
    private String id;

    private String roleId;

    private String permissionId;

    private String idAttribute;

}