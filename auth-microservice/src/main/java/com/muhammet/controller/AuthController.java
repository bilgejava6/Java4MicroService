package com.muhammet.controller;

import com.muhammet.dto.request.DoLoginRequestDto;
import com.muhammet.dto.request.RegisterRequestDto;
import com.muhammet.dto.response.DoLoginResponseDto;
import com.muhammet.dto.response.RegisterResponseDto;
import com.muhammet.repository.entity.Auth;
import com.muhammet.repository.entity.State;
import com.muhammet.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.muhammet.constants.RestApi.*;
@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Value("${buuygulama.birdeger}")
    private String BuradaYMLdanDegerAlalim;

    /**
     * Bir end point e istek atarken farklı yollarla parametere gönderilebilir.
     * 1- Header, başlık içinde
     * 2- Body, form elementi içinde
     * burada bıdy içinde parametereleri almak daha güvenlidir.
     * @Valid
     * girilen bilgilerin belli kriterleri olmalıdır. mesela şifrenin karmaşıklığı
     * email adresinin doğru formatta olması v.s.
     * @param dto
     * @return
     */
    @PostMapping(DOLOGIN)
    @CrossOrigin("*")
    public ResponseEntity<DoLoginResponseDto> doLogin(@RequestBody @Valid DoLoginRequestDto dto){
        return ResponseEntity.ok(
                DoLoginResponseDto.builder()
                        .token(authService.doLogin(dto))
                        .build());
    }

    @CrossOrigin("*")
    @PostMapping(REGISTER)
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto){
        return ResponseEntity.ok(authService.save(dto));
    }

    @GetMapping("/say")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok( "Selam arkadaşlar ben Auth");
    }

    @GetMapping("/sayadmin")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<String> sayHelloAdmin(){
        return ResponseEntity.ok( "Selam arkadaşlar ben Admin Auth");
    }

}
