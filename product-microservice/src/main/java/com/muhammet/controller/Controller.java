package com.muhammet.controller;

import com.muhammet.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static  com.muhammet.constants.RestApis.*;

@RestController
@RequestMapping(PRODUCT)
@RequiredArgsConstructor
public class Controller {
    private final ProductService productService;
}
