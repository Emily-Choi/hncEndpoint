
package com.example.demo.dto;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;

@Component
@Alias("HcNotice")
public class HcNotice {

    private BigDecimal seq_no;
    private String title_nm;
    private String cntnt;
    private String del_yn;
    private String rgst_dt;
    //private date rgst_dt;
    public BigDecimal getSeq_no() {
		return seq_no;
	}
	public void setSeq_no(BigDecimal seq_no) {
		this.seq_no = seq_no;
	}
    public String getTitle_nm() {
		return title_nm;
	}
	public void setTitle_nm(String title_nm) {
		this.title_nm = title_nm;
	}
	
	public String getCntnt() {
		return cntnt;
	}
	public void setCntnt(String cntnt) {
		this.cntnt = cntnt;
	}
	public String getDel_yn() {
		return del_yn;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
	public String getRgst_dt() {
		return rgst_dt;
	}
	public void setRgst_dt(String rgst_dt) {
		this.rgst_dt = rgst_dt;
	}
}


