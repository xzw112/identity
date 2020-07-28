package com.tiptimes.identity.qo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PostRequest extends BaseReauest implements Serializable {
    // 岗位名称
    private String postName;

    private Integer postId;
}
