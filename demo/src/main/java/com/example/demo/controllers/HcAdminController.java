package com.example.demo.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.HcFaqDto;
import com.example.demo.dto.HcNotice;
import com.example.demo.dto.HcPopupMsg;
import com.example.demo.dto.HcStaffM;
import com.example.demo.mapper.HcAdminMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@MapperScan(basePackages = "com.example.demo.mapper")
@RequestMapping("/admin")
public class HcAdminController {
	@Autowired
	private HcAdminMapper mapper;
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	final DefaultResourceLoader dr = new DefaultResourceLoader();
	
	@Value("${popupImg.upload-dir}")
    String dir;
	
	List<String> files = new ArrayList<String>();
	
	@RequestMapping("/login")
	public HcStaffM getLogin(@RequestParam("userId") String userId
						 , @RequestParam("password") String password ) {
		HcStaffM out = new HcStaffM();
		HcStaffM in = new HcStaffM();
		in.setUserId(userId);
		in.setPassword(password);
		
		out.setIsValid("N");
		
		out = mapper.selectVaildUser(in) ;
		
		if (!out.getUserId().isEmpty() && out.getUserId().equals(userId)) {
			if((!out.getPassword().isEmpty() && out.getPassword().equals(password)))
				out.setIsValid("Y");
		}
		
		return out;
	}
	@RequestMapping("/inertFaq")
	public int insertNewFaq(@RequestParam("titleNm") String titleNm, @RequestParam("cntnt") String cntnt) {
		int out = -1;
		HcNotice in = new HcNotice();
		in.setTitle_nm(titleNm);
		in.setCntnt(cntnt);
		out = mapper.insertNewFaq(in);
		logger.debug("insert new faq =======================");
		logger.debug("get inserted data seq: =======================", out);
		logger.debug("End insert new faq =======================");
		return out;
	}
	@RequestMapping("/deleteFaq")
	public int deleteFaqBySeqNo(@RequestParam("seqNo") int seqNo) {
		int out = -1;
		out= mapper.deleteFaqBySeqNo(seqNo);
		return out;
	}
	
	@RequestMapping("/updateFaq")
	public int updateFaqBySeqNo(@RequestParam("seqNo") int seqNo, @RequestParam("titleNm") String titleNm, @RequestParam("cntnt") String cntnt) {
		int out = -1;
		HcFaqDto in = new HcFaqDto();
		in.setSeqNo(seqNo);
		in.setTitleNm(titleNm);
		in.setCntnt(cntnt);
		out = mapper.updateFaqBySeqNo(in);

		return out;
	}
	
	@RequestMapping("/getLatestPopup")
	public HcPopupMsg getLatestPopupContent(@RequestParam("seqNo") int seqNo, @RequestParam("popupYn") String popupYn) {		
		HcPopupMsg in = new HcPopupMsg();
		in.setSeqNo(seqNo);
		in.setPopupYn(popupYn);
		
		HcPopupMsg out = new HcPopupMsg();
		
		out = mapper.selectLastestPopupContent(in);
		return out;
	}
	
	@RequestMapping("/updatePopupCntnt")
	public int updatePopupContent(@RequestParam("seqNo") int seqNo
							, @RequestParam("titleNm") String titleNm, @RequestParam("cntnt") String cntnt
							, @RequestParam("popupYn") String popupYn) {		
		int out = -1;
		HcPopupMsg in = new HcPopupMsg();
		in.setSeqNo(seqNo);
		in.setTitleNm(titleNm);
		in.setCntnt(cntnt);
		in.setPopupYn(popupYn);
		
		out = mapper.updatePopupContent(in);
		return out;
	}
	
	@RequestMapping("/insertPopupCntnt")
	public int insertPopupContent(@RequestParam("titleNm") String titleNm, @RequestParam("cntnt") String cntnt
							, @RequestParam("popupYn") String popupYn) {		
		int out = -1;
		HcPopupMsg in = new HcPopupMsg();
		in.setTitleNm(titleNm);
		in.setCntnt(cntnt);
		in.setPopupYn(popupYn);
		
		out = mapper.insertPopupContent(in);
		return out;
	}
	
	@RequestMapping("/getImgPopupYn")
	public List<HcPopupMsg> getImgPopupYn() {		
		List<HcPopupMsg> out = new ArrayList<HcPopupMsg>();
		
		out = mapper.selectImgPopupYn();
		
		return out;
	}
	
	@RequestMapping("/changeImgPopupImg")
	public ResponseEntity<String> changeImgPopupImg(@RequestParam("seqNo") Integer seqNo
			, @RequestParam("popupYn") String popupYn
			, @RequestParam("file") MultipartFile file ) throws Exception{
		
		int out = -1;
		HcPopupMsg in = new HcPopupMsg();
		logger.info("====setData PARAMS titleNm" + seqNo); 
		logger.info("====setData Absolute Upload Path: " + dir); 
		
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
	        	 Path filePath = Paths.get(dir + file.getOriginalFilename());
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
	         in.setPopupYn(popupYn);
	         in.setTitleNm("/assets/images/popup/"+file.getOriginalFilename());
	         logger.info("mapper.changeImgPopupImg in: seq: " + in.getSeqNo() + " popupYn: " + in.getPopupYn() + " in.getFilePath: " + in.getTitleNm());
	         out = mapper.updateImgPopup(in);
	         
	         logger.info("mapper.insertGosiinfo: " + out);
	         logger.info("Message: " + ResponseEntity.status(HttpStatus.OK).body(message));
	         return  ResponseEntity.status(HttpStatus.OK).body(message);
	      } catch (Exception e) {
	         message = e.getMessage().toString();
	         logger.info("Message: " + ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message));
	         return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
	      }
	}
	
	@RequestMapping("/changeImgPopupCntnt")
	public int changeImgPopupCntnt(@RequestParam("seqNo") Integer seqNo
			, @RequestParam("popupYn") String popupYn ) throws Exception{
		
		int out = -1;
		HcPopupMsg in = new HcPopupMsg();
		logger.info("====setData PARAMS titleNm" + seqNo); 
		logger.info("====setData Absolute Upload Path: " + dir); 
		
	
	         //DB저장
	         in.setSeqNo(seqNo);
	         in.setPopupYn(popupYn);
	         
	         out = mapper.updateImgPopup(in);
	         
	         logger.info("mapper.insertGosiinfo: " + out);

	   return out;
	}
	
//	@RequestMapping("/getLatestPopup")
//	public HcPopupMsg getLatestPopupContent() {
//		HcPopupMsg out = new HcPopupMsg();
//		out = mapper.selectLastestPopupContent();
//		return out;
//	}
	
}