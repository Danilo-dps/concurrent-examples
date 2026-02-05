package com.danilodps.concurrent.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RandomUpdate {
    private String randomColor;
    private Integer randomNumber;
}
