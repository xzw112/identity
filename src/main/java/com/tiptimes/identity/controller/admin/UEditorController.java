package com.tiptimes.identity.controller.admin;

import com.baidu.ueditor.ActionEnter;
import com.tiptimes.identity.utils.UEditorFile;
import com.tiptimes.identity.utils.UEditorUploadUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
public class UEditorController {

    @Autowired
    private UEditorUploadUtil uEditorUpload;

    @RequestMapping("/config")
    public String config(HttpServletRequest request, HttpServletResponse response, String action, MultipartFile upfile) throws IOException {
        if (action.equals("config")) {
            request.setCharacterEncoding("utf-8");
            response.setHeader("Content-Type", "text/html");
            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/config";
            PrintWriter filterWriter = response.getWriter();
            filterWriter.write(new ActionEnter(request, path).exec());
            filterWriter.flush();
            filterWriter.close();
        } else if (action.equals("uploadimage")) {
            UEditorFile uEditorFile = uEditorUpload.uploadImage(upfile);
            JSONObject jsonObject = new JSONObject(uEditorFile);
            return jsonObject.toString();
        }
        return null;
    }
}
