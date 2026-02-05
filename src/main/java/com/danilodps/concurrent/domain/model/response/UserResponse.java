package com.danilodps.concurrent.domain.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserResponse (String userId, String username, String createdAt, String randomColor, Integer randomNumber, String message){
}
