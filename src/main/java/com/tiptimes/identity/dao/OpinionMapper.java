package com.tiptimes.identity.dao;

import com.tiptimes.identity.entity.Opinion;
import com.tiptimes.identity.qo.OpinionRequest;
import com.tiptimes.identity.vo.OpinionVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpinionMapper {

    List<OpinionVo> selectOpinionList(OpinionRequest opinionRequest);

    int insert(Opinion opinion);

    Opinion selectDetailById(String id);


}