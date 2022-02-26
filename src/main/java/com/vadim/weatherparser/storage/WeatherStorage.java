package com.vadim.weatherparser.storage;

import com.vadim.weatherparser.Weather;
import com.vadim.weatherparser.WeatherParser;
import com.vadim.weatherparser.exception.NotFoundException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public interface WeatherStorage {

    void loadWeather();

    Weather getDayWeather(int day);

    List<Weather> getWeathers();
}
