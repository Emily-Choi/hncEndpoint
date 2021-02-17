package com.example.demo.dto;

import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

@Component
@Alias("HcCorpHncInfo")
public class HcCorpHncInfo {
	private HcCorpBizInfoDto bizInfo;
	private List<HcCorpHIsInfo> hisInfo;
	public HcCorpBizInfoDto getBizInfo() {
		return bizInfo;
	}
	public void setBizInfo(HcCorpBizInfoDto bizInfo) {
		this.bizInfo = bizInfo;
	}
	public List<HcCorpHIsInfo> getHisInfo() {
		return hisInfo;
	}
	public void setHisInfo(List<HcCorpHIsInfo> hisInfo) {
		this.hisInfo = hisInfo;
	}

	

}
