package com.muhammet.utility;

import com.muhammet.dto.request.UserProfileSaveRequestDto;
import com.muhammet.manager.IUserProfileElasticService;
import com.muhammet.repository.IUserProfileRepository;
import com.muhammet.repository.entity.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MyData {

    private final IUserProfileRepository repository;
    private final IUserProfileElasticService manager;

   // @PostConstruct
    public void init(){
        List<UserProfile> plist = repository.findAll();
        plist.forEach(u->{
            manager.save(UserProfileSaveRequestDto.builder()
                            .username(u.getUsername())
                            .authid(u.getAuthid())
                            .profileimage(u.getProfileimage())
                            .userid(u.getId())
                    .build());
        });
    }

}
