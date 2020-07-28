package com.tiptimes.identity.service;

import com.tiptimes.identity.entity.UserPost;
import com.tiptimes.identity.vo.UserVo;

import java.util.List;

public interface UserPostService {

    // 根据岗位id查询用户
    List<UserVo> selectUserByPostId(Integer postId);

    UserPost selectDetail(Integer id);

    int insert(UserPost userPost);

    int updateById(UserPost userPost);

    int del(Integer id);
}
