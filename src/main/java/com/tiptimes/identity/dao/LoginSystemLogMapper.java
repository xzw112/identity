package com.tiptimes.identity.dao;

import com.tiptimes.identity.entity.LoginSystemLog;
import com.tiptimes.identity.qo.LoginSystemLogRequest;
import com.tiptimes.identity.vo.LoginSystemLogVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginSystemLogMapper {

    List<LoginSystemLogVo> selectList(LoginSystemLogRequest loginSystemLogRequest);

    int insert(LoginSystemLog loginSystemLog);

}