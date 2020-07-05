//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.qingqing.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {
    @Value("${file.rootPath}")
    private String ROOT_PATH;
    @Value("${file.sonPath}")
    private String SON_PATH;

    public FileController() {
    }

    @PostMapping({"/fileUpload"})
    public String fileUpload(@RequestParam("mFile") MultipartFile file, HttpServletRequest request) {
        if (file.isEmpty()) {
            System.out.println("文件为空");
        }

        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String filePath = this.ROOT_PATH + this.SON_PATH;
        fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        try {
            file.transferTo(dest);
        } catch (IOException var9) {
            var9.printStackTrace();
        }

        List<String> url = new ArrayList();
        String strurl = "http://59.110.170.49:8080/img/" + fileName;
        if (request.getSession().getAttribute("url") == null) {
            url.add(strurl);
            request.getSession().setAttribute("url", url);
        } else {
            url = (List)request.getSession().getAttribute("url");
            url.add(strurl);
            request.getSession().setAttribute("url", url);
        }

        return "edit::urls";
    }
}
