package com.tiptimes.identity.utils;

import java.util.UUID;

/**
 * uuid
 * @author: lj
 * @date: 2016年1月5日
 */
public class UUIDUtil {

	/**
	 * 得到一个32位的uuid
	 * 
	 * @author lj 2016年1月5日
	 */
	public static String getUUID(){
		String uString=UUID.randomUUID().toString();
		uString=uString.replace("-", "");
		return uString;
	}

	/**
	 * 测试
	 * 
	 * @author lj 2016年1月5日
	 * @throws ParseException 
	 *//*
	@Test
	public void main() throws ParseException {
		String uuid1 = dateToStamp("2017-04-19 13:30:15");
		String uuid2 = dateToStamp2();
		System.out.println(uuid1+"长度："+uuid2);
	}*/
}
