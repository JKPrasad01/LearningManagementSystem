package com.example.LearningManagementSystem.apiclient;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class MyCustomPaymentClientErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultDecoder=new Default();

    public Exception decode(String methodKey, Response response){

        HttpStatus statusCode=HttpStatus.valueOf(response.status());

        if(statusCode.is4xxClientError()){
            return new MyCustomBadRequestException("Client Error");
        }
        else if(statusCode.is5xxServerError()){
            return new MyCustomServerException("server error");
        }else{
            return defaultDecoder.decode(methodKey,response);
        }
    }
}
