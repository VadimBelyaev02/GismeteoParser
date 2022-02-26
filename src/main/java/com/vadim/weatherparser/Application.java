package com.vadim.weatherparser;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Application {

    private final static CityServiceImpl cityService = new CityServiceImpl();

    public static void main(String[] args) throws Throwable {
        Scanner scanner = new Scanner(System.in);
        Weather weather;
        List<Weather> weathers;
        printCities();
        System.out.print("Enter a city: ");
        String city = scanner.nextLine();
        String cityUrl = "https://www.gismeteo.by" + cityService.getCityUrl(city) + "/month/";
        WeatherServiceImpl weatherService = new WeatherServiceImpl(cityUrl);
        printChoice();
        int choice = scanner.nextInt();

        weatherService.getWeathers().forEach(System.out::println);

        Thread.sleep(1000000000);

        switch (choice) {
            case 1:
                System.out.println("Enter a day: ");
                int day = scanner.nextInt();
                weather = weatherService.getDayWeather(day);
                System.out.println(weather);
                break;
            case 2:
                weathers = weatherService.getMonthWeather();
                weathers.forEach(System.out::println);
                break;
            case 3:
                weather = weatherService.getHottest(true);
                System.out.println(weather);
                break;
            case 4:
                weather = weatherService.getHottest(false);
                System.out.println(weather);
                break;
            case 5:
                weather = weatherService.getColdest(true);
                System.out.println(weather);
                break;
            case 6:
                weather = weatherService.getColdest(false);
                System.out.println(weather);
                break;
            case 7:
                weathers = weatherService.getSortedWeather(true);
                weathers.forEach(System.out::println);
                break;
            case 8:
                weathers = weatherService.getSortedWeather(false);
                weathers.forEach(System.out::println);
                break;
        }
    }

    public static void printChoice() {
        System.out.println("1 - Get the weather in the day");
        System.out.println("2 - Get the weather for the month");
        System.out.println("3 - Get the hottest day");
        System.out.println("4 - Get the hottest night");
        System.out.println("5 - Get the coldest day");
        System.out.println("6 - Get the coldest night");
        System.out.println("7 - Sort by increasing degrees");
        System.out.println("8 - Sort by decreasing degrees");
    }

    public static void printCities() {
        List<String> citiesNames = cityService.getCitiesNames();
        for (int i = 0; i < citiesNames.size(); i++) {
            if (i % 4 == 0) {
                System.out.println();
            }
            System.out.printf("%-15s", citiesNames.get(i));
        }
        System.out.println();
    }
}
