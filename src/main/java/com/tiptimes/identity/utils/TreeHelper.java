package com.tiptimes.identity.utils;

import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName TreeHelper
 * Description: 树形结构数据转换工具类
 * Author user
 * Date 2019/10/29
 * Version V1.0
 **/
public class TreeHelper {

    /**
     * MethodName: generateTree
     * Description: 生成树
     * Param: [treeVOList]
     * Return: java.util.List<com.tiptimes.oa.organization.util.tree.TreeEntity < T>>
     * Author: user
     * Date: 2019/10/29
     **/
    public static List<TreeEntity> generateTree(List<TreeEntity> treeVOList) {
        List<TreeEntity> treeVOS = new ArrayList<>();

        for (TreeEntity te : treeVOList) {
            if (StringUtils.isBlank(te.getParentId())) {
                treeVOS.add(te);
            }
        }
        for (TreeEntity te : treeVOS) {
            te.setChildren(getChildNav(te.getId(), treeVOList));
        }
        return treeVOS;
    }

    /**
     * MethodName: getChildNav
     * Description: 获取子节点
     * Param: [id, treeVOList]
     * Return: java.util.List<com.tiptimes.oa.organization.util.tree.TreeEntity < T>>
     * Author: user
     * Date: 2019/10/29
     **/
    private static List<TreeEntity> getChildNav(String id, List<TreeEntity> treeVOList) {
        // 子菜单
        List<TreeEntity> childList = new ArrayList<>();
        // 遍历所有节点，如果父节点等于id则为子菜单
        for (TreeEntity te : treeVOList) {
            if (id.equals(te.getParentId())) {
                childList.add(te);
            }
        }
        // 递归退出的条件
        if (childList.size() == 0) {
            return new ArrayList<>();
        }
        // 递归查找所有子节点的下级菜单
        for (TreeEntity te : childList) {
            te.setChildren(getChildNav(te.getId(), treeVOList));
        }
        return childList;
    }
}
