package com.tiptimes.identity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.dao.PostMapper;
import com.tiptimes.identity.entity.Post;
import com.tiptimes.identity.qo.PostRequest;
import com.tiptimes.identity.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;

    @Override
    public PageResult<Post> selectPostList(PostRequest postRequest) {
        PageHelper.startPage(postRequest.getPageNumber(), postRequest.getPageSize());
        List<Post> list = postMapper.selectPostList(postRequest);
        PageInfo<Post> pageInfo = new PageInfo<>(list);
        PageResult<Post> result = new PageResult<>();
        result.setTotal(pageInfo.getTotal());
        result.setRows(list);
        return result;
    }

    @Override
    public Post selectDetail(Integer id) {
        return postMapper.selectDetail(id);
    }

    @Override
    public int updateById(Post post) {
        return postMapper.updateById(post);
    }

    @Override
    public int insert(Post post) {
        post.setCreateTime(new Date());
        post.setIsDel(0);
        return postMapper.insert(post);
    }

    @Override
    public int del(Integer id) {
        return postMapper.del(id);
    }
}
