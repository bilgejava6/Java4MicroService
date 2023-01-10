package com.muhammet.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class Fallbackcontorller {

    @GetMapping("/fallbackauth")
    public ResponseEntity<String> fallbackauth(){
        return ResponseEntity.ok("Servis şuan çalışmıyor. başka zaman.. :)");
    }
    @GetMapping("/fallbackproduct")
    public ResponseEntity<String> fallbackproduct(){
        return ResponseEntity.ok("Servis şuan çalışmıyor. başka zaman.. :)");
    }
    @GetMapping("/fallbacksales")
    public ResponseEntity<String> fallbacksales(){
        return ResponseEntity.ok("Servis şuan çalışmıyor. başka zaman.. :)");
    }
    @GetMapping("/fallbackuser")
    public ResponseEntity<String> fallbackuser(){
        return ResponseEntity.ok("Servis şuan çalışmıyor. başka zaman.. :)");
    }
}
