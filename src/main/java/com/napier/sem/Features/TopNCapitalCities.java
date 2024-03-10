package com.napier.sem.Features;

import com.napier.sem.Helpers.ReportHelper;
import com.napier.sem.Models.City;
import java.util.ArrayList;

public class TopNCapitalCities {
    /**
     * Gets a list of the top N capital cities in the world by their population number in descending order
     *
     * @return ArrayList that contains City objects in the world with their respective properties : Name, Country and Population
     */
    public static ArrayList<City> ByWorld(int N) {
        return ReportHelper.getCityReport("SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.capital = city.id " +
                "ORDER BY city.Population DESC " +
                "LIMIT " + N);
    }
}