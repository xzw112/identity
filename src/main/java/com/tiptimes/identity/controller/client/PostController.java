package com.tiptimes.identity.controller.client;

import com.tiptimes.identity.annotation.SystemLog;
import com.tiptimes.identity.common.OperateTypeConstant;
import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.common.ResponseResult;
import com.tiptimes.identity.entity.Department;
import com.tiptimes.identity.entity.Post;
import com.tiptimes.identity.qo.DepartmentRequest;
import com.tiptimes.identity.qo.PostRequest;
import com.tiptimes.identity.service.PostService;
import com.tiptimes.identity.vo.PostVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(description = "岗位")
public class PostController {

    @Autowired
    private PostService postService;

    /**
     * 获取岗位列表
     * @return
     */
    @RequestMapping(value = "/getPostList", method = RequestMethod.POST)
    @ApiOperation(value = "获取岗位列表")
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
    @ApiOperation(value = "获取岗位详情")
    public ResponseResult getPostDetail(Integer id){
        Post post = postService.selectDetail(id);
        return ResponseResult.successWithData(post);

    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation(value = "编辑", hidden = true)
    @SystemLog(operateType = OperateTypeConstant.MODIFY, operateDetail = "编辑岗位", moduleName = "用户-机构与组")
    public ResponseResult edit(@RequestBody Post post){
        int num = postService.updateById(post);
        return ResponseResult.successWithData(num);
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ApiOperation(value = "删除", hidden = true)
    @SystemLog(operateType = OperateTypeConstant.DELETE, operateDetail = "删除岗位", moduleName = "用户-机构与组")
    public ResponseResult del(Integer id){
        int num = postService.del(id);
        return ResponseResult.successWithData(num);
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "添加", hidden = true)
    @SystemLog(operateType = OperateTypeConstant.ADD, operateDetail = "添加岗位", moduleName = "用户-机构与组")
    public ResponseResult add(@RequestBody Post post, HttpServletRequest request){
        String userId = (String)request.getSession().getAttribute("userId");
        post.setCreateUser(userId);
        int num = postService.insert(post);
        return ResponseResult.successWithData(num);
    }
}
