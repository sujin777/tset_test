package com.bccard.template.membership.sample.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bccard.template.common.consts.ErrorCode;
import com.bccard.template.common.dto.CommonDTO;
import com.bccard.template.common.dto.ResponseDTO;
import com.bccard.template.common.exceptions.BizException;
import com.bccard.template.common.handler.WebClientHandler;
import com.bccard.template.common.utils.JacksonUtils;
import com.bccard.template.common.utils.LogUtils;
import com.bccard.template.membership.sample.dto.SampleDTO;
import com.bccard.template.membership.sample.entity.Sample;
import com.bccard.template.membership.sample.service.SampleService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/api")
@RestController
@Slf4j
@RequiredArgsConstructor
public class SampleApiController {

	private final SampleService sampleService;

	private final WebClientHandler webClientHandler;
	
	@Operation(summary = "회원정보 목록")
	@GetMapping("/member")
	public ResponseDTO list() {
		List<Sample> list = sampleService.list();
		
		List<SampleDTO> sampleDTOList = new ArrayList<>();
		for (Sample sample : list) {
			SampleDTO sampleDTO = JacksonUtils.convetObject(sample, SampleDTO.class);
			sampleDTOList.add(sampleDTO);
			LogUtils.info("sampleDTO : {}", sampleDTO);
		}

		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setResponse(sampleDTOList);
		return responseDTO;
	}

	@Operation(summary = "에러 테스트 1 - (BizException)")
	@GetMapping("/error1")
	public ResponseDTO error1() {
		throw new BizException(ErrorCode.INTERNAL_SERVER_ERROR,"에러입니다");
	}

	@Operation(summary = "에러 테스트 2 - (BizException, i18n)")
	@GetMapping("/error2")
	public ResponseDTO error2() {

		try{
			//String ddd =  messageSource.getMessage("asdf", null, Locale.getDefault());
		}catch(Exception e){
			e.printStackTrace();
		}

		throw new BizException(ErrorCode.INTERNAL_SERVER_ERROR);
	}

	@Operation(summary = "로그 샘플")
	@GetMapping("/log")
	public ResponseDTO log(HttpServletRequest req) {

		log.info("slf4j Info!");
		log.error("slf4j Error!");

		CommonDTO commonDTO = (CommonDTO)req.getSession().getAttribute("commonDTO");
		LogUtils.info("logUtils Info!");
		LogUtils.error("logUtils Error! : {}", "test!!!!");
		LogUtils.error(commonDTO, "logUtils Error! : {}", "test with commonDto!!!!");

		ResponseDTO responseDTO = new ResponseDTO();
		return responseDTO;
	}

	@Operation(summary = "WebClient")
	@GetMapping("/web-client")
	public ResponseDTO webClient() {

		String resultStr = webClientHandler.get("todos/1");
		
		@SuppressWarnings("unchecked")
		Map<String, Object> result = JacksonUtils.convetObject(resultStr, Map.class);

		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setResponse(result);
		return responseDTO;
	}


//
//	/**
//	 * @methodName : detail
//	 * @author     : hslee
//	 * @date       : 2021.11.30
//	 * @param id
//	 * @return
//	 */
//	@Operation(summary = "회원정보 상세")
//	@GetMapping("/member/{id}")
//	public ResponseVO<SampleVO> detail(@PathVariable String id) throws Exception {
//		SampleVO result = sampleService.detail(id);
//
//		ResponseVO<SampleVO> responseDTO = new ResponseVO<SampleVO>();
//		responseDTO.setResponse(result);
//		return responseDTO;
//	}
//
//	@Operation(summary = "회원정보 등록")
//	@PostMapping("/insert")
//	public ResponseVO<Integer> insert(@RequestBody SampleVO vo) {
//
//		return null;
//	}
//
//	@Operation(summary = "회원정보 수정")
//	@PostMapping("/update")
//	public ResponseVO<Integer> update(@RequestBody SampleVO vo) {
//		int result = sampleService.update(vo);
//
//		ResponseVO<Integer> responseDTO = new ResponseVO<Integer>();
//		responseDTO.setResponse(result);
//		return responseDTO;
//	}
//
//	@Operation(summary = "회원정보 삭제")
//	@PostMapping("/delete")
//	public ResponseVO<Integer> delete(@RequestBody SampleVO vo) {
//
//		return null;
//	}
}
