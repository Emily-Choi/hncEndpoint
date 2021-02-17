package com.example.demo.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class HcNoticeList {
	private BigDecimal startSeqNo;
	private BigDecimal lastSeqNo;
	private List<HcNotice> noticeList;
	public BigDecimal getLastSeqNo() {
		return lastSeqNo;
	}
	public void setLastSeqNo(BigDecimal lastSeqNo) {
		this.lastSeqNo = lastSeqNo;
	}
	public List<HcNotice> getNoticeList() {
		if (this.noticeList == null)
			this.noticeList = new ArrayList<HcNotice>();
		return noticeList;
	}
	public void setNoticeList(List<HcNotice> noticeList) {
		this.noticeList = noticeList;
	}
	public BigDecimal getStartSeqNo() {
		return startSeqNo;
	}
	public void setStartSeqNo(BigDecimal startSeqNo) {
		this.startSeqNo = startSeqNo;
	}
	
}
