package com.example.demo.controllers;


import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

@RestController
@MapperScan(basePackages = "com.example.demo.mapper")
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/file")
public class HcFileController {

   List<String> files = new ArrayList<String>();
   final DefaultResourceLoader dr = new DefaultResourceLoader();
   private static final Logger logger = LoggerFactory.getLogger(HcFileController.class);

  // String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().toString();
   
//   @PostMapping("/savefile")
   
   @Autowired private ResourceLoader resourceLoader;
   @Autowired private ServletContext servletContext;

   @RequestMapping("/upload")
   public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
   
      String message;
      String fileDownloadUri;

      Resource resource = dr.getResource("classpath:files"); 
      logger.info("file Path: " + file);
      
      try {
    	  fileDownloadUri = resource.getFile().getAbsoluteFile().toString();
    	  logger.info("fileDownloadUri Path: " + fileDownloadUri);
         try {
        	 Path filePath = Paths.get(fileDownloadUri + "/" + file.getOriginalFilename());
        	 logger.info("file Path: " + filePath.toString());
        	 logger.info("file name : " + file.getOriginalFilename());
        	 String strPath = "D:\\documents\\";
        	 
             Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
             
             filePath = Paths.get(strPath  + file.getOriginalFilename());
             logger.info("file Path: " + filePath.toString());
//        	 logger.info("file name : " + file.getOriginalFilename());
        	 
             Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
             
         } catch (Exception e) {
        	logger.info("FAIL: " , e.getLocalizedMessage());
            throw new RuntimeException("FAIL!");
         }
         files.add(file.getOriginalFilename());

         message = "Successfully uploaded!";
         return ResponseEntity.status(HttpStatus.OK).body(message);
      } catch (Exception e) {
         message = e.getMessage().toString();
         return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
      }
   }
   
   public void getFile(@RequestParam("fileNm") String fileNm, HttpServletResponse response) {
   
       try {
           DefaultResourceLoader loader = new DefaultResourceLoader();
           InputStream is = loader.getResource("classpath:META-INF/resources/Accepted.pdf").getInputStream();
           IOUtils.copy(is, response.getOutputStream());
           response.setHeader("Content-Disposition", "attachment; filename=Accepted.pdf");
           response.flushBuffer();
       } catch (IOException ex) {
           throw new RuntimeException("IOError writing file to output stream");
       }
   }
}