package com.tiptimes.identity.vo;

import com.tiptimes.identity.entity.Group;
import lombok.Data;

import java.io.Serializable;

@Data
public class GroupVo extends Group implements Serializable {

    private String groupParentName;

    private String departmentName;
}
