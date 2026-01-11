package com.example.LearningManagementSystem.apiclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestTemplate;
import feign.codec.Encoder;

import java.lang.reflect.Type;

public class MyCustomPaymentClientEncoder implements Encoder {

    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template){

        try{
            String json=new ObjectMapper().writeValueAsString(object);
            template.body(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
