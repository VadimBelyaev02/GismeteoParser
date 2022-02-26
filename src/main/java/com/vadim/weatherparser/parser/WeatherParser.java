package com.vadim.weatherparser.parser;

import com.vadim.weatherparser.Weather;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WeatherParser {

    public Weather toWeather(Element element) {
        //Integer dayOfWeek = element.select("div[class=date item-day-*");
        int current = LocalDate.now().getDayOfWeek().getValue();


        Element element1 = element.select("div[class=date item-day-" + current + "]").first();
        System.out.println(element1);
        return null;
    }

    public List<Weather> toListWeather(Element element) {
        List<Weather> weathers = new ArrayList<>();
        List<Element> rowItems = element.select("a[class=row-item]");
        int current = LocalDate.now().getDayOfWeek().getValue();
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();

        int date;
        int minTemp; //= Integer.parseInt(rowItems.get(0).select("div[class=mint]").text()
//                .split(" ")[1]);
        int maxTemp;
        int temp = 1;

        for (int i = 0; i < rowItems.size(); i += 7) {
            for (int j = 0; j < 7; j++) {
                if (current > 7) {
                    current = 1;
                }
                String tag = "div[class=date item-day-" + current + "]";
                if (rowItems.get(i + j).select(tag).first() == null) {
                    tag = "div[class=date item-day-" + current + " bold]";
                }
                current++;
                System.out.println(tag);
                System.out.println(rowItems.get(i + j).select(tag).first());
                date = Integer.parseInt(rowItems.get(i + j).select(tag).text().split(" ")[0]);
                System.out.println(date + "  ");
                maxTemp = parseInt(rowItems.get(i + j).select("div[class=maxt]").text()
                        .split(" ")[0]);
                minTemp = parseInt(rowItems.get(i + j).select("div[class=mint]").text()
                        .split(" ")[temp]);
                temp = 0;
                Weather weather = new Weather();
                weather.setDate(date);
                weather.setMaxTemp(maxTemp);
                weather.setMinTemp(minTemp);
                weather.setDayOfWeek(dayOfWeek);
                dayOfWeek = dayOfWeek.plus(1);
                weathers.add(weather);
            }
        } // Минск
        // WILL BE REFACTORED !!!
        return weathers;
    }

    public int parseInt(String str) {
        int number;
        if (str.isEmpty()) {
            throw new RuntimeException();
        }
        if (str.charAt(0) == 8722) {
            number = Integer.parseInt(str.substring(1, str.length()));
            number *= -1;
        }
        else {
            number = Integer.parseInt(str);
        }
        return number;
    }
}
