package com.danilodps.concurrent.domain.service.impl;

import com.danilodps.concurrent.application.exception.NotFoundException;
import com.danilodps.concurrent.domain.adapter.UserCreateRequest2UserEntity;
import com.danilodps.concurrent.domain.adapter.UserCreateRequest2UserResponse;
import com.danilodps.concurrent.domain.adapter.UserEntity2UserResponse;
import com.danilodps.concurrent.domain.adapter.UserUpdateRequest2UserResponse;
import com.danilodps.concurrent.domain.model.entity.UserEntity;
import com.danilodps.concurrent.domain.model.request.create.UserCreateRequest;
import com.danilodps.concurrent.domain.model.request.update.UserUpdateRequest;
import com.danilodps.concurrent.domain.model.response.UserResponse;
import com.danilodps.concurrent.domain.repository.UserEntityRepository;
import com.danilodps.concurrent.domain.service.AsyncService;
import com.danilodps.concurrent.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserEntityRepository userEntityRepository;
    private final AsyncService asyncService;

    @Override
    public UserResponse getInfo(String userId) {
        UserEntity user = userEntityRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        return UserEntity2UserResponse.convert(user);
    }

    @Override
    public UserResponse create(UserCreateRequest userCreateRequest) {
        log.info("Criando usuário");
        UserEntity user = UserCreateRequest2UserEntity.convert(userCreateRequest);
        user.setUserId(UUID.randomUUID().toString());
        userEntityRepository.saveAndFlush(user);

        asyncService.getAsyncValue(user.getUserId());
        return UserCreateRequest2UserResponse.convert(user);
    }

    @Override
    public UserResponse update(UserUpdateRequest userUpdateRequest) {
        log.info("Atualizando solicitação");
        UserEntity user = userEntityRepository.findById(userUpdateRequest.getUserId())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        asyncService.getAsyncValue(userUpdateRequest.getUserId());

        return UserUpdateRequest2UserResponse.convert(user);
    }
}
