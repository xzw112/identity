package com.tiptimes.identity.service;

import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.entity.Information;
import com.tiptimes.identity.qo.InformationRequest;
import com.tiptimes.identity.qo.InformationStatusRequest;
import com.tiptimes.identity.vo.InformationVo;


public interface InformationService {

    PageResult<InformationVo> getInformationList(InformationRequest informationRequest);

    Information selectDetailById(String id);

    int deleteById(String id);

    int insert(Information information);

    int updateById(Information information);

    int updateDel(String id);

    int updateUnDel(String id);

    // 更新消息的发布状态
    int updateInformationStatus(InformationStatusRequest informationStatusRequest);
}
