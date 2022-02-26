package com.vadim.weatherparser.storage;

import com.vadim.weatherparser.Weather;

import java.util.List;

public interface WeatherStorage {

    void loadWeather();

    Weather getDayWeather(int day);

    List<Weather> getWeathers();
}
