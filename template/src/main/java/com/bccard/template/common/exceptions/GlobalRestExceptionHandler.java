/**
 * 
 */
package com.bccard.template.common.exceptions;

import java.nio.file.AccessDeniedException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.bccard.template.common.consts.ErrorCode;
import com.bccard.template.common.dto.ResponseDTO;
import com.bccard.template.common.handler.MessageHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalRestExceptionHandler extends Exception {
	
	private final MessageHandler messageHandler;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2620211632649491622L;

	
    /**
     * javax.validation.Valid or @Validated 으로 binding error 발생시 발생
     * HttpMessageConverter 에서 등록한 HttpMessageConverter binding 못할경우 발생
     * @methodName : handleMethodArgumentNotValidException
     * @author     : hslee
     * @date       : 2021.11.30
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("handleMethodArgumentNotValidException", e);
        
        ResponseDTO response = new ResponseDTO(ErrorCode.INVALID_TYPE_VALUE, e.getBindingResult());
        return getRespose(response);
    }

    /**
     * @ModelAttribut 으로 binding error 발생시 BindException 발생
     * @methodName : handleBindException
     * @author     : hslee
     * @date       : 2021.11.30
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    protected ResponseEntity<ResponseDTO> handleBindException(BindException e) {
        log.error("handleBindException", e);

        ResponseDTO response = new ResponseDTO(ErrorCode.INVALID_TYPE_VALUE, e.getBindingResult());
        return getRespose(response);
    }
	
    /**
     * enum type 일치하지 않아 binding 못할 경우 발생
     * @methodName : handleMethodArgumentTypeMismatchException
     * @author     : hslee
     * @date       : 2021.11.30
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ResponseDTO> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("handleMethodArgumentTypeMismatchException", e);
        
        ResponseDTO response = new ResponseDTO(ErrorCode.BAD_REQUEST);
        return getRespose(response);
    }
	
    /**
     * 지원하지 않은 HTTP method 호출 할 경우 발생
     * @methodName : handleHttpRequestMethodNotSupportedException
     * @author     : hslee
     * @date       : 2021.11.30
     * @param e
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ResponseDTO> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException", e);
        
        ResponseDTO response = new ResponseDTO(ErrorCode.METHOD_NOT_ALLOWED);
        return getRespose(response);
    }
    
    /**
     * Authentication 객체가 필요한 권한을 보유하지 않은 경우 발생합
     * @methodName : handleAccessDeniedException
     * @author     : hslee
     * @date       : 2021.11.30
     * @param e
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<ResponseDTO> handleAccessDeniedException(AccessDeniedException e) {
        log.error("handleAccessDeniedException", e);
        
        ResponseDTO response = new ResponseDTO(ErrorCode.HANDLE_ACCESS_DENIED);
        return getRespose(response);
    }

    
    /**
     * 업무 처리중 발생
     * @methodName : handleBusinessException
     * @author     : hslee
     * @date       : 2021.11.30
     * @param e
     * @return
     */
    @ExceptionHandler(BizException.class)
    protected ResponseEntity<ResponseDTO> handleBusinessException(final BizException e) {
        log.error("handleBusinessException", e);
        
        ResponseDTO response = new ResponseDTO();
        response.setCode(e.getErrorCode());
        response.setMessage(e.getMessage());
        response.setStatus(e.getStatus());

        return getRespose(response);
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<ResponseDTO> handleRuntimeException(final RuntimeException e) {
        log.error("handleRuntimeException", e);

        ResponseDTO response = new ResponseDTO();
        response.setCode("ZZZZZ");
        response.setMessage(e.getMessage());
        response.setStatus(500);

        return getRespose(response);
    }
	
    private ResponseEntity<ResponseDTO> getRespose(ResponseDTO responseDTO){
    	
    	try {
    		responseDTO.setMessage(messageHandler.getMessage(responseDTO.getCode()));
    	}catch(Exception e) {
    	}
    	
        return new ResponseEntity<>(responseDTO, HttpStatus.valueOf(responseDTO.getStatus()));
    }
}
