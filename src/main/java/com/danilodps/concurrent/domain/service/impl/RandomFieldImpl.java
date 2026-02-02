package com.danilodps.concurrent.domain.service.impl;

import com.danilodps.concurrent.domain.model.entity.UserEntity;
import com.danilodps.concurrent.domain.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RandomFieldImpl {

    private final UserEntityRepository userEntityRepository;

    public void generateValues(UserEntity userEntity){

    }
}
