package com.bccard.template.membership.sample.mapper;

import com.bccard.template.membership.sample.entity.Sample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SampleMapper {

	List<Sample> list();
	
	Sample detail(String id);
	
	int update(Sample vo);
}
