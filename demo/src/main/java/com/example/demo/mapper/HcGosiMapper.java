package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.HcFaqDto;

@Repository
@Mapper
public interface HcGosiMapper {
	public HcFaqDto selectAllList();
	public int getAllCount();
	public List<HcFaqDto> selectGosiListPage(int in);
	public int deleteGosiInfo(int seqNo);
	public int updateeGosiInfo(HcFaqDto in);
	public int insertGosiinfo(HcFaqDto in);
}
