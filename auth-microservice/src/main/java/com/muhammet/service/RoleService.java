package com.muhammet.service;

import com.muhammet.repository.IRoleRepository;
import com.muhammet.repository.entity.Role;
import com.muhammet.utility.ServiceManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService extends ServiceManager<Role,Long> {
    private final IRoleRepository repository;
    public RoleService(IRoleRepository repository) {
        super(repository);
        this.repository=repository;
    }

    public List<Role> findByAuthid(Long id){
        return repository.findByAuthid(id);
    }
}
