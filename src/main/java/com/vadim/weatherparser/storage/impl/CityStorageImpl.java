package com.vadim.weatherparser.storage.impl;

import com.vadim.weatherparser.storage.CityStorage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CityStorageImpl implements CityStorage {

    private Map<String, String> cities;

    public CityStorageImpl() {
        loadCities();
    }

    @Override
    public void loadCities() {
        Document document = null;
        try {
            String URL = "https://www.gismeteo.by";
            document = Jsoup.connect(URL).get();
            Element tagWithAllCities = document.select("div[class=widget widget-cities]").first();
            cities = tagWithAllCities.select("div[class=list]")
                    .select("a[class=link]").stream()
                    .collect(Collectors.toMap(Element::text, a -> a.attr("href")));
        } catch (IOException e) {
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
