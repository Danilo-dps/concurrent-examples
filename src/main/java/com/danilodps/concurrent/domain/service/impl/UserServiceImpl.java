package com.danilodps.concurrent.domain.service.impl;

import com.danilodps.concurrent.application.exception.NotFoundException;
import com.danilodps.concurrent.domain.adapter.UserCreateRequest2UserEntity;
import com.danilodps.concurrent.domain.adapter.UserEntity2UserResponse;
import com.danilodps.concurrent.domain.model.entity.UserEntity;
import com.danilodps.concurrent.domain.model.request.create.UserCreateRequest;
import com.danilodps.concurrent.domain.model.request.update.UserUpdateRequest;
import com.danilodps.concurrent.domain.model.response.UserResponse;
import com.danilodps.concurrent.domain.repository.UserEntityRepository;
import com.danilodps.concurrent.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserEntityRepository userEntityRepository;

    @Override
    public UserResponse getInfo(String userId) {
        UserEntity user = userEntityRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        UserEntity2UserResponse.convert(user);
        return UserEntity2UserResponse.convert(user);
    }

    @Override
    public UserResponse create(UserCreateRequest userCreateRequest) {
        UserEntity user = UserCreateRequest2UserEntity.convert(userCreateRequest);
        userEntityRepository.saveAndFlush(user);

        return UserEntity2UserResponse.convert(user);
    }

    @Override
    public UserResponse update(UserUpdateRequest userUpdateRequest) {
        return null;
    }
}
