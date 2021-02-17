package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.HcCorpInfoDto;
import com.example.demo.dto.HcCorpBizInfoDto;
import com.example.demo.dto.HcCorpHIsInfo;
@Repository
@Mapper
public interface HcCompanyInfoMapper {
	HcCorpInfoDto selectCorpInfo();
	List<HcCorpHIsInfo> selectCoprHisInfo();
	HcCorpBizInfoDto selectCoprBizInfo();
}
