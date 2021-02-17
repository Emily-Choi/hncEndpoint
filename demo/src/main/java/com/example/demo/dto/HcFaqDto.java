package com.example.demo.dto;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

@Component
@Alias("HcFaqDto")
public class HcFaqDto {
	private int seqNo;
	private String titleNm;
	private String cntnt;
	private String delYn;
	private String rgstDt;
	public int getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}
	public String getTitleNm() {
		return titleNm;
	}
	public void setTitleNm(String titleNm) {
		this.titleNm = titleNm;
	}
	public String getCntnt() {
		return cntnt;
	}
	public void setCntnt(String cntnt) {
		this.cntnt = cntnt;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	public String getRgstDt() {
		return rgstDt;
	}
	public void setRgstDt(String rgstDt) {
		this.rgstDt = rgstDt;
	}
	
	
}
