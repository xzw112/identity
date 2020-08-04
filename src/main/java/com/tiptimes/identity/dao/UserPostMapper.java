package com.tiptimes.identity.dao;

import com.tiptimes.identity.entity.UserPost;
import com.tiptimes.identity.vo.UserVo;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserPostMapper {

    // 根据岗位id查询用户
    List<UserVo> selectUserByPostId(Integer postId);

    UserPost selectDetail(Integer id);

    int insert(UserPost userPost);

    int updateById(UserPost userPost);

    int del(Integer id);

    int delByUserId(String userId);
}