package com.tiptimes.identity.service;

import com.tiptimes.identity.entity.TpMainDictionary;
import java.util.List;

/**
 * 基础数据 Service接口
 */
public interface TpMainDictionaryService {

	/**
	 * 通过条件查询数据
	 * @param dictionaryClass
	 * @return
	 */
	List<TpMainDictionary> selectByDictionaryClass(String dictionaryClass);


}