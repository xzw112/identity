package com.tiptimes.identity.utils;

import com.tiptimes.identity.component.FastDFSClientWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

/**
 * ueditor上传图片
 * @author xzw
 */
@Component
public class UEditorUploadUtil {

    @Autowired
    private FastDFSClientWrapper fastDFSClientWrapper;

    @Value("${fdfs.resHost}")
    private String host;

    public UEditorFile uploadImage(MultipartFile file) {
        String fileName = file.getOriginalFilename();  //获取文件名
        String fileId = upload(file);
        UEditorFile uEditorFile = new UEditorFile();
        if(StringUtils.isEmpty(fileId)){
            uEditorFile.setState("ERROR");
        }else{
            uEditorFile.setState("SUCCESS");
            uEditorFile.setUrl(fileId);  //访问URL
            uEditorFile.setTitle(fileName);
            uEditorFile.setOriginal(fileName);
        }
        return uEditorFile;
    }

    /**
     * ueditor上传文件
     * @param file
     * @return
     */
    private String upload(MultipartFile file){
        try {
            String path = fastDFSClientWrapper.uploadFile(file);
            if(org.apache.commons.lang3.StringUtils.isNotBlank(path)){
                return host + "/" + path;
            }else{
                return null;
            }
        } catch (IOException e) {
            return null;
        }
    }
}
