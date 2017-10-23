package com.company.algorytmy;

import com.company.AppConfig;
import com.company.Utils;
import com.company.entity.City;

import java.util.ArrayList;
import java.util.Random;

public class Zachłanny {

    private ArrayList<City> cityList = new ArrayList<>();

    public Zachłanny() {
        iniCityList();
        City startCity = cityList.get(0);
        cityList.remove(cityList.get(0));
        City newStatCity = findTheSmalledDistance(startCity);
        if (cityList.size() != 0) {
            do {
                newStatCity = findTheSmalledDistance(newStatCity);
            } while (cityList.size() != 0);
        }
    }


    City findTheSmalledDistance(City startCity) {
        double tmpWay = 1000000000;
        City tempCity = new City("", 0, 0);
        for (City city : cityList) {
            System.out.println(startCity.distanceToCity(city));
            if (startCity.distanceToCity(city) < tmpWay) {
                tmpWay = startCity.distanceToCity(city);
                tempCity = city;
                tempCity.setDistance(startCity.distanceToCity(city));
            }
        }
        Utils.ShowLog("Najmniejsza odległosc to :" + tempCity.getDistance() + " CityName :" + tempCity.getCityName() + " X :" + tempCity.getX() + " X :" + tempCity.getY());
        Utils.ShowLog("");
        cityList.remove(tempCity);
        return tempCity;
    }


    private void iniCityList() {
        Random generator = new Random();
        for (int i = 0; i < AppConfig.LIST_CITY_SIZE; i++) {
            cityList.add(new City(Utils.generateRandom(5), generator.nextInt(100) + 1, generator.nextInt(100) + 1));
            Utils.ShowLog("CityName = " + cityList.get(i).getCityName() + " X = " + cityList.get(i).getX() + " Y = " + cityList.get(i).getY());
            Utils.ShowLog("");
        }

    }

}
