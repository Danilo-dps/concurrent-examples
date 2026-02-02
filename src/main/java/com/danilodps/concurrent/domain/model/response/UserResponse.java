package com.danilodps.concurrent.domain.model.response;

import lombok.Builder;

@Builder
public record UserResponse (String userId, String username, String createdAt, String randomColor, Integer randomNumber, String message){
}
