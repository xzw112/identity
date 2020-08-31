package com.tiptimes.identity.service;
import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.entity.Opinion;
import com.tiptimes.identity.qo.OpinionRequest;
import com.tiptimes.identity.vo.OpinionVo;


public interface OpinionService {

    PageResult<OpinionVo> selectOpinionList(OpinionRequest opinionRequest);

    int insert(Opinion opinion);

    Opinion selectDetailById(String id);
}
