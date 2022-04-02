/**
 * 
 */
package com.bccard.template.common.utils;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bccard.template.common.dto.CommonDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @packageName : com.hoban.common.util
 * @fileName    : MessageUtils.java
 * @author      : hslee
 * @date        : 2021.11.30
 * @description :
 * ===========================================================
 * DATE                       AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2021.11.30                 hslee       최초 생성
 */
@Slf4j
@RequiredArgsConstructor
public class LogUtils {
	
    private static final int BASE_TARGET_STACK_TRACE_ELEMENT_DEPTH = 4;
    private static final String LOG_MARKER_NAME = "MESSAGE";

    private static final Marker MESSAGE_MARKER = MarkerFactory.getMarker(LOG_MARKER_NAME);

    public static void info(String msg, Object... params){
        _info(null, null, msg, params);
    }

    public static void error(String msg, Object... params){
        _error(null, null, msg, params);
    }

    public static void info(CommonDTO commonDTO, String msg, Object... params){
        _info(null, commonDTO, msg, params);
    }

    public static void error(CommonDTO commonDTO, String msg, Object... params){
        _error(null, commonDTO, msg, params);
    }

    public static void infoWithDisk(String msg, Object... params){
        _info(MESSAGE_MARKER, null, msg, params);
    }

    public static void errorWithDisk(String msg, Object... params){
        _error(MESSAGE_MARKER, null, msg, params);
    }

    public static void infoWithDisk(CommonDTO commonDTO, String msg, Object... params){
        _info(MESSAGE_MARKER, commonDTO, msg, params);
    }

    public static void errorWithDisk(CommonDTO commonDTO, String msg, Object... params){
        _error(MESSAGE_MARKER, commonDTO, msg, params);
    }

    public static void infoByCommonDTO(String msg, Object... params){
        _info(null, _getCommonDTO(), msg, params);
    }

    public static void errorByCommonDTO(String msg, Object... params){
        _error(null, _getCommonDTO(), msg, params);
    }

    public static void infoWithDiskByCommonDTO(String msg, Object... params){
        _info(MESSAGE_MARKER, _getCommonDTO(), msg, params);
    }

    public static void errorWithDiskByCommonDTO(String msg, Object... params){
        _error(MESSAGE_MARKER, _getCommonDTO(), msg, params);
    }

    private static void _info(Marker marker, CommonDTO commonDTO, String msg, Object... params){
        String pathStr = _getSte(BASE_TARGET_STACK_TRACE_ELEMENT_DEPTH, commonDTO);
        log.info(marker, pathStr + msg, params);
    }

    private static void _error(Marker marker, CommonDTO commonDTO, String msg, Object... params){
        String pathStr = _getSte(BASE_TARGET_STACK_TRACE_ELEMENT_DEPTH, commonDTO);
        log.error(marker, pathStr + msg, params);
    }

    private static CommonDTO _getCommonDTO(){
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return (CommonDTO) req.getSession().getAttribute("commonDTO");
    }

    private static String _getSte(int depth, CommonDTO commonDTO){

        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        String fileName = ste[depth].getFileName();
        String methodName = ste[depth].getMethodName();
        int lineNumber = ste[depth].getLineNumber();

        String commonStr = "";
        if(commonDTO != null){
            commonStr = " - txId : " + commonDTO.getHTxId();
        }

        String result = "["+fileName +":"+lineNumber+"] :: ["+ methodName + commonStr +"] ";
        return result;
    }

}
