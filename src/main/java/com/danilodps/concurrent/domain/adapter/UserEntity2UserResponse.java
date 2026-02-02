package com.danilodps.concurrent.domain.adapter;

import com.danilodps.concurrent.domain.model.entity.UserEntity;
import com.danilodps.concurrent.domain.model.response.UserResponse;

import static java.util.Objects.nonNull;

public class UserEntity2UserResponse {

    private UserEntity2UserResponse(){}

    public static UserResponse convert(UserEntity userEntity) {
        if(nonNull(userEntity.getRandomColor()) && nonNull(userEntity.getRandomNumber())){
            return UserResponse.builder()
                    .userId(userEntity.getUserId())
                    .username(userEntity.getUsername())
                    .createdAt(userEntity.getCreatedAt().toString())
                    .randomColor(userEntity.getRandomColor())
                    .randomNumber(userEntity.getRandomNumber())
                    .build();
        }

        return UserResponse.builder()
                .userId(userEntity.getUserId())
                .username(userEntity.getUsername())
                .createdAt(userEntity.getCreatedAt().toString())
                .message("Erro no processo de criação de cor e número")
                .build();
    }
}
