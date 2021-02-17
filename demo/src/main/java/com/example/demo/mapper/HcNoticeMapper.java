package com.example.demo.mapper;

import java.util.List;

import com.example.demo.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface HcNoticeMapper {
    List<HcNotice> selectAllList();
    int getAllCount(String rgstDt);
    List<HcNotice> selectNoticeListPage(Integer seq_no);
    int insertNoticeData(HcNotice in);
    int updateNoticeData(HcNotice in);
    int selectFaqCount();
    List<HcFaqDto> selectFaqListPage(Integer seq_no);
}	

