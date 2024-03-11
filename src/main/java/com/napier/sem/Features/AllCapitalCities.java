package com.napier.sem.Features;

import com.napier.sem.Helpers.ReportHelper;
import com.napier.sem.Models.City;
import java.util.ArrayList;

public class AllCapitalCities {
    /**
     * Gets a list of all the capital cities in the world by their population number in descending order
     *
     * @return ArrayList that contains City objects in the world with their respective properties : Name, Country and Population
     */
    public static ArrayList<City> ByWorld() {
        return ReportHelper.getCityReport("SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.capital = city.id " +
                "ORDER BY city.Population DESC");
    }

    /**
     * Gets a list of all the capital cities in a continent by their population number in descending order
     * @param continent The name of the continent, constraining the list of capital cities returned to those within the specified continent
     * @return ArrayList that contains City objects in a continent with their respective properties : Name, Country and Population
     */
    public static ArrayList<City> ByContinent(String continent) {
        return ReportHelper.getCityReport("SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.capital = city.id " +
                "AND country.Continent = '" + continent + "' " +
                "ORDER BY city.Population DESC ");
    }

    /**
     * Gets a list of all the capital cities in a region by their population number in descending order
     * @param region The name of the region, constraining the list of capital cities returned to those within the specified region
     * @return ArrayList that contains City objects in a region with their respective properties : Name, Country and Population
     */
    public static ArrayList<City> ByRegion(String region) {
        return ReportHelper.getCityReport("SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.capital = city.id " +
                "AND country.Region = '" + region + "' " +
                "ORDER BY city.Population DESC ");
    }
}
