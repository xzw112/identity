package com.tiptimes.identity.controller.admin;

import com.tiptimes.identity.common.ResponseResult;
import com.tiptimes.identity.dao.*;
import com.tiptimes.identity.entity.*;
import com.tiptimes.identity.enums.DataStatus;
import com.tiptimes.identity.service.TpMainDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;
import javax.annotation.Resource;
import java.util.List;

/**
 * 基本数据
 */
@RestController
@RequestMapping("/admin/base")
public class BaseController {


    @Resource
    private TpMainRoleMapper tpMainRoleMapper;

    @Autowired
    private TpMainDictionaryService tpMainDictionaryService;

    /**
     * 获取字典信息
     * @return
     */
    @PostMapping("/dictionaryData")
    public ResponseResult nationalityData(String dictionaryClass) {
        List<TpMainDictionary> dictionaryList = tpMainDictionaryService.selectByDictionaryClass(dictionaryClass);
        StringBuilder htmlStr = new StringBuilder();
        htmlStr.append("<option value=\"\">请选择</option>");
        for (TpMainDictionary tpMainDictionary : dictionaryList) {
            htmlStr.append("<option value=\"").append(tpMainDictionary.getId()).append("\">").append(tpMainDictionary.getDictionaryValue()).append("</option>");
        }
        return ResponseResult.successWithData(htmlStr);
    }

    /**
     * 获取角色信息
     * @return
     */
    @PostMapping("/roleData")
    public ResponseResult roleData() {
        Example example = new Example(TpMainRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", DataStatus.ENABLED.getCode());
        criteria.andEqualTo("isDelete", DataStatus.NOT_DELETE.getCode());
        List<TpMainRole> roleList = tpMainRoleMapper.selectByExample(example);
        StringBuilder htmlStr = new StringBuilder();
        htmlStr.append("<option value=\"\">请选择</option>");
        for (TpMainRole role : roleList) {
            htmlStr.append("<option value=\"").append(role.getId()).append("\">").append(role.getRoleName()).append("</option>");
        }
        return ResponseResult.successWithData(htmlStr);
    }
}
