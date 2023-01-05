package com.muhammet.service;

import com.muhammet.repository.IProductRepository;
import com.muhammet.repository.entity.Product;
import com.muhammet.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends ServiceManager<Product,Long> {
    private final IProductRepository repository;
    public ProductService(IProductRepository repository){
        super(repository);
        this.repository = repository;
    }

}
