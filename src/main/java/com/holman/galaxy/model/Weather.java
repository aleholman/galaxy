package com.holman.galaxy.model;

public enum Weather {
    DROUGHT("Sequía"),
    RAIN("Lluvia"),
    OPTIMAL_CONDITIONS("Presión y temperatura óptimas"),
    UNKNONW("Desconocido");

    private final String value;

    private Weather(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public String getValue() {
        return value;
    }
}
