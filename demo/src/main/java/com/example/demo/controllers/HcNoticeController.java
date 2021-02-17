package com.example.demo.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.dto.HcNotice;
import com.example.demo.dto.HcNoticeList;

import com.example.demo.mapper.HcNoticeMapper;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@MapperScan(basePackages = "com.example.demo.mapper")
@RequestMapping("/notice")
public class HcNoticeController {
	//public HcNoticeService hcNoticeSvc;
	@Autowired
	private HcNoticeMapper mapper;
	@RequestMapping("/list")
	 public List<HcNotice> test() throws Exception{
        //ModelAndView mav = new ModelAndView("test");
        
        //hcNoticeSvc = new HcNoticeService();
        List<HcNotice> testList = new ArrayList<HcNotice>();
        
        testList = mapper.selectAllList();
        
        //List<HcNotice> testList = hcNoticeSvc.selectAllList();
        //mav.addObject("list", testList);

        return testList;
    }
	
	@RequestMapping("/page")
	 public List<HcNotice> getNoticeDataByNo(Integer seq_no) throws Exception{
       //ModelAndView mav = new ModelAndView("test");
		
		BigDecimal minSeqNo = BigDecimal.ZERO;
		BigDecimal curSeqNo = BigDecimal.ZERO;
		int i = 1;
         
       List<HcNotice> out = new ArrayList<HcNotice>();
       //List out = new ArrayList<>();
       out = mapper.selectNoticeListPage(seq_no*10);
       
       return out;
   }
	
	@RequestMapping("/count")
	public int getAllCount(String rgstDt) throws Exception{
		int count = 0;

		count = mapper.getAllCount("");
		return count;

	}
	
	@RequestMapping("/insert")
	public int insertNoticeData(HcNotice in) {
		int out = -1;
		
		out = mapper.insertNoticeData(in);
		
		return out;
	}
	
	@RequestMapping("/update")
	public int updateNoticeData(HcNotice in) {
		int out = -1;
		
		out = mapper.updateNoticeData(in);
		
		return out;
	}
	
}
