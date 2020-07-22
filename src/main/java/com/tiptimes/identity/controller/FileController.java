package com.tiptimes.identity.controller;

import com.alibaba.fastjson.JSONObject;
import com.tiptimes.identity.common.ErrorConstants;
import com.tiptimes.identity.common.ResponseResult;
import com.tiptimes.identity.component.FastDFSClientWrapper;
import com.tiptimes.identity.entity.TpMainFile;
import com.tiptimes.identity.service.TpMainFileService;
import com.tiptimes.identity.utils.FileUtil;
import com.tiptimes.identity.utils.UUIDUtil;
import com.tiptimes.identity.vo.FileVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;

@RestController
@RequestMapping("/admin/file")
public class FileController {

    @Autowired
    private TpMainFileService tpMainFileService;

    @Autowired
    private FastDFSClientWrapper fastDFSClientWrapper;

    @Value("${fdfs.resHost}")
    private String host;

    @Value("${fdfs.storagePort}")
    private String port;

    /**
     * 使用FastDFS进行文件上传
     *
     * @param mainfile
     * @return
     * @throws IOException
     */
    @PostMapping("/uploadDFSFile")
    public ResponseResult uploadDFSFile(@RequestParam("mainfile") MultipartFile mainfile) throws IOException {
        String path = fastDFSClientWrapper.uploadFile(mainfile);
        if(StringUtils.isNotBlank(path)){
            String fileName = mainfile.getOriginalFilename();
            FileVO fileVO = new FileVO();
            fileVO.setFileId(host + "/" + path);
            fileVO.setFileName(fileName);
            return ResponseResult.successWithData(fileVO);
        }else{
            return ResponseResult.error(ErrorConstants.UPLOAD_ERROR);
        }
    }

    /**
     * 下载
     * @param fileId 文件地址
     * @param fileName 文件名称
     * @param response
     * @throws IOException
     */
    @GetMapping("/downloadDFSFile")
    public void downloadFile(String fileId, String fileName, HttpServletResponse response) throws IOException {
        byte[] bytes = fastDFSClientWrapper.downloadFile(fileId);
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        response.setCharacterEncoding("UTF-8");
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            outputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将上传的base64图片，转换成文件存入磁盘
     * @param request 参数1
     * @param response 参数2
     * @param data 参数1
     * @return
     * @throws IOException
     */
    @PostMapping("/uploadBase64")
    public ResponseResult uploadBase64(HttpServletRequest request, HttpServletResponse response, HttpEntity<String> data) throws IOException{
        JSONObject jsonParam = JSONObject.parseObject(data.getBody());
        // 获取base64格式的图片
        String base64Url = jsonParam.getString("base64Url");
        // 去掉base64的文件头
        String newBase64Str = base64Url.replace("data:image/jpeg;base64,","");

        String filePath = FileUtil.getFileUrl();
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            // 将base64转换成byte[]
            byte[] bytes = decoder.decodeBuffer(newBase64Str);
            // 生成文件名称
            String fileStr = UUIDUtil.getUUID();
            // 写入
            File file = new File(filePath + "/" + fileStr + ".jpg");
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
            TpMainFile tpMainFile = new TpMainFile();
            tpMainFile.setFileId(fileStr);
            tpMainFile.setFileName(fileStr);
            tpMainFile.setFileType(fileStr);
            tpMainFile.setFileTime(new Date());
            tpMainFile.setFileStorageName(fileStr + ".jpg");
            tpMainFile.setFileMd5(fileStr);
            int result = tpMainFileService.saveMainFile(tpMainFile);
            if(result > 0){
                FileVO fileVO = new FileVO();
                fileVO.setFileId(fileStr);
                fileVO.setFileName(fileStr);
                return ResponseResult.successWithData(fileVO);
            }else{
                return ResponseResult.error(ErrorConstants.UPLOAD_ERROR);
            }
        }catch (Exception e){
            return ResponseResult.error(ErrorConstants.UPLOAD_ERROR);
        }finally {
            if(bos != null){
                try{
                    bos.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(fos != null){
                try{
                    fos.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping("/upload")
    public ResponseResult upload(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile mainfile) throws IOException {
        try {
            String uuid = UUIDUtil.getUUID();
            byte[] buffer = mainfile.getBytes();
            String md5 = DigestUtils.md5DigestAsHex(buffer);
            TpMainFile mainFile = tpMainFileService.getMainFileByFileMD5(md5);
            int result;
            if(mainFile == null){
                String newName = FileUtil.updateFileName(mainfile.getOriginalFilename(), UUIDUtil.getUUID());
                FileOutputStream fos = new FileOutputStream(FileUtil.getFileUrl() + "/" + newName);
                fos.write(buffer);
                fos.close();
                TpMainFile tpMainFile = new TpMainFile();
                tpMainFile.setFileId(uuid);
                tpMainFile.setFileName(mainfile.getOriginalFilename());
                tpMainFile.setFileType(mainfile.getContentType());
                tpMainFile.setFileTime(new Date());
                tpMainFile.setFileStorageName(newName);
                tpMainFile.setFileMd5(md5);
                result = tpMainFileService.saveMainFile(tpMainFile);
            }else{
                TpMainFile tpMainFile = new TpMainFile();
                tpMainFile.setFileId(uuid);
                tpMainFile.setFileName(mainfile.getOriginalFilename());
                tpMainFile.setFileType(mainfile.getContentType());
                tpMainFile.setFileTime(new Date());
                tpMainFile.setFileStorageName(mainFile.getFileStorageName());
                tpMainFile.setFileMd5(md5);
                result = tpMainFileService.saveMainFile(tpMainFile);
            }
            if(result > 0){
                FileVO fileVO = new FileVO();
                fileVO.setFileId(uuid);
                fileVO.setFileName(mainfile.getOriginalFilename());
                return ResponseResult.successWithData(fileVO);
            }else{
                return ResponseResult.error(ErrorConstants.UPLOAD_ERROR);
            }
        } catch (Exception e) {
            return ResponseResult.error(ErrorConstants.UPLOAD_ERROR);
        }
    }

    /**
     * 下载文件
     * @param fileId
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/downloadFile")
    public HttpServletResponse downloadFile(String fileId, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        if (fileId == null || "".equals(fileId)) {
            return null;
        }
        TpMainFile mainFile = tpMainFileService.getMainFileByFileID(fileId);
        if (mainFile == null) {
            return null;
        } else {
            String http_url = FileUtil.getFileUrl() + mainFile.getFileStorageName();
            String browser = "";
            String fileName = mainFile.getFileName();
            try {
                // filePath是指欲下载的文件的路径。
                File file = new File(http_url);
                // 以流的形式下载文件。
                InputStream fis = new BufferedInputStream(new FileInputStream(http_url));
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                fis.close();
                // 清空response
                response.reset();
                // 设置response的Header
                browser = request.getHeader("User-Agent");
                if (browser.contains("MSIE 6.0") || browser.contains("MSIE 7.0")) {
                    // IE6, IE7 浏览器
                    response.addHeader("content-disposition",
                            "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1"));
                } else if (browser.contains("MSIE 8.0")) {
                    // IE8
                    response.addHeader("content-disposition",
                            "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                } else if (browser.contains("MSIE 9.0")) {
                    // IE9
                    response.addHeader("content-disposition",
                            "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                } else if (browser.contains("Chrome")) {
                    // 谷歌
                    response.addHeader("content-disposition",
                            "attachment;filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF-8"));
                } else if (browser.contains("Safari")) {
                    // 苹果
                    response.addHeader("content-disposition",
                            "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1"));
                } else {
                    // 火狐或者其他的浏览器
                    response.addHeader("content-disposition",
                            "attachment;filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF-8"));
                }
                response.addHeader("Content-Length", "" + file.length());
                OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
                response.setContentType("application/octet-stream");
                toClient.write(buffer);
                toClient.flush();
                toClient.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return null;
        }
    }

}
