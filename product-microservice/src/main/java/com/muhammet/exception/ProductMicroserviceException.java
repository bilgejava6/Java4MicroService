package com.muhammet.exception;

import lombok.Getter;

@Getter
public class ProductMicroserviceException extends  RuntimeException{
    private final ErrorType errorType;

    public ProductMicroserviceException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public ProductMicroserviceException(ErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }
}
