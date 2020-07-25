package com.tiptimes.identity.qo;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroupRequest extends BaseReauest implements Serializable {

    private String groupName;

    private Integer groupId;
}
