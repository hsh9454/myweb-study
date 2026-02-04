package com.mycom.myweb.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.UUID;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PhotoController {

    @RequestMapping("/photoUpload")
    public void photoUpload(HttpServletRequest request, HttpServletResponse response) {
        try {
            String filename = request.getHeader("file-name");
            String fileSizeStr = request.getHeader("file-size");
            
            String dftFilePath = request.getSession().getServletContext().getRealPath("/");
            String filePath = dftFilePath + "resources" + File.separator + "upload" + File.separator + "photo" + File.separator;
            
            File file = new File(filePath);
            if(!file.exists()) {
                file.mkdirs();
            }
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String today = formatter.format(new java.util.Date());
            String realFileNm = today + UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));
            String rlFileNm = filePath + realFileNm;
            
            InputStream is = request.getInputStream();
            OutputStream os = new FileOutputStream(rlFileNm);
            
            int numRead;
            byte b[] = new byte[Integer.parseInt(fileSizeStr)];
            
            while ((numRead = is.read(b, 0, b.length)) != -1) {
                os.write(b, 0, numRead);
            }
            
            if (is != null) is.close();
            os.flush();
            os.close();
            
            String sResult = "&bNewLine=true&sFileName=" + filename + "&sFileURL=" + request.getContextPath() + "/resources/upload/photo/" + realFileNm;
            
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(sResult);
            out.flush();
            out.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}