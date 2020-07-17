package com.tiptimes.identity.service.impl;

import com.tiptimes.identity.dao.TpMainDictionaryMapper;
import com.tiptimes.identity.entity.TpMainDictionary;
import com.tiptimes.identity.enums.DataStatus;
import com.tiptimes.identity.service.TpMainDictionaryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import javax.annotation.Resource;
import java.util.List;

/**
 * 运动员申请项目模块 Service层
 */
@Service
@Transactional
public class TpMainDictionaryServiceImpl implements TpMainDictionaryService {

    @Resource
    private TpMainDictionaryMapper tpMainDictionaryMapper;

    @Override
    public List<TpMainDictionary> selectByDictionaryClass(String dictionaryClass) {
        Example example = new Example(TpMainDictionary.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDelete", DataStatus.NOT_DELETE.getCode());
        if(StringUtils.isNotBlank(dictionaryClass)){
            criteria.andEqualTo("dictionaryClass", dictionaryClass);
        }
        return tpMainDictionaryMapper.selectByExample(example);
    }
}
