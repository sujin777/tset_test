package com.bccard.template.membership.sample.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.bccard.template.membership.sample.entity.Sample;
import com.bccard.template.membership.sample.mapper.SampleMapper;

import lombok.RequiredArgsConstructor;

@Service("SampleService")
@RequiredArgsConstructor
public class SampleServiceImpl implements SampleService {

	private final SampleMapper sampleMapper;
	
	@Override
	public List<Sample> list() {
		return sampleMapper.list();
	}

	@Override
	public Sample detail(String id) {//throws BizException {
		Sample vo = sampleMapper.detail(id);
		if (vo == null) {
			//throw new BizException("F001", "회원정보가 존재하지 않습니다.");
		}
		return vo;
	}

	@Override
	public int update(Sample vo) {
		return sampleMapper.update(vo);
	}

}
