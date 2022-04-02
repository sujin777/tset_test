package com.bccard.template.common.utils;

import com.bccard.template.common.consts.ErrorCode;
import com.bccard.template.common.exceptions.BizException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class JacksonUtils {
	
	private static final ObjectMapper objectMapper = new ObjectMapper()
			.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	
    /**
     * Json String -> List * * @param dto * @return
     */
    public static List<Object> convJsonToList(String json) {
        List<Object> list = null;
        try {
            list = objectMapper.readValue(json, new TypeReference<List<Object>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    public static <T> T convetObject(String str, Class<T> targetClass) {

        T result = null;
        try{
            result = objectMapper.readValue(str, targetClass);
        }catch(Exception e){
            LogUtils.error(e.getMessage());
            throw new BizException(ErrorCode.INTERNAL_SERVER_ERROR1);
        }

        return result;
    }

    public static <T> T convetObject(Object object, Class<T> targetClass) {

        T result = null;
        try{
            result = objectMapper.convertValue(object, targetClass);
        }catch(Exception e){
            LogUtils.error(e.getMessage());
            throw new BizException(ErrorCode.INTERNAL_SERVER_ERROR1);
        }

        return result;
    }

}

