package com.vadim.weatherparser.service.impl;

import com.vadim.weatherparser.service.CityService;
import com.vadim.weatherparser.storage.impl.CityStorageImpl;

import java.util.List;

public class CityServiceImpl implements CityService {

    private final CityStorageImpl storage = new CityStorageImpl();

    public List<String> getCitiesNames() {
        return storage.getCities();
    }

    @Override
    public String getCityUrl(String city) {
        return storage.getCityUrl(city);
    }
}
