package com.vadim.weatherparser.parser;

import com.vadim.weatherparser.model.Weather;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Element;
import org.springframework.cglib.core.Local;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WeatherParser {

    private final static Logger logger = LogManager.getLogger(WeatherParser.class);

    public List<Weather> toListWeather(Element element) {
        logger.info("Parsing the weather for the months");
        List<Weather> weathers = new ArrayList<>();
        List<Element> rowItems = element.select("a[class=row-item]");
        int current = LocalDate.now().getDayOfWeek().getValue();
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        LocalDate date;
        int dayOfMonth, minTemp, maxTemp, tmp = 1;

        for (int i = 0; i < rowItems.size() - current; i += 7) {
            for (int j = 0; j < 7; j++) {
                if (current > 7) {
                    current = 1;
                }
                String tag = "div[class=date item-day-" + current + "]";
                if (rowItems.get(i + j).select(tag).first() == null) {
                    tag = "div[class=date item-day-" + current + " bold]";
                }
                current++;
                dayOfMonth = Integer.parseInt(rowItems.get(i + j).select(tag).text().split(" ")[0]);
                date = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), dayOfMonth);
                maxTemp = parseInt(rowItems.get(i + j).select("div[class=maxt]").text()
                        .split(" ")[0]);
                minTemp = parseInt(rowItems.get(i + j).select("div[class=mint]").text()
                        .split(" ")[tmp]);
                tmp = 0;
                Weather weather = new Weather(maxTemp, minTemp, dayOfWeek, date);
                dayOfWeek = dayOfWeek.plus(1);
                weathers.add(weather);
            }
        }
        return weathers;
    }

    public int parseInt(String str) {
        logger.info("Parsing integer");
        int number;
        if (str.isEmpty()) {
            logger.error("Can not parse integer");
            throw new RuntimeException();
        }
        if (str.charAt(0) == 8722) {
            number = Integer.parseInt(str.substring(1));
            number *= -1;
        }
        else {
            number = Integer.parseInt(str);
        }
        return number;
    }
}
