package com.danilodps.concurrent.domain.service;

import com.danilodps.concurrent.domain.model.request.create.UserCreateRequest;
import com.danilodps.concurrent.domain.model.request.update.UserUpdateRequest;
import com.danilodps.concurrent.domain.model.response.UserResponse;

public interface UserService {

    UserResponse getInfo(String userId);
    UserResponse create(UserCreateRequest userCreateRequest);
    UserResponse update(UserUpdateRequest userUpdateRequest);
}
