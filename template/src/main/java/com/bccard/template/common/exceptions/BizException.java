/**
 * 
 */
package com.bccard.template.common.exceptions;

import com.bccard.template.common.consts.ConstCd;
import com.bccard.template.common.consts.ErrorCode;

import lombok.Getter;

/**
 * @packageName : com.hoban.common.exception
 * @fileName    : BizException.java
 * @author      : hslee
 * @date        : 2021.11.30
 * @description :
 * ===========================================================
 * DATE                       AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2021.11.30                 hslee       최초 생성
 */
@Getter
public class BizException extends RuntimeException {
	
	private static final long serialVersionUID = 7531867935325555008L;

	private String message;

	private String errorCode;

	private int status;

	private ConstCd.LANG_CD langCd;
	
	private Object object;
	
	//private final static String DEFAULT_MESSAGE = "이 요청을 처리하는 동안 오류가 발생하였습니다.<br/>같은 문제가 계속해서 발생할 경우 1588-XXXX번으로 문의 바랍니다.";

	public BizException(ErrorCode errorCode){
		this.status = errorCode.getStatus();
		this.errorCode = errorCode.getCode();

		this.message = errorCode.getMessage();
//		try{
//			this.message = messageHandler.getMessage("asd");
//		}catch(NoSuchMessageException e){
//		}


//		this.message = errorCode.getMessage();
	}

	public BizException(ErrorCode errorCode, String errorMsg){
		this.status = errorCode.getStatus();
		this.errorCode = errorCode.getCode();
		this.message = errorMsg;
	}
}
