package com.vadim.weatherparser.service;

import com.vadim.weatherparser.model.Weather;

import java.util.Comparator;
import java.util.List;

public interface WeatherService {

    Weather getWeather(String city);

    Weather getDayWeather(int day);

    List<Weather> getMonthWeather();

    List<Weather> getWeathers();

    Weather getHottest(boolean isDay);

    Weather getColdest(boolean isDay);

    List<Weather> getSortedWeather(Comparator<Weather> comparator);
}
