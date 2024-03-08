package com.napier.sem.Features;

import com.napier.sem.Helpers.ReportHelper;
import com.napier.sem.Models.City;
import java.util.ArrayList;

public class TopNCities {
    /**
     * Gets a list of the top N populated cities in the world by their population number in descending order
     *
     * @param N The number of top populated cities to be returned
     * @return ArrayList that contains City objects in the world with their respective properties : Name, Country, District and Population
     */
    public static ArrayList<City> ByWorld(int N) {
        return ReportHelper.getCityReport("SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "ORDER BY city.Population DESC " +
                "LIMIT " + N
        );
    }

    /**
     * Gets a list of the top N populated cities in a continent by their population number in descending order
     *
     * @param N The number of top populated cities to be returned
     * @param continent The name of the continent, constraining the list of cities returned to those within the specified continent
     * @return ArrayList that contains City objects in a specific continent with their respective properties : Name, Country, District and Population
     */
    public static ArrayList<City> ByContinent(int N, String continent) {
        return ReportHelper.getCityReport("SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.continent = '" + continent + "'" +
                "ORDER BY city.Population DESC " +
                "LIMIT " + N
        );
    }

    /**
     * Gets a list of the top N populated cities in a region by their population number in descending order
     *
     * @param N The number of top populated cities to be returned
     * @param region The name of the region, constraining the list of cities returned to those within the specified region
     * @return ArrayList that contains City objects in a specific region with their respective properties : Name, Country, District and Population
     */
    public static ArrayList<City> ByRegion(int N, String region) {
        return ReportHelper.getCityReport("SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.region = '" + region + "'" +
                "ORDER BY city.Population DESC " +
                "LIMIT " + N
        );
    }

    /**
     * Gets a list of the top N populated cities in a country by their population number in descending order
     *
     * @param N The number of top populated cities to be returned
     * @param country The name of the country, constraining the list of cities returned to those within the specified country
     * @return ArrayList that contains City objects in a specific country with their respective properties : Name, Country, District and Population
     */
    public static ArrayList<City> ByCountry(int N, String country) {
        return ReportHelper.getCityReport("SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Name = '" + country + "'" +
                "ORDER BY city.Population DESC " +
                "LIMIT " + N
        );
    }
}