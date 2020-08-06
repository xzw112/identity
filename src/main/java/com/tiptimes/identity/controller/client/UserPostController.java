package com.tiptimes.identity.controller.client;

import com.tiptimes.identity.common.ResponseResult;
import com.tiptimes.identity.entity.UserDepartment;
import com.tiptimes.identity.entity.UserPost;
import com.tiptimes.identity.service.UserPostService;
import com.tiptimes.identity.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/customer/userPost")
@Api(description = "用户岗位")
public class UserPostController {

    @Autowired
    private UserPostService userPostService;


    /**
     * 根据岗位id获取用户
     * @param postId
     * @return
     */
    @RequestMapping(value = "/getUserByPostId", method = RequestMethod.POST)
    @ApiOperation(value = "根据岗位id获取用户")
    public ResponseResult getUserByPostId(Integer postId){
        List<UserVo> list = null;
        if (postId > 0) {
            list = userPostService.selectUserByPostId(postId);
        }
        return ResponseResult.successWithData(list);
    }

    @RequestMapping(value = "/getDetail", method = RequestMethod.POST)
    @ApiOperation(value = "详情", hidden = true)
    public ResponseResult getDetail(Integer id){
        UserPost userPost = null;
        if (id > 0) {
            userPost = userPostService.selectDetail(id);
        }
        return ResponseResult.successWithData(userPost);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation(value = "编辑", hidden = true)
    public ResponseResult edit(@RequestBody UserPost userPost){
        int num = userPostService.updateById(userPost);
        return ResponseResult.successWithData(num);
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ApiOperation(value = "删除", hidden = true)
    public ResponseResult del(Integer id) {
        int num = userPostService.del(id);
        return ResponseResult.successWithData(num);
    }
}
