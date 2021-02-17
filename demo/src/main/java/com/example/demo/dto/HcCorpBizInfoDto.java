package com.example.demo.dto;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

@Component
@Alias("HcCorpBizInfoDto")
public class HcCorpBizInfoDto {
	
	private Integer seqNo;
	private String mainBiz;
	private String estbFnd;
	private String capital;
	private String stckHlder;
	
	public Integer getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}
	public String getMainBiz() {
		return mainBiz;
	}
	public void setMainBiz(String mainBiz) {
		this.mainBiz = mainBiz;
	}
	public String getEstbFnd() {
		return estbFnd;
	}
	public void setEstbFnd(String estbFnd) {
		this.estbFnd = estbFnd;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public String getStckHlder() {
		return stckHlder;
	}
	public void setStckHlder(String stckHlder) {
		this.stckHlder = stckHlder;
	}

	
}
