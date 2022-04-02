package com.bccard.template.common.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.bccard.template.common.utils.LogUtils;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WebClientHandler {

    private final WebClient webClient;

    public String get(String uri){

        ResponseEntity<String> result = webClient.get()
                .uri(uri)
                .retrieve()
                .toEntity(String.class)
                //.bodyToMono(Object.class)
                .block();

        LogUtils.info("result : {}", result);

        return result.getBody();
    }


}
