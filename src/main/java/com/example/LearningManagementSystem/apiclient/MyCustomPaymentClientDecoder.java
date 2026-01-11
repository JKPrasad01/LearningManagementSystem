package com.example.LearningManagementSystem.apiclient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.Decoder;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;


public class MyCustomPaymentClientDecoder implements Decoder {


    @Override
    public Object decode(Response response, Type type) throws IOException {
        InputStream responseBody=response.body().asInputStream();

        return new ObjectMapper().readValue(responseBody, new TypeReference<Object>() {

            public Type getType(){
                return type;
            }
        });
    }
}
