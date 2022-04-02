/**
 * 
 */
package com.bccard.template.common.handler;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

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

@Component
@RequiredArgsConstructor
public class MessageHandler {

	static final String DefaultMsg = "-MSG_UNDEFINED";

    private final MessageSource messageSource;

    /**
     * 입력된 code 에 해당하는 message 를 반환
     * @param code 에러코드
     * @return 에러 메시지
     */
    public String getMessage(String code){
        return messageSource.getMessage("error."+code, null, Locale.getDefault());
    }
}
