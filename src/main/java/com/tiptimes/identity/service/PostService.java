package com.tiptimes.identity.service;

import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.entity.Post;
import com.tiptimes.identity.qo.PostRequest;

public interface PostService {

    PageResult<Post> selectPostList(PostRequest postRequest);

    Post selectDetail(Integer id);

    int updateById(Post post);

    int insert(Post record);

    int del(Integer id);
}
