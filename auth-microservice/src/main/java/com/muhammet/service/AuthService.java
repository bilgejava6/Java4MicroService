package com.muhammet.service;

import com.muhammet.dto.request.CreateProfileRequestDto;
import com.muhammet.dto.request.RegisterRequestDto;
import com.muhammet.dto.response.RegisterResponseDto;
import com.muhammet.exception.AuthMicroserviceException;
import com.muhammet.exception.ErrorType;
import com.muhammet.manager.IUserProfileManager;
import com.muhammet.mapper.IAuthMapper;
import com.muhammet.repository.IAuthRepository;
import com.muhammet.repository.entity.Auth;
import com.muhammet.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class AuthService extends ServiceManager<Auth,Long> {
    private final IAuthRepository repository;
    /**
     * DİKKAT!!!!!
     * kullanmak istediğiniz interface, services, component gibi sınıflardan nesne türetmeke için 2 yolunuz va r
     * @Autowired ile işaretlemek ya da Constructor Injection ile kullanmak.
     */
    private final IUserProfileManager userProfileManager;
    public AuthService(IAuthRepository repository, IUserProfileManager userProfileManager){
        super(repository);
        this.repository=repository;
        this.userProfileManager = userProfileManager;
    }

    /**
     * DİKKAT!!! bu save method u ServiceManager methosunun
     * overload edilmiş halidir. DTO ile işlem yapar.
     *
     * @param dto
     * @return
     */
    public RegisterResponseDto save(RegisterRequestDto dto){
        /**
         * Eğer şifre ile ikinci şifre uyuşmuyor ise, direkt false
         * dönülmesi mantıklıdır.
         */
        if(!dto.getPassword().equals(dto.getRepassword()))
            throw  new AuthMicroserviceException(ErrorType.REGISTER_REPASSWORD_ERROR);
        /**
         * Burada elle dönüşüm yerine Mapper kullanmak daha
         * doğru olacaktır.
         */
        /* Elle dönüşüm işlemi
        save(
                Auth.builder()
                        .email(dto.getEmail())
                        .username(dto.getUsername())
                        .password(dto.getPassword())
                        .build()
        );
         */
        if(repository.findOptionalByUsername(dto.getUsername()).isPresent())
            throw new AuthMicroserviceException(ErrorType.REGISTER_KULLANICIADI_KAYITLI);

        Auth auth = save(IAuthMapper.INSTANCE.fromRegisterRequestDto(dto));
        userProfileManager.createProfile(CreateProfileRequestDto.builder()
                        .token("")
                        .authid(auth.getId())
                        .username(auth.getUsername())
                        .email(auth.getEmail())
                .build());
        RegisterResponseDto result = IAuthMapper.INSTANCE.fromAuth(auth);
        result.setRegisterstate(100);
        result.setContent(auth.getEmail()+" ile başarı şekilde kayıt oldunuz.");
        return  result;

    }
}
