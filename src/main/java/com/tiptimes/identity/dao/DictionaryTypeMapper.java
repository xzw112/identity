package com.tiptimes.identity.dao;

import com.tiptimes.identity.entity.DictionaryType;
import org.springframework.stereotype.Repository;

@Repository
public interface DictionaryTypeMapper {

    DictionaryType selectDetail(Integer id);

    int insert(DictionaryType dictionaryType);

    int updateById(DictionaryType dictionaryType);
}