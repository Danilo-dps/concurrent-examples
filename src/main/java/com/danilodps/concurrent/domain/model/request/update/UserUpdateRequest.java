package com.danilodps.concurrent.domain.model.request.update;

import lombok.Builder;

@Builder
public record UserUpdateRequest(String userId) {}
