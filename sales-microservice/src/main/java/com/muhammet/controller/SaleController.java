package com.muhammet.controller;

import com.muhammet.dto.request.BaseRequestDto;
import com.muhammet.repository.entity.Sale;
import com.muhammet.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.muhammet.constants.RestApis.*;
@RestController
@RequestMapping(SALES)
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @PostMapping(GETALL)
    public ResponseEntity<List<Sale>> getAll(@RequestBody @Valid BaseRequestDto dto){
        return ResponseEntity.ok(saleService.findAll());
    }
}
