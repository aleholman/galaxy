package com.holman.galaxy.model;

public enum TurningMode {

    CLOCKWISE(-1),
    COUNTER_CLOCKWISE(1);

    private final Integer value;

    private TurningMode(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }

}
