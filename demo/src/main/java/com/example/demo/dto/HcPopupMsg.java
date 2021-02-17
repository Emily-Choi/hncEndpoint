package com.example.demo.dto;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

@Component
@Alias("HcPopupMsg")

public class HcPopupMsg {
	private int seqNo;
	private String titleNm;
	private String cntnt;
	private String rgstDt;
	private String popupYn;
	private Date lastChngTmpstmp;
	
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
	public String getRgstDt() {
		return rgstDt;
	}
	public void setRgstDt(String rgstDt) {
		this.rgstDt = rgstDt;
	}
	public String getPopupYn() {
		return popupYn;
	}
	public void setPopupYn(String popupYn) {
		this.popupYn = popupYn;
	}
	public Date getLastChngTmpstmp() {
		return lastChngTmpstmp;
	}
	public void setLastChngTmpstmp(Date lastChngTmpstmp) {
		this.lastChngTmpstmp = lastChngTmpstmp;
	}
}
