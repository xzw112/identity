package com.tiptimes.identity.controller;

import com.tiptimes.identity.common.ResponseResult;
import com.tiptimes.identity.entity.UserDepartment;
import com.tiptimes.identity.entity.UserPost;
import com.tiptimes.identity.service.UserPostService;
import com.tiptimes.identity.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户岗位
 */
@RestController
@RequestMapping("/userPost")
public class UserPostController {

    @Autowired
    private UserPostService userPostService;


    /**
     * 根据岗位id获取用户
     * @param postId
     * @return
     */
    @RequestMapping(value = "/getUserByDepartmentId", method = RequestMethod.POST)
    public ResponseResult getUserByDepartmentId(Integer postId){
        List<UserVo> list = null;
        if (postId > 0) {
            list = userPostService.selectUserByPostId(postId);
        }
        return ResponseResult.successWithData(list);
    }

    @RequestMapping(value = "/getDetail", method = RequestMethod.POST)
    public ResponseResult getDetail(Integer id){
        UserPost userPost = null;
        if (id > 0) {
            userPost = userPostService.selectDetail(id);
        }
        return ResponseResult.successWithData(userPost);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResponseResult edit(@RequestBody UserPost userPost){
        int num = userPostService.updateById(userPost);
        return ResponseResult.successWithData(num);
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public ResponseResult del(Integer id) {
        int num = userPostService.del(id);
        return ResponseResult.successWithData(num);
    }
}
