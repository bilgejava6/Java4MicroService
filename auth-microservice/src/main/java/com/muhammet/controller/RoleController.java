package com.muhammet.controller;

import com.muhammet.repository.entity.Role;
import com.muhammet.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;


    @PostMapping("/saverole")
    public ResponseEntity<Void> saveRole(String RoleName, Long authid){
        roleService.save(Role.builder()
                        .role(RoleName)
                        .authid(authid)
                .build());
        return ResponseEntity.ok().build();
    }
}
