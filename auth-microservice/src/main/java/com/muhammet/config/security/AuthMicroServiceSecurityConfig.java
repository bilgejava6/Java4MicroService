package com.muhammet.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthMicroServiceSecurityConfig {

    @Bean
    JwtTokenFilter getJwtTokenFilter(){
        return new JwtTokenFilter();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        /**
         * _csrf kapatma ve açma işlemlerinde kullanılır. Sisteminizin gvende
         * kalmaısnı sağlamak için kullanılır.
         */
        httpSecurity.csrf().disable();

        /**
         * Öncelikle gelen tüm isteklere doğrulama uygulayacağımız bildiriytoruz.
         * ** antMatchers -> izin verilecek yada veirlmeyecek end point lerin belirlendiği alandır.
         * ** permitAll -> gelen tüm isteklere izin ver.
         * ** anyRequest -> herhangi  bir istek.
         * ** authenticated -> oturum açmaya tabi tut. kimlik doğrulama yap
         */
        httpSecurity.authorizeRequests()
                .antMatchers("/v3/api-docs/**",
                        "/swagger-ui/**","/v1/dev/auth/dologin",
                        "/role/saverole","/v1/dev/auth/register").permitAll()
                        .anyRequest().authenticated();

        /**
         * Bir flitre uygulanacak bunun nasul yapılacağı ve hangi şekilde yapılacağının belirtilmesine
         * ihtiyaç vardır. bunun parametrelerini yazcağız.
         * 1- Filter Nesnesi
         * 2- Filter Type Class
         */
        httpSecurity
                .addFilterBefore(getJwtTokenFilter(),
                        UsernamePasswordAuthenticationFilter.class);

        /**
         * Gelen isteklere oturum açma zorunluluğu getirildikten sonra bir Login page
         * e yönlendirme yapmak gereklidir. bunun aktif edilmesi aşağıdaki şekilde olur.
         * Ayrıca kendinize ait bir Login Page kullanmak isterseniz. Bu safyanın
         * uzuntısını buraya girmeniz gereklidir.
         */
        //httpSecurity.formLogin();
        return httpSecurity.build();
    }

}
