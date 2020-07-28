package com.tiptimes.identity.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserPost implements Serializable {
    private Integer id;

    private Integer postId;

    private Integer userId;
}