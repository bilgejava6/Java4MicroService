package com.muhammet.graphql.mutation;

import com.muhammet.graphql.model.UserProfileInput;
import com.muhammet.repository.entity.UserProfile;
import com.muhammet.service.UserProfileService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserProfileMutationResolver implements GraphQLMutationResolver {
    private final UserProfileService userProfileService;

    public Boolean createUserProfile(UserProfileInput input){
        userProfileService.save(UserProfile.builder()
                        .username(input.getUsername())
                        .authid(input.getAuthid())
                        .profileimage(input.getProfileimage())
                        .userid(UUID.randomUUID().toString())
                .build());
        return true;
    }
}
