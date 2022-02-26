package com.vadim.weatherparser.model;

import java.time.DayOfWeek;

public class Weather {

    private Integer maxTemp;
    private Integer minTemp;
    private DayOfWeek dayOfWeek;
    private Integer date;

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

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
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
