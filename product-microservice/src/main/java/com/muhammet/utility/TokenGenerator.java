package com.muhammet.utility;

import com.muhammet.exception.ErrorType;
import com.muhammet.exception.ProductMicroserviceException;
import org.springframework.stereotype.Component;

@Component
public class TokenGenerator {

    public String createToken(Long userid){
        String token = "Brr:"+userid;
        return token;
    }

    public Long decodeToken(String token){
        try{
            if(!token.split(":")[0].equals("Brr"))
                throw  new ProductMicroserviceException(ErrorType.INVALID_TOKEN);
            String userid = token.split(":")[1]; //rwerwer16  --  Brr:16 -> [Brr,16]
            return Long.parseLong(userid);
        }catch (Exception e){
            throw new ProductMicroserviceException(ErrorType.INVALID_TOKEN);
        }
    }

}
