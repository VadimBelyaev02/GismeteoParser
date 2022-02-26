package com.vadim.weatherparser.service.impl;

import com.vadim.weatherparser.Weather;
import com.vadim.weatherparser.exception.NotFoundException;
import com.vadim.weatherparser.service.WeatherService;
import com.vadim.weatherparser.storage.impl.WeatherStorageImpl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class WeatherServiceImpl implements WeatherService {

    private final WeatherStorageImpl storage;

    public WeatherServiceImpl(String city) {
        storage = new WeatherStorageImpl(city);
    }

    @Override
    public Weather getWeather(String city) {
        return null;
    }

    @Override
    public Weather getDayWeather(int day) {
        return storage.getDayWeather(day);
    }

    @Override
    public List<Weather> getMonthWeather() {
        return storage.getWeathers();
    }

    @Override
    public List<Weather> getWeathers() {
        return storage.getWeathers();
    }

    @Override
    public Weather getHottest(boolean isDay) {
        Optional<Weather> optionalWeather;
        if (isDay) {
            optionalWeather = storage.getWeathers().stream().max(Comparator.comparingInt(Weather::getMaxTemp));
        }
        else {
            optionalWeather = storage.getWeathers().stream().max(Comparator.comparingInt(Weather::getMinTemp));
        }
        return optionalWeather.orElseThrow(() -> new NotFoundException("Weather is not found"));
    }

    @Override
    public Weather getColdest(boolean isDay) {
        Optional<Weather> weatherOptional;
        if (isDay) {
            weatherOptional = storage.getWeathers().stream().min(Comparator.comparingInt(Weather::getMaxTemp));
        }
        else {
            weatherOptional = storage.getWeathers().stream().min(Comparator.comparingInt(Weather::getMinTemp));
        }
        return weatherOptional.orElseThrow(() -> new NotFoundException("Weather is not found"));
    }

    @Override
    public List<Weather> getSortedWeather(boolean ascending) {
        if (ascending) {

        }
        return storage.getWeathers().stream()
                .sorted(Comparator.comparingInt(Weather::getMaxTemp))
                .collect(Collectors.toList());
    }
}
