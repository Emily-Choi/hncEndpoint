package com.example.demo.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.HcFaqDto;
import com.example.demo.dto.HcNotice;
import com.example.demo.dto.HcPopupMsg;
import com.example.demo.dto.HcStaffM;

@Repository
@Mapper
public interface HcAdminMapper {
	HcStaffM selectVaildUser(HcStaffM in);
	int insertNewFaq(HcNotice in);
	int deleteFaqBySeqNo(int in);
	int updateFaqBySeqNo(HcFaqDto in);
	HcPopupMsg selectLastestPopupContent(HcPopupMsg in);
	int updatePopupContent(HcPopupMsg in);
	int insertPopupContent(HcPopupMsg in);
}
