package com.muhammet.service;

import com.muhammet.dto.request.BaseRequestDto;
import com.muhammet.exception.ErrorType;
import com.muhammet.exception.UserProfileMicroserviceException;
import com.muhammet.repository.IUserProfileRepository;
import com.muhammet.repository.entity.UserProfile;
import com.muhammet.utility.JwtTokenManager;
import com.muhammet.utility.ServiceManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile,String> {
    private final IUserProfileRepository repository;
    private final JwtTokenManager jwtTokenManager;


    public UserProfileService(IUserProfileRepository repository, JwtTokenManager jwtTokenManager){
        super(repository);
        this.repository = repository;
        this.jwtTokenManager = jwtTokenManager;
    }

    public Page<UserProfile> findAll(BaseRequestDto dto){
        Optional<Long> authid = jwtTokenManager.getByIdFromToken(dto.getToken());
        if(authid.isEmpty())
            throw new UserProfileMicroserviceException(ErrorType.INVALID_TOKEN);
        Optional<UserProfile> userProfile = repository.findOptionalByAuthid(authid.get());
        if(userProfile.isEmpty())
            throw new UserProfileMicroserviceException(ErrorType.UNAUTHORIZED_REQUEST);
        /**
         * Sort.Direction.fromString(dto.getDirection()) -> "ASC" - "DESC" -> Enum
         * dto.getSortParameter() -> ID ye göre sırala, username göre sırala
         */

        Sort sort = null;
        Pageable pageable = null;
        /**
         * Sıralama parametresi mutlaka dolu gelmeli, değil ise default bir değer atanmalı
         * Hangi alanın sıralanacağı isteğe bağlıdır, eğer sıralama alanı belirtilmemiş ise sıralama yapmayın.
         */
        if(dto.getSortParameter()!=null)
            sort= Sort.by(Sort.Direction.fromString(dto.getDirection()==null ? "ASC" : dto.getDirection() ),
                    dto.getSortParameter());
        /**
         * dto.getCurrentPage() -> hangi sayfadasın
         * dto.getPageSize() -> bir sayafa gösterilecek kayıt sayısı nekadar
         * sort > sıralama var mı?
         */
        if(sort!=null && dto.getCurrentPage()!=null){
            pageable = PageRequest.of(dto.getCurrentPage(),dto.getPageSize()==0 ? 10 : dto.getPageSize(),sort);
        }else if(sort==null && dto.getCurrentPage()!=null){
            pageable = PageRequest.of(dto.getCurrentPage(),dto.getPageSize()==0 ? 10 : dto.getPageSize());
        }else{
            pageable = PageRequest.of(0,dto.getPageSize()==0 ? 10 : dto.getPageSize());
        }
        return findAll(pageable);
    }
    @Cacheable(value = "getuppercase")
    public String getUpperCase(String name){
        try{
            Thread.sleep(3000L);
        }catch (Exception exception){

        }
        return name.toUpperCase();
    }


    @CacheEvict(value = "getuppercase", allEntries = true )
    public void clearCache(){
        System.out.println("Belleği temizledim.");
    }
}
