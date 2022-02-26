package com.vadim.weatherparser.service;

import com.vadim.weatherparser.Weather;

import java.util.List;

public interface WeatherService {

    Weather getWeather(String city);

    Weather getDayWeather(int day);

    List<Weather> getMonthWeather();

    List<Weather> getWeathers();

    Weather getHottest(boolean isDay);

    Weather getColdest(boolean isDay);

    List<Weather> getSortedWeather(boolean ascending);
}
