package com.example.demo.dto;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

@Component
@Alias("HcCorpHIsInfo")
public class HcCorpHIsInfo {
	private int seqNo;
	private String baseYm;
	private String cntnt;
	private String cntntDtl;
	private String delYn;
	
	public int getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}

	public String getBaseYm() {
		return baseYm;
	}
	public void setBaseYm(String baseYm) {
		this.baseYm = baseYm;
	}
	public String getCntnt() {
		return cntnt;
	}
	public void setCntnt(String cntnt) {
		this.cntnt = cntnt;
	}
	public String getCntntDtl() {
		return cntntDtl;
	}
	public void setCntntDtl(String cntntDtl) {
		this.cntntDtl = cntntDtl;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	
}
