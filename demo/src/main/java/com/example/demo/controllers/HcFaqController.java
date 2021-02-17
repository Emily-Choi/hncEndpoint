package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.HcFaqDto;
import com.example.demo.dto.HcFaqList;
import com.example.demo.dto.HcNotice;
import com.example.demo.mapper.HcNoticeMapper;

@RestController
@MapperScan(basePackages = "com.example.demo.mapper")
@RequestMapping("/faq")
public class HcFaqController {
	@Autowired
	private HcNoticeMapper mapper;
	@RequestMapping("/count")
	 public int getFaqPageCount() throws Exception{
        //ModelAndView mav = new ModelAndView("test");
        int out = 0;
        //hcNoticeSvc = new HcNoticeService();
        
        out = mapper.selectFaqCount();
        
        //List<HcNotice> testList = hcNoticeSvc.selectAllList();
        //mav.addObject("list", testList);

        return out;
    }
	@RequestMapping("/list")
	 public HcFaqList getFaqPageList(Integer seq_no) throws Exception{
       //ModelAndView mav = new ModelAndView("test");
		HcFaqList out = new HcFaqList();
		List<HcFaqDto> ll = new ArrayList<HcFaqDto>();
       //hcNoticeSvc = new HcNoticeService();
       
		ll = mapper.selectFaqListPage(seq_no*10);
        out.setFaqList(ll);
        if(ll.size() > 0 ) {
        	out.setStartSeqNo(ll.get(0).getSeqNo());
        	out.setEndSeqNo(ll.get(ll.size()-1).getSeqNo());
        }
        
       return out;
   }
}
