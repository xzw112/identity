package com.tiptimes.identity.dao;


import com.tiptimes.identity.entity.Group;
import com.tiptimes.identity.qo.GroupRequest;
import com.tiptimes.identity.vo.GroupVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupMapper {

    List<GroupVo> selectGroupList(GroupRequest groupRequest);

    Group selectDetail(Integer id);

    int insert(Group group);

    int updateById(Group group);

    int delGroup(Integer id);
}