package com.example.demo.dto;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

@Component
@Alias("HcCorpInfoDto")
public class HcCorpInfoDto {
	private String corpNm;
	private String corpAddr;
	private String corpRgstNo;
	private String prsnInfoMngr;
	private String zipNo;
	private String telNo;
	private String faxNo;
	private String prsdtNm;
	public String getCorpNm() {
		return corpNm;
	}
	public void setCorpNm(String corpNm) {
		this.corpNm = corpNm;
	}
	public String getCorpAddr() {
		return corpAddr;
	}
	public void setCorpAddr(String corpAddr) {
		this.corpAddr = corpAddr;
	}
	public String getCorpRgstNo() {
		return corpRgstNo;
	}
	public void setCorRgstNo(String corRgstNo) {
		this.corpRgstNo = corRgstNo;
	}
	public String getZipNo() {
		return zipNo;
	}
	public void setZipNo(String zipNo) {
		this.zipNo = zipNo;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public String getFaxNo() {
		return faxNo;
	}
	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}
	
	public String getPrsnInfoMngr() {
		return prsnInfoMngr;
	}
	public void setPrsnInfoMngr(String prsnInfoMngr) {
		this.prsnInfoMngr = prsnInfoMngr;
	}
	public String getPrsdtNm() {
		return prsdtNm;
	}
	public void setPrsdtNm(String prsdtNm) {
		this.prsdtNm = prsdtNm;
	}
}
