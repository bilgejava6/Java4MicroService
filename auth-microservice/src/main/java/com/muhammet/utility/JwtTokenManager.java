package com.muhammet.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JwtTokenManager {

    private final String sifreAnahtari = "1234";
    //@Value("${SECRETKEY}")
    //String secretKey;
    /**
     * Login olan kişinin Id bilgisini alarak ona yeni bir token üretmek için kullanılır.
     * @param id
     * @return
     */
    public Optional<String> createToken(Long id){
        String token;

        Long exDate = 1000L*60*15;
        try{
            /**
             * DİKKAT!!!  kullanıcı adı şifre Claim içine konulmaz.
             * Claim nesnesi olarak yerleştirdiğiniz bilgilerin açık okunur olduğunu asla unutmayınız.
             */
         token =  JWT.create()
                    .withAudience()
                    .withClaim("id",id) // Token içerisine eklemek istediğiniz nesneleri bununla ekliyoruz.
                    .withClaim("howtopage","AuthMicroService")
                    .withClaim("isOne",true)
                    .withIssuer("java4") // sahibi
                    .withIssuedAt(new Date()) // oluşturulma zamanı
                    .withExpiresAt(new Date(System.currentTimeMillis()+exDate)) // geçersiz olma zamanı
                    .sign(Algorithm.HMAC512(sifreAnahtari));
         return Optional.of(token);
        }catch (Exception ex){
            return Optional.empty();
        }
    }
    /**
     * Kullanıcı tarafından verilen token bilgisinin doğruluğunu kontrol etmek için kullanırız.
     * token imzası ve geçirliliği kontrol edilir.
     * @param token
     * @return
     */
    public Boolean validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC512(sifreAnahtari);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("java4")
                    .build();
           DecodedJWT decodedJWT = verifier.verify(token);
           if(decodedJWT==null)
               return false;
        }catch (Exception exception){
            return false;
        }
        return true;
    }

    /**
     * Token bilgisi gönderen kullanıcının Id bilgisi token Payload içinden alınır.
     * @param token
     * @return
     */
    public Optional<Long> getByIdFromToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC512(sifreAnahtari);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("java4")
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if(decodedJWT==null)
                return Optional.empty();
            return Optional.of(decodedJWT.getClaim("id").asLong());
        }catch (Exception exception){
            return Optional.empty();
        }

    }
}
