package com.muhammet.config.security;

import com.muhammet.repository.entity.Auth;
import com.muhammet.service.AuthService;
import com.muhammet.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JwtUserDetails implements UserDetailsService {
    @Autowired
    AuthService authService;
    @Autowired
    RoleService roleService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public UserDetails getUserByAuthId(Long authid){
        Optional<Auth> auth = authService.findById(authid);
        if (auth.isEmpty()) return null;
        List<GrantedAuthority> authorities = new ArrayList<>();
        /**
         * Burada belirtilen isimlendirmeler tamamen size aittir. özel bir kullanım değildir.
         * Yetki Adı: yönetici, asistan,
         */
        roleService.findByAuthid(authid).forEach(x->{
            authorities.add(new SimpleGrantedAuthority(x.getRole()));
        });
        return User.builder()
                .username(authid.toString())
                .password("")
                .accountLocked(false)
                .accountExpired(false)
                .authorities(authorities)
                .build();
    }
}
