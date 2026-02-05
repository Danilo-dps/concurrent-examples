package com.danilodps.concurrent.domain.adapter;

import com.danilodps.concurrent.domain.model.entity.UserEntity;
import com.danilodps.concurrent.domain.model.request.create.UserCreateRequest;

import java.time.LocalDateTime;

public class UserCreateRequest2UserEntity {

    private UserCreateRequest2UserEntity(){}

    public static UserEntity convert(UserCreateRequest userCreateRequest){
        return UserEntity.builder()
                .username(userCreateRequest.getUsername())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
