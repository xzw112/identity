package com.tiptimes.identity.controller;

import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.common.ResponseResult;
import com.tiptimes.identity.entity.Department;
import com.tiptimes.identity.entity.Post;
import com.tiptimes.identity.qo.DepartmentRequest;
import com.tiptimes.identity.qo.PostRequest;
import com.tiptimes.identity.service.PostService;
import com.tiptimes.identity.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 岗位
 */
@RestController
@RequestMapping("/customer/post")
public class PostController {

    @Autowired
    private PostService postService;

    /**
     * 获取岗位列表
     * @return
     */
    @RequestMapping(value = "/getPostList", method = RequestMethod.POST)
    public PageResult getPostList(@RequestBody PostRequest postRequest) {
        PageResult<PostVo> list = postService.selectPostList(postRequest);
        return list;
    }

    /**
     * 获取岗位详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/getPostDetail", method = RequestMethod.POST)
    public ResponseResult getPostDetail(Integer id){
        Post post = postService.selectDetail(id);
        return ResponseResult.successWithData(post);

    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResponseResult edit(@RequestBody Post post){
        int num = postService.updateById(post);
        return ResponseResult.successWithData(num);
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public ResponseResult del(Integer id){
        int num = postService.del(id);
        return ResponseResult.successWithData(num);
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseResult add(@RequestBody Post post, HttpServletRequest request){
        String userId = (String)request.getSession().getAttribute("userId");
        post.setCreateUser(userId);
        int num = postService.insert(post);
        return ResponseResult.successWithData(num);
    }
}
