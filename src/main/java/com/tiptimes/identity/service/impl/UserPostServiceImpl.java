package com.tiptimes.identity.service.impl;

import com.tiptimes.identity.dao.UserPostMapper;
import com.tiptimes.identity.entity.UserPost;
import com.tiptimes.identity.qo.PostRequest;
import com.tiptimes.identity.service.UserPostService;
import com.tiptimes.identity.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPostServiceImpl implements UserPostService {

    @Autowired
    private UserPostMapper userPostMapper;

    @Override
    public List<UserVo> selectUserByPostId(Integer postId) {
        return userPostMapper.selectUserByPostId(postId);
    }

    @Override
    public UserPost selectDetail(Integer id) {
        return userPostMapper.selectDetail(id);
    }

    @Override
    public int insert(UserPost userPost) {
        return userPostMapper.insert(userPost);
    }

    @Override
    public int updateById(UserPost userPost) {
        return userPostMapper.updateById(userPost);
    }

    @Override
    public int del(Integer id) {
        return userPostMapper.del(id);
    }
}
