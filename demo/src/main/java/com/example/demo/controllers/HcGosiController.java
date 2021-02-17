package com.example.demo.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.HcFaqDto;
import com.example.demo.dto.HcFaqList;
import com.example.demo.mapper.HcGosiMapper;
import com.example.demo.mapper.HcNoticeMapper;

@RestController
@MapperScan(basePackages = "com.example.demo.mapper")
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/gosi")
public class HcGosiController {
	@Autowired
	private HcGosiMapper mapper;
	List<String> files = new ArrayList<String>();
	final DefaultResourceLoader dr = new DefaultResourceLoader();
	private static final Logger logger = LoggerFactory.getLogger(HcGosiController.class);
	
	@Value("${file.upload-dir}")
    String fDir;
    
	@RequestMapping("/count")
	 public int getFaqPageCount() throws Exception{
       //ModelAndView mav = new ModelAndView("test");
       int out = 0;
       //hcNoticeSvc = new HcNoticeService();
       
       out = mapper.getAllCount();

       return out;
   }
	
	@RequestMapping("/getPageList")
	public HcFaqList getGosiListPage(Integer seq_no) throws Exception{
		HcFaqList out = new HcFaqList();
		List<HcFaqDto> ll = new ArrayList<HcFaqDto>();
		ll = mapper.selectGosiListPage(seq_no*10);
        out.setFaqList(ll);
        if(ll.size() > 0 ) {
        	out.setStartSeqNo(ll.get(0).getSeqNo());
        	out.setEndSeqNo(ll.get(ll.size()-1).getSeqNo());
        }
		return out;
	}
	
	@RequestMapping("/setData")
	public ResponseEntity<String> insertGosiData(@RequestParam("titleNm") String titleNm
			, @RequestParam("file") MultipartFile file) throws Exception{
		
		int out = -1;
		HcFaqDto in = new HcFaqDto();
		logger.info("====setData PARAMS titleNm" + titleNm); 
		logger.info("====setData Absolute Upload Path: " + fDir); 
		
		String message;
	      String fileDownloadUri;

	      Resource resource = dr.getResource("classpath:files"); 
	      logger.info("file Path: " + file);
	      
	      try {
	    	  fileDownloadUri = resource.getFile().getAbsoluteFile().toString();
	    	  logger.info("fileDownloadUri Path: " + fileDownloadUri);
	         try {
	        	 //file.upload-dir
//	        	 Path filePath = Paths.get(fileDownloadUri + "/" + file.getOriginalFilename());
	        	 Path filePath = Paths.get(fDir + file.getOriginalFilename());
	        	 logger.info("file Path: " + filePath.toString());
	        	 logger.info("file name : " + file.getOriginalFilename());
	             Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
	             
	         } catch (Exception e) {
	            throw new RuntimeException("FAIL!");
	         }
	         files.add(file.getOriginalFilename());

	         message = "Successfully uploaded!";
	         //DB저장
	         in.setTitleNm(titleNm);
	         in.setCntnt(file.getOriginalFilename());
	         
	         out = mapper.insertGosiinfo(in);
	         
	         logger.info("mapper.insertGosiinfo: " + out);
	         logger.info("Message: " + ResponseEntity.status(HttpStatus.OK).body(message));
	         return  ResponseEntity.status(HttpStatus.OK).body(message);
	      } catch (Exception e) {
	         message = e.getMessage().toString();
	         return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
	      }
	}
	
	@RequestMapping("/delete")
	public int deleteGosiInfo(Integer seqNo) throws Exception{
		int out = -1;
//		List<HcFaqDto> ll = new ArrayList<HcFaqDto>();
		out = mapper.deleteGosiInfo(seqNo);
        
		return out;
	}
	
	@RequestMapping("/update")
	public ResponseEntity<String> updateGosiInfo(@RequestParam("seqNo") Integer seqNo
			, @RequestParam("titleNm") String titleNm
			, @RequestParam("file") MultipartFile file) throws Exception{
		
		int out = -1;
		HcFaqDto in = new HcFaqDto();
		String message ="";
		
		 try {
        	 //file.upload-dir
        	 Path filePath = Paths.get(fDir + file.getOriginalFilename());
        	 logger.info("file Path: " + filePath.toString());
        	 logger.info("file name : " + file.getOriginalFilename());
             Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
             
         } catch (Exception e) {
            throw new RuntimeException("FAIL!");
         }
         files.add(file.getOriginalFilename());

         message = "Successfully uploaded!";
         //DB저장
         in.setSeqNo(seqNo);
         in.setTitleNm(titleNm);
         in.setCntnt(file.getOriginalFilename());
         
         out = mapper.updateeGosiInfo(in);
         
         logger.info("mapper.insertGosiinfo: " + out);
         
         return ResponseEntity.status(HttpStatus.OK).body(message);
         
	}
	
	@RequestMapping("/updateTitleNm")
	public int updateGosiInfoTitleNm(@RequestParam("seqNo") Integer seqNo
			, @RequestParam("titleNm") String titleNm ) throws Exception{
		HcFaqDto in = new HcFaqDto();
		int out = -1;
		in.setSeqNo(seqNo);
		in.setTitleNm(titleNm);
		
		out = mapper.updateeGosiInfo(in);
        
		return out;
	}
}
