package com.danilodps.concurrent.domain.model.request.create;

import com.danilodps.concurrent.domain.model.request.IRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequest implements IRequest {
    private String username;
}
