package com.tiptimes.identity.controller.admin;

import com.tiptimes.identity.common.ErrorConstants;
import com.tiptimes.identity.common.ResponseResult;
import com.tiptimes.identity.component.FastDFSClientWrapper;
import com.tiptimes.identity.vo.FileVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/admin/file")
@Api("文件上传，下载")
public class FileController {

    @Autowired
    private FastDFSClientWrapper fastDFSClientWrapper;

    @Value("${fdfs.resHost}")
    private String host;

    @Value("${fdfs.storagePort}")
    private String port;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/");

    @Value("${file-save-path}")
    private String fileSavePath;

    @PostMapping(value = "uploadFile")
    @ApiOperation(value = "上传文件--流")
    public ResponseResult uploadFile(@RequestParam("mainfile") MultipartFile mainfile, HttpServletRequest request){
        //1.后半段目录：  2020/03/15
        String directory = simpleDateFormat.format(new Date());
        /**
         *  2.文件保存目录  D:/images/2020/03/15/
         *  如果目录不存在，则创建
         */
        File dir = new File(fileSavePath + directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        //3.给文件重新设置一个名字
        //后缀
        String suffix = mainfile.getOriginalFilename().substring(mainfile.getOriginalFilename().lastIndexOf("."));
        String newFileName= UUID.randomUUID().toString().replaceAll("-", "")+suffix;
        //4.创建这个新文件
        File newFile = new File(fileSavePath + directory + newFileName);
        //5.复制操作
        try {
            mainfile.transferTo(newFile);
            //协议 :// ip地址 ：端口号 / 文件目录(/images/2020/03/15/xxx.jpg)
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/file/" + directory + newFileName;
            FileVO fileVO = new FileVO();
            fileVO.setFileId(url);
            fileVO.setFileName(mainfile.getOriginalFilename());
            return ResponseResult.successWithData(fileVO);
        } catch (IOException e) {
            return ResponseResult.error("上传失败");
        }
    }

    /**
     * 下载文件
     * @param fileId
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/downloadFile",method = RequestMethod.GET)
    @ApiOperation(value = "下载文件--流")
    public void downloadFile(String fileId, String fileName, HttpServletRequest request, HttpServletResponse response) {
        if (fileId == null || "".equals(fileId)) {
            return;
        }
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/file/";
        fileId = fileId.replace(url, fileSavePath);
        String browser = "";
        try {
            // filePath是指欲下载的文件的路径。
            File file = new File(fileId);
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(fileId));
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
    }

    /**
     * 使用FastDFS进行文件上传
     *
     * @param mainfile
     * @return
     * @throws IOException
     */
    @PostMapping("/uploadDFSFile")
    @ApiOperation(value = "上传文件--FastDFS")
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
    @ApiOperation(value = "下载文件--FastDFS")
    public void downloadDFSFile(String fileId, String fileName, HttpServletResponse response) throws IOException {
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

}
