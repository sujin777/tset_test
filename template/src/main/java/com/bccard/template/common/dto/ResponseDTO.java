/**
 * 
 */
package com.bccard.template.common.dto;

import com.bccard.template.common.consts.ErrorCode;
import com.bccard.template.common.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.BindingResult;

import java.io.Serializable;

@Schema(description = "공통 ResponseVO")
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseDTO implements Serializable {

	private static final long serialVersionUID = -3644478902715776246L;
	
	/** 스테이터스 */
	@JsonIgnore
	private int status;

	@Schema(description = "상태코드")
	/** 상태코드 */
	private String code;
	
	/** 메세지 */
	private String message;
	
	/** 거래시간 */
	private String timestamp;
	
	/** Response Body */
	private Object response = null;

	public ResponseDTO() {
		String timestamp = DateUtils.getNowDate("yyyyMMddHHmmss");

		this.code = "0000";
		this.message = "정상 처리 되었습니다.";
		this.status = 200;
		this.timestamp = timestamp;
	}

	public ResponseDTO(ErrorCode errorCode) {
		String timestamp = DateUtils.getNowDate();

		this.code = errorCode.getCode();
		this.message = errorCode.getMessage();
		this.status = errorCode.getStatus();
		this.timestamp = timestamp;
	}

	public ResponseDTO(ErrorCode errorCode, BindingResult bindingResult) {
		String timestamp = DateUtils.getNowDate();

		// fhkrwogk BidingResult쓰도록 수정도 필요해 보임.
		
		this.code = errorCode.getCode();
		this.message = errorCode.getMessage();
		this.status = errorCode.getStatus();
		this.timestamp = timestamp;
	}
}
