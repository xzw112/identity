package com.tiptimes.identity.dao;

import com.tiptimes.identity.entity.Post;
import com.tiptimes.identity.qo.PostRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostMapper {

    List<Post> selectPostList(PostRequest postRequest);

    Post selectDetail(Integer id);

    int updateById(Post post);

    int insert(Post record);

    int del(Integer id);
}