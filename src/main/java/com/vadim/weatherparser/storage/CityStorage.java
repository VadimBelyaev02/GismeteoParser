package com.vadim.weatherparser.storage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface CityStorage {

    void loadCities();
    List<String> getCities();
    String getCityUrl(String city);
}
