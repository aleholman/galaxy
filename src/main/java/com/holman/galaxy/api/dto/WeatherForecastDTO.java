package com.holman.galaxy.api.dto;

public class WeatherForecastDTO {

    private Integer dia;
    private String clima;

    public WeatherForecastDTO(Integer dia, String clima) {
        this.dia = dia;
        this.clima = clima;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

}
