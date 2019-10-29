package com.kainattu.stock.api.dto;

import lombok.Data;
import lombok.ToString;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

@Data
@ToString
public class CustomResponse extends ResourceSupport {

    private Object data;
    private String errorCode;
    private String errorMessage;
    private Response response;


    public enum Response{
        SUCCESS,FAILURE;
    }

    public CustomResponse(Response response,Object data) {
        this.data = data;
        this.response = response;
    }

    public CustomResponse(Response response, String errorCode, String errorMessage) {
        this.data = data;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.response = response;
    }
}
