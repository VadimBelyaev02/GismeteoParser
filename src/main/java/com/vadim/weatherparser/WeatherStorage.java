package com.vadim.weatherparser;

import com.vadim.weatherparser.exception.NotFoundException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WeatherStorage {

    private List<Weather> weathers;
    private final String URL;

    public WeatherStorage(String URL) {
        this.URL = URL;
        loadWeather();
    }

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

    public Weather getDayWeather(int day) {
        return weathers.stream().filter(a -> a.getDate().equals(day)).findFirst().orElseThrow(() ->
                new NotFoundException("The weather in this day is not found")
        );
    }

    public List<Weather> getWeathers() {
        return weathers;
    }
}
