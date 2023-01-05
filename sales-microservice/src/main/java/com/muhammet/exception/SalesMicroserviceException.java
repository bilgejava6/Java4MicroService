package com.muhammet.exception;

import lombok.Getter;

@Getter
public class SalesMicroserviceException extends  RuntimeException{
    private final ErrorType errorType;

    public SalesMicroserviceException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public SalesMicroserviceException(ErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }
}
