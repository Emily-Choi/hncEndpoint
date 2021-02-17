package com.example.demo.dto;

import java.math.BigDecimal;
import java.util.List;

public class HcFaqList {
	private int startSeqNo;
	private int EndSeqNo;
	private List<HcFaqDto> faqList;
	public int getStartSeqNo() {
		return startSeqNo;
	}
	public void setStartSeqNo(int startSeqNo) {
		this.startSeqNo = startSeqNo;
	}
	public int getEndSeqNo() {
		return EndSeqNo;
	}
	public void setEndSeqNo(int endSeqNo) {
		EndSeqNo = endSeqNo;
	}
	public List<HcFaqDto> getFaqList() {
		return faqList;
	}
	public void setFaqList(List<HcFaqDto> faqList) {
		this.faqList = faqList;
	}
	
}
