package com.muhammet.utility;

import com.muhammet.exception.AuthMicroserviceException;
import com.muhammet.exception.ErrorType;
import org.springframework.stereotype.Component;

@Component
public class TokenGenerator {

    public String createToken(Long userid){
        String token = "Brr:"+userid;
        return token;
    }

    public Long decodeToken(String token){
        try{
            String userid = token.split(":")[1];
            return Long.parseLong(userid);
        }catch (Exception e){
            throw new AuthMicroserviceException(ErrorType.INTERNAL_ERROR);
        }
    }

}
