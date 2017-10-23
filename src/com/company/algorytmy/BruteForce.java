package com.company.algorytmy;

import com.company.AppConfig;
import com.company.Utils;
import com.company.entity.City;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BruteForce {

    private ArrayList<City> cityList = new ArrayList<>();

    public BruteForce() {
        initCityList();
        List<Integer> droga = new ArrayList<>();
        //droga.add(0);   jezeli zaczynamy od pierwszego miasta na liscie to odkomentowujemy -
        // jezeli chcemy przetestowac wszystkie permutacje w ktorych pierwszy wezel moze byc kazdym z miast zostawiamy zakomentowane
        List<List<Integer>> lists = permutacje(droga, cityList.size());
        Double minodleglosc = null;
        List<Integer> minList = null;
        for (List<Integer> list : lists) {
            double odleglosc = 0;
            for (int i = 0; i < list.size(); i++) {
                if (i != list.size() - 1) {
                    odleglosc += cityList.get(list.get(i)).distanceToCity(cityList.get(list.get(i + 1)));
                } else {
                    odleglosc += cityList.get(list.get(i)).distanceToCity(cityList.get(list.get(0)));
                }
            }
            if (minodleglosc == null || minodleglosc > odleglosc) {
                minodleglosc = odleglosc;
                minList = list;
            }
            System.out.print("Dlugosc dla drogi:");
            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i));
            }
            System.out.print(list.get(0));
            System.out.println(" to " + odleglosc);
        }
        System.out.println("Najlepszy wariant to " + minodleglosc);
        for (int i = 0; i < minList.size(); i++) {
            System.out.print(minList.get(i));
        }
        System.out.print(minList.get(0));
    }

    private List<List<Integer>> permutacje(List<Integer> way, int size) {
        List<List<Integer>> allWays = new ArrayList<>();
        List<Integer> copyOfWay = new ArrayList<>();
        copyOfWay.addAll(way);
        Integer numberOfMissingNodesInWay = size - way.size();
        if (numberOfMissingNodesInWay == 1) {
            for (int i = 0; i < cityList.size(); i++) {
                if (!way.contains(i)) {
                    way.add(i);
                    allWays.add(way);
                    return allWays;
                }
            }
        } else {
            for (int i = 0; i < cityList.size(); i++) {
                if (!way.contains(i)) {
                    List<Integer> tempWay = new ArrayList<>();
                    tempWay.addAll(way);
                    tempWay.add(i);
                    allWays.addAll(permutacje(tempWay, size));
                }
            }
        }
        return allWays;
    }

    private void initCityList() {
        Random generator = new Random();
        for (int i = 0; i < AppConfig.LIST_CITY_SIZE; i++) {
            cityList.add(new City(Utils.generateRandom(5), generator.nextInt(100) + 1, generator.nextInt(100) + 1));
            Utils.ShowLog("CityName = " + cityList.get(i).getCityName() + " X = " + cityList.get(i).getX() + " Y = " + cityList.get(i).getY());
        }
    }
}
