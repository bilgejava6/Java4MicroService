package com.muhammet.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {

    INTERNAL_ERROR(5100,"Sunucuda beklenmeyen hata oluştu",HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_TOKEN(5200,"Geçersiz token bilgisi",HttpStatus.BAD_REQUEST),
    UNAUTHORIZED_REQUEST(5201,"Yetkisiz giriş denemesi",HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST_ERROR(4300, "Parametre eksik yada hatalı", HttpStatus.BAD_REQUEST);

    int code;
    String message;
    HttpStatus httpStatus;
}
