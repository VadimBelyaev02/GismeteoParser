package com.vadim.weatherparser.storage.impl;

import com.vadim.weatherparser.model.Weather;
import com.vadim.weatherparser.parser.WeatherParser;
import com.vadim.weatherparser.exception.NotFoundException;
import com.vadim.weatherparser.storage.WeatherStorage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class WeatherStorageImpl implements WeatherStorage {

    private List<Weather> weathers;
    private final String URL;

    public WeatherStorageImpl(String URL) {
        this.URL = URL;
        loadWeather();
    }

    @Override
    public void loadWeather() {
        WeatherParser parser = new WeatherParser();
        try {
            Document document = Jsoup.connect(URL).get();
            Element element = Objects.requireNonNull(document.select("div[class=widget widget-month]").first());
            this.weathers = parser.toListWeather(element);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Weather getDayWeather(int day) {
        return weathers.stream().filter(weather -> weather.getDate().getDayOfMonth() == day).findFirst().orElseThrow(() ->
                new NotFoundException("The weather in this day is not found")
        );
    }

    @Override
    public List<Weather> getWeathers() {
        return weathers;
    }
}
