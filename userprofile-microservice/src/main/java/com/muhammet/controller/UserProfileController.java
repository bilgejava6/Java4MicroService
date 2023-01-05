package com.muhammet.controller;

import com.muhammet.dto.request.BaseRequestDto;
import com.muhammet.dto.request.CreateProfileRequestDto;
import com.muhammet.repository.entity.UserProfile;
import com.muhammet.service.UserProfileService;
import com.muhammet.utility.TokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.List;

import static com.muhammet.constants.RestApis.*;
@RestController
@RequestMapping(USERPROFILE)
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;
    private final TokenGenerator tokenGenerator;
    @PostMapping(GETALL)
    public ResponseEntity<List<UserProfile>> userProfileList(@RequestBody @Valid BaseRequestDto dto){
        Long userid = tokenGenerator.decodeToken(dto.getToken());
        return ResponseEntity.ok(userProfileService.findAll(userid));
    }

    /**
     * DİKKAT!!!!
     * mutlaka, @RequestBody ve @Valid eklentilerini yapınız.
     */
    @PostMapping(CREATEPROFILE)
    public ResponseEntity<Boolean> createProfile(@RequestBody @Valid CreateProfileRequestDto dto){
        userProfileService.save(
                UserProfile.builder()
                        .authid(dto.getAuthid())
                        .email(dto.getEmail())
                        .username(dto.getUsername())
                        .build()
        );
        return ResponseEntity.ok(true);
    }

}
