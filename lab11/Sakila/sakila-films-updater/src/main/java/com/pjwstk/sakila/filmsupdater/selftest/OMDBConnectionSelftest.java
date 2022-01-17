package com.pjwstk.sakila.filmsupdater.selftest;

import com.pjwstk.sakila.*;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Component
public class OMDBConnectionSelftest implements Selftest{

    RestTemplate rest;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @Override
    public SelfTestResult run(){
        SelfTestResult result;
        try{
            var response = rest.getForEntity("https://www.omdbapi.com/?i=tt3896198&apikey="
                    + System.getenv("OMDBapiKey"), String.class).getBody();

            if(!response.isBlank() && !response.isEmpty() && response != null){
                result = SelfTestResult.builder()
                        .name("OMDB api connection test")
                        .description("Checks if there is a connection with omdbapi.com")
                        .passed(true)
                        .errors(null)
                        .build();
            }else {
                throw new Exception();
            }
        }catch (Exception e){
            result = SelfTestResult.builder()
                    .name("OMDB api connection test")
                    .description("Checks if there is a connection with omdbapi.com")
                    .passed(false)
                    .errors(List.of("There is no connection with OMDB Api. Check your internet connection",e.getMessage()))
                    .build();
        }
        return result;
    }
}