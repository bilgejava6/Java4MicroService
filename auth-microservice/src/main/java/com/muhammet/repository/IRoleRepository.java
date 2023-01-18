package com.muhammet.repository;

import com.muhammet.repository.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleRepository extends JpaRepository<Role,Long> {

    List<Role> findByAuthid(Long authid);
}
