package com.muhammet.service;

import com.muhammet.repository.ISaleRepository;
import com.muhammet.repository.entity.Sale;
import com.muhammet.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class SaleService extends ServiceManager<Sale,Long> {
    private final ISaleRepository repository;
    public SaleService(ISaleRepository repository){
        super(repository);
        this.repository=repository;
    }
}
