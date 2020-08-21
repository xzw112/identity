package com.tiptimes.identity.dao;

import com.tiptimes.identity.entity.Information;
import com.tiptimes.identity.qo.InformationRequest;
import com.tiptimes.identity.qo.InformationStatusRequest;
import com.tiptimes.identity.vo.InformationVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InformationMapper {

    List<InformationVo> getInformationList(InformationRequest informationRequest);

    Information selectDetailById(String id);

    int deleteById(String id);

    int insert(Information information);

    int updateById(Information information);

    int updateDel(String id);
    int updateUnDel(String id);
    // 更新消息的发布状态
    int updateInformationStatus(InformationStatusRequest informationStatusRequest);
}