package com.danilodps.concurrent.domain;

import lombok.Getter;

@Getter
public enum ColorsEnum {

    RED(1, "RED"),
    ORANGE(2, "ORANGE"),
    YELLOW(3, "YELLOW"),
    GREEN(4, "GREEN"),
    BLUE(5, "BLUE"),
    INDIGO(6, "INDIGO"),
    VIOLET(7, "VIOLET");

    private final Integer colorId;
    private final String color;

    ColorsEnum(Integer colorId, String color) {
        this.colorId = colorId;
        this.color = color;
    }
}
