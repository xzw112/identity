package com.tiptimes.identity.vo;

import com.tiptimes.identity.entity.Post;
import lombok.Data;

import java.io.Serializable;

@Data
public class PostVo extends Post implements Serializable {

    private Integer departmentId;

    private String departmentName;


}
