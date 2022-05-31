package com.example.pancakem.exceptions;

import org.springframework.http.HttpStatus;

public class PancakeException extends HttpException{

    public PancakeException(){
        super(HttpStatus.UNPROCESSABLE_ENTITY,null);
    }
    public PancakeException(Object data){
        super(HttpStatus.UNPROCESSABLE_ENTITY, data);
    }

}
