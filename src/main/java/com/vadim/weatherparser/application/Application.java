package com.vadim.weatherparser.application;

import com.vadim.weatherparser.model.Weather;
import com.vadim.weatherparser.service.impl.CityServiceImpl;
import com.vadim.weatherparser.service.impl.WeatherServiceImpl;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static com.vadim.weatherparser.InputChecker.getInteger;

public class Application {

    private final static CityServiceImpl cityService = new CityServiceImpl();
    private static List<Weather> weathers;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printCities();
        System.out.print("Enter a city: ");
        String city = scanner.nextLine();

        String cityUrl = "https://www.gismeteo.by" + cityService.getCityUrl(city) + "/month/";
        WeatherServiceImpl weatherService = new WeatherServiceImpl(cityUrl);

        while (true) {
            printChoice();
            int choice = getInteger(1, 8);
            switch (choice) {
                case 1:
                    System.out.println("Enter a day: ");
                    int day = getInteger(1, 31);
                    Weather weather = weatherService.getDayWeather(day);
                    printDayWeather(weather);
                    break;
                case 2:
                    weathers = weatherService.getMonthWeather();
                    printWeather();
                    break;
                case 3:
                    weather = weatherService.getHottest(true);
                    printDayWeather(weather);
                    break;
                case 4:
                    weather = weatherService.getHottest(false);
                    printDayWeather(weather);
                    break;
                case 5:
                    weather = weatherService.getColdest(true);
                    printDayWeather(weather);
                    break;
                case 6:
                    weather = weatherService.getColdest(false);
                    printDayWeather(weather);
                    break;
                case 7:
                    Comparator<Weather> comparator = Comparator.comparingInt(Weather::getMaxTemp);
                    weathers = weatherService.getSortedWeather(comparator);
                    printWeather();
                    break;
                case 8:
                    comparator = Comparator.comparingInt(Weather::getMinTemp);
                    weathers = weatherService.getSortedWeather(comparator);
                    printWeather();
                    break;
                case 9:
                    printWeather();
                    break;
                default:
                    return;
            }
        }
    }

    public static void printChoice() {
        System.out.println("1 - Get the weather in the day");
        System.out.println("2 - Get the weather for the month");
        System.out.println("3 - Get the hottest day");
        System.out.println("4 - Get the hottest night");
        System.out.println("5 - Get the coldest day");
        System.out.println("6 - Get the coldest night");
        System.out.println("7 - Get sorted day weather");
        System.out.println("8 - Get sorted night weather");
        System.out.println("9 - Output table");
    }

    public static void printCities() {
        List<String> citiesNames = cityService.getCitiesNames();
        if (Objects.isNull(citiesNames) || citiesNames.size() == 0) {
            System.out.println("There is no any city!");
            return;
        }
        for (int i = 0; i < citiesNames.size(); i++) {
            if (i % 4 == 0) {
                System.out.println();
            }
            System.out.printf("%-15s", citiesNames.get(i));
        }
        System.out.println();
    }

    public static void printWeather() {
        if (Objects.isNull(weathers) || weathers.size() == 0) {
            System.out.println("Weather is empty");
            return;
        }
        String hLine = "\n--------------------------------------------------------\n";
        String vLine = " | ";
        System.out.println("\n***Here is the weather for the next month***");
        System.out.printf("%-15s%-15s%-20s%-20s\n", vLine + "Date", vLine + "Day of week", vLine + "Day Temperature", vLine + "Night Temperature" + vLine + hLine);
        for (Weather weather : weathers) {
            System.out.printf("%-15s%-15s", vLine + weather.getDate(), vLine + weather.getDayOfWeek());
            System.out.printf("%-20s%-20s", vLine + weather.getMinTemp(), vLine + weather.getMaxTemp() + vLine + hLine);
        }
    }

    public static void printDayWeather(Weather weather) {
        if (Objects.isNull(weather)) {
            return;
        }
        String hLine = "\n-------------------------------------------------------------------\n";
        String vLine = " | ";
        System.out.println(hLine);
        System.out.printf("%-10s%-15s%-20s%-20s\n", vLine + "Date", vLine + "Day of week", vLine + "Day Temperature", vLine + "Night Temperature" + vLine);
        System.out.println(hLine);
        System.out.printf("%-10s%-15s", vLine + weather.getDate(), vLine + weather.getDayOfWeek().toString());
        System.out.printf("%-20s%-20s%3s", vLine + weather.getMinTemp(), vLine + weather.getMaxTemp(), vLine);
        System.out.println(hLine);
    }


}
