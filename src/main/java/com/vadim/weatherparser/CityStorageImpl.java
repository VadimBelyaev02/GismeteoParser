package com.vadim.weatherparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CityStorageImpl implements CityStorage {

    private Map<String, String> cities;
    private final String URL = "https://www.gismeteo.by";

    public CityStorageImpl() {
        loadCities();
    }

    public void loadCities() {
        Document document = null;
        try {
            document = Jsoup.connect(URL).get();
            Element tagWithAllCities = document.select("div[class=widget widget-cities]").first();
            cities = tagWithAllCities.select("div[class=list]")
                    .select("a[class=link]").stream()
                    .collect(Collectors.toMap(Element::text, a -> a.attr("href")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<String> getCities() {
        return new ArrayList<>(cities.keySet());
    }

    public String getCityUrl(String city) {
        return cities.get(city);
    }
}
