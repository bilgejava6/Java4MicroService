package com.muhammet.service;

import com.muhammet.exception.ErrorType;
import com.muhammet.exception.UserProfileMicroserviceException;
import com.muhammet.repository.IUserProfileRepository;
import com.muhammet.repository.entity.UserProfile;
import com.muhammet.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {
    private final IUserProfileRepository repository;

    public List<UserProfile> findAll(Long userid){
        Optional<UserProfile> userProfile = repository.findOptionalByAuthid(userid);
        if(userProfile.isEmpty())
            throw new UserProfileMicroserviceException(ErrorType.UNAUTHORIZED_REQUEST);
        return findAll();
    }

    public UserProfileService(IUserProfileRepository repository){
        super(repository);
        this.repository = repository;
    }
}
