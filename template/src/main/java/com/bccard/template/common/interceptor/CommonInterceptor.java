package com.bccard.template.common.interceptor;

import com.bccard.template.common.utils.LogUtils;
import com.bccard.template.common.dto.CommonDTO;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CommonInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        LogUtils.info("in");

        // fhkrwogk 샘플
        CommonDTO commonDTO = new CommonDTO();
        commonDTO.setHTxId(request.getHeader("hTxId"));
        commonDTO.setHTxId("txIdTest00192idj83bsj");
        request.setAttribute("commonDTO", commonDTO);
        request.getSession().setAttribute("commonDTO", commonDTO);

        return true;
    }
}
