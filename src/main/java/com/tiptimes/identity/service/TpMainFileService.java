package com.tiptimes.identity.service;

import com.tiptimes.identity.entity.TpMainFile;

/**
 * 文件管理 Service接口
 */
public interface TpMainFileService {

    /**
     * 根据md5获取文件列表
     * @param fileMD5
     * @return
     */
    TpMainFile getMainFileByFileMD5(String fileMD5);

    /**
     * 根据文件id获取文件列表
     * @param fileId
     * @return
     */
    TpMainFile getMainFileByFileID(String fileId);

    /**
     * 上传文件
     * @param file
     * @return
     */
    int saveMainFile(TpMainFile file);
}