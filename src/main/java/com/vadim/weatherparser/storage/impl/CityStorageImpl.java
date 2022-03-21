package com.vadim.weatherparser.storage.impl;

import com.vadim.weatherparser.storage.CityStorage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CityStorageImpl implements CityStorage {

    private final static Logger logger = LogManager.getLogger(CityStorageImpl.class);

    private Map<String, String> cities;

    public CityStorageImpl() {
        loadCities();
    }

    @Override
    public void loadCities() {
        try {
            logger.info("Loading cities...");
            String URL = "https://www.gismeteo.by";
            Document document = Jsoup.connect(URL).get();
            Element tagWithAllCities = document.select("div[class=widget widget-cities]").first();
            cities = tagWithAllCities.select("div[class=list]")
                    .select("a[class=link]").stream()
                    .collect(Collectors.toMap(Element::text, a -> a.attr("href")));
        } catch (IOException e) {
            logger.error("There was an exception during loading the cities");
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getCities() {
        return new ArrayList<>(cities.keySet());
    }

    @Override
    public String getCityUrl(String city) {
        return cities.get(city);
    }
}
