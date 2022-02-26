package com.vadim.weatherparser;

import com.vadim.weatherparser.storage.impl.CityStorageImpl;

import java.util.List;

public class CityServiceImpl implements CityService {

    private final CityStorageImpl storage = new CityStorageImpl();

    public List<String> getCitiesNames() {
        return storage.getCities();
    }

    public String getCityUrl(String city) {
        return storage.getCityUrl(city);
    }
}
