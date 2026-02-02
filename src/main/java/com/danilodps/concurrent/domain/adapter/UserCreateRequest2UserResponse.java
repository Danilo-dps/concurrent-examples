package com.danilodps.concurrent.domain.adapter;

import com.danilodps.concurrent.domain.model.entity.UserEntity;
import com.danilodps.concurrent.domain.model.response.UserResponse;

public class UserCreateRequest2UserResponse {

    private UserCreateRequest2UserResponse(){}

    public static UserResponse convert(UserEntity userEntity) {
        return UserResponse.builder()
                .userId(userEntity.getUserId())
                .username(userEntity.getUsername())
                .createdAt(userEntity.getCreatedAt().toString())
                .message("Solicitação criada: sua cor e número estão em processamento")
                .build();
    }
}
