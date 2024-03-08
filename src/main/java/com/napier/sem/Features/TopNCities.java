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
}