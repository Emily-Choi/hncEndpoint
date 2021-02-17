package com.example.demo.controllers;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	
//	@RequestMapping("/getLatestPopup")
//	public HcPopupMsg getLatestPopupContent() {
//		HcPopupMsg out = new HcPopupMsg();
//		out = mapper.selectLastestPopupContent();
//		return out;
//	}
	
}