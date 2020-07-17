package com.tiptimes.identity.utils;

import com.tiptimes.identity.entity.TpMainFile;
import com.tiptimes.identity.service.TpMainFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * ueditor上传图片
 * @author xzw
 */
@Component
public class UEditorUploadUtil {

    @Autowired
    private TpMainFileService tpMainFileService;

    public UEditorFile uploadImage(MultipartFile file) {
        String fileName = file.getOriginalFilename();  //获取文件名
        String fileId = upload(file);
        UEditorFile uEditorFile = new UEditorFile();
        if(StringUtils.isEmpty(fileId)){
            uEditorFile.setState("ERROR");
        }else{
            uEditorFile.setState("SUCCESS");
            uEditorFile.setUrl("/admin/file/downloadFile?fileId=" + fileId);  //访问URL
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
            String uuid = UUIDUtil.getUUID();
            byte[] buffer = file.getBytes();
            String md5 = DigestUtils.md5DigestAsHex(buffer);
            TpMainFile mainFile = tpMainFileService.getMainFileByFileMD5(md5);
            int result;
            if(mainFile == null){
                String newName = FileUtil.updateFileName(file.getOriginalFilename(), UUIDUtil.getUUID());
                FileOutputStream fos = new FileOutputStream(FileUtil.getFileUrl() + "/" + newName);
                fos.write(buffer);
                fos.close();
                TpMainFile tpMainFile = new TpMainFile();
                tpMainFile.setFileId(uuid);
                tpMainFile.setFileName(file.getOriginalFilename());
                tpMainFile.setFileType(file.getContentType());
                tpMainFile.setFileTime(new Date());
                tpMainFile.setFileStorageName(newName);
                tpMainFile.setFileMd5(md5);
                result = tpMainFileService.saveMainFile(tpMainFile);
            }else{
                TpMainFile tpMainFile = new TpMainFile();
                tpMainFile.setFileId(uuid);
                tpMainFile.setFileName(file.getOriginalFilename());
                tpMainFile.setFileType(file.getContentType());
                tpMainFile.setFileTime(new Date());
                tpMainFile.setFileStorageName(mainFile.getFileStorageName());
                tpMainFile.setFileMd5(md5);
                result = tpMainFileService.saveMainFile(tpMainFile);
            }
            if(result > 0){
                return uuid;
            }else{
                return null;
            }
        } catch (IOException e) {
            return null;
        }
    }
}
