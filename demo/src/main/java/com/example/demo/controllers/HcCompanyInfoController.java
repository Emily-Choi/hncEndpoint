package com.example.demo.controllers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.HcCorpInfoDto;
import com.example.demo.dto.HcCorpHIsInfo;
import com.example.demo.dto.HcCorpBizInfoDto;
import com.example.demo.dto.HcCorpHncInfo;
import com.example.demo.mapper.HcCompanyInfoMapper;

@RestController
@MapperScan(basePackages = "com.example.demo.mapper")
@RequestMapping("/corp")
public class HcCompanyInfoController {
	@Autowired
	private HcCompanyInfoMapper mapper;
	@RequestMapping("/info")
	public HcCorpInfoDto getCorpInfo() {
		HcCorpInfoDto out = new HcCorpInfoDto();
		out = mapper.selectCorpInfo();
		return out;
	}
	@RequestMapping("/about")
	public HcCorpHncInfo getCorpHisInfo() {
		HcCorpHncInfo out = new HcCorpHncInfo();
		List<HcCorpHIsInfo> his = new ArrayList<HcCorpHIsInfo>();
		HcCorpBizInfoDto biz = new HcCorpBizInfoDto();
		
		his = mapper.selectCoprHisInfo();
		biz = mapper.selectCoprBizInfo();
		
		out.setBizInfo(biz);
		out.setHisInfo(his);
		
		return out;
	}
	
	
}
