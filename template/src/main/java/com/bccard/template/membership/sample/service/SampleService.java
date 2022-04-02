package com.bccard.template.membership.sample.service;

import com.bccard.template.membership.sample.entity.Sample;

import java.util.List;

public interface SampleService {

	List<Sample> list();
	
	Sample detail(String id) throws Exception;
	
	int update(Sample vo);
}
