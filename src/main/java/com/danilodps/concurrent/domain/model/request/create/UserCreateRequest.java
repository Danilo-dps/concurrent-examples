package com.danilodps.concurrent.domain.model.request.create;

import lombok.Builder;

@Builder
public record UserCreateRequest(String username) {}
