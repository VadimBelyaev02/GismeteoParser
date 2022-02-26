package com.vadim.weatherparser;

import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WeatherServiceImpl implements WeatherService {

    private final WeatherStorage storage;

    public WeatherServiceImpl(String city) {
         storage = new WeatherStorage(city);
    }

    @Override
    public Weather getWeather(String city) {
        return null;
    }


    public Weather getDayWeather(int day) {
        return storage.getDayWeather(day);
    }

    public List<Weather> getMonthWeather() {
        return storage.getWeathers();
    }

    public List<Weather> getWeathers() {
        return storage.getWeathers();
    }

    public Weather getHottestDay() {
        return storage.getWeathers().stream().min(Comparator.comparingInt(Weather::getMaxTemp)).get();
    }
    
    public Weather getColdestDay() {
        return storage.getWeathers().stream().max(Comparator.comparingInt(Weather::getMinTemp)).get();
    }


    public List<Weather> getSortedWeather(boolean ascending) {
        if (ascending) {

        }
        return storage.getWeathers().stream()
                .sorted(Comparator.comparingInt(Weather::getMaxTemp))
                .collect(Collectors.toList());
    }
}
