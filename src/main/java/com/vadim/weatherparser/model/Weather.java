package com.vadim.weatherparser.model;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Weather {

    private Integer maxTemp;
    private Integer minTemp;
    private DayOfWeek dayOfWeek;
    private LocalDate date;

    public Weather(Integer maxTemp, Integer minTemp, DayOfWeek dayOfWeek, LocalDate date) {
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.dayOfWeek = dayOfWeek;
        this.date = date;
    }

    public Integer getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(Integer maxTemp) {
        this.maxTemp = maxTemp;
    }

    public Integer getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(Integer minTemp) {
        this.minTemp = minTemp;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "maxTemp=" + maxTemp +
                ", minTemp=" + minTemp +
                ", dayOfWeek='" + dayOfWeek + '\'' +
               // ", number=" + number +
                '}';
    }
}
