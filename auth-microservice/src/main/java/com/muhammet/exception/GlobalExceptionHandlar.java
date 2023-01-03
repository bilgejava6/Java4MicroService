package com.muhammet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandlar {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<String> handlerRuntimeException(Exception exception){
        System.out.println("Hata oluştu");
        return ResponseEntity.ok(exception.getMessage());
    }

    @ExceptionHandler(AuthMicroserviceException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handlerAuthMicroseviceException(AuthMicroserviceException exception){
        return new ResponseEntity<>(createErrorMessage(exception,exception.getErrorType()), HttpStatus.BAD_REQUEST);
    }

    /**
     * Hata yakalama işlemleri bir çok hata için ayrı ayrı yapılmalıdır. bu nedenel tüm hataların
     * içerisine log alma işlemi yazmak zorunda kalırız. bu işlemleri tekelleştirmek ve hata log kayıtlarını
     * toplamak için tekbir method kullanmak daha doğru olacaktır.
     * @param exception
     * @param errorType
     * @return
     */
    private ErrorMessage createErrorMessage(Exception exception,ErrorType errorType){
        System.out.println("Tüm hataların geçtiği nokta...: "+ exception.getMessage());
        return ErrorMessage.builder()
                .message(errorType.getMessage())
                .code(errorType.getCode())
                .build();
    }
}
