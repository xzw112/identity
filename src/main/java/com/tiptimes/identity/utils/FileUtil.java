package com.tiptimes.identity.utils;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * 文件工具类
 */
public class FileUtil {

	/**
	 * 对上传的文件进行重新命名
	 * @param oldName
	 * @param newName
	 * @return
	 */
	public static String updateFileName(String oldName, String newName) {
        StringBuffer newNameTemp = new StringBuffer(newName);
		String[] nameArray = oldName.split("\\.");
        for(int i=1;i<nameArray.length;i++){
        	newNameTemp = newNameTemp.append(".").append(nameArray[i]);
        }
        return newNameTemp.toString();     
    }

	/**
	 * 获取文件路径
	 * @return
	 * @throws IOException
	 */
	public static String getFileUrl() throws IOException {
		Properties props = new Properties();
		props.load(FileUtil.class.getClassLoader().getResourceAsStream("file.properties"));
		String fileUrl=props.getProperty("fileUrl");
		File tempFile = new File(fileUrl);
		if (!tempFile.exists()) {
			tempFile.mkdirs();
		}
		return fileUrl;
	}

}
