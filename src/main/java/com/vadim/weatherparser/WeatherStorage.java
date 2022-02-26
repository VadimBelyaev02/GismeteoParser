package com.vadim.weatherparser;

import com.vadim.weatherparser.exception.NotFoundException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            Element element = document.select("div[class=widget widget-month]").first();//.select("a[class=row-item]");

            this.weathers = parser.toListWeather(element);
            //            for (Element element : elements) {
//                weathers.add(parser.toWeather(element));
//            }


        //    weathers = parser.toWeather2(element);

        //    elements.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Weather getDayWeather(int day) {
        return weathers.stream().filter(a -> a.getDate().equals(day)).findFirst().orElseThrow(() ->
                new NotFoundException("Not found")
        );
    }

    public List<Weather> getWeathers() {
        return weathers;
    }
}
