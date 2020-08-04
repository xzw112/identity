package com.tiptimes.identity.service;

import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.entity.Post;
import com.tiptimes.identity.qo.PostRequest;
import com.tiptimes.identity.vo.PostVo;

public interface PostService {

    PageResult<PostVo> selectPostList(PostRequest postRequest);

    Post selectDetail(Integer id);

    int updateById(Post post);

    int insert(Post record);

    int del(Integer id);
}
