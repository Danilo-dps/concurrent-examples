package com.danilodps.concurrent.domain.model.request.update;

import com.danilodps.concurrent.domain.model.request.IRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest implements IRequest {
    private String userId;
}
