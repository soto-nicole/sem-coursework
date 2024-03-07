package com.napier.sem.Features;

import com.napier.sem.Helpers.ReportHelper;
import com.napier.sem.Models.City;
import com.napier.sem.Utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AllCities
{
    /**
     * Gets a list of all the cities in the world by their population number in descending order
     * @return ArrayList that contains Country objects in the world with their respective properties : Name, Country, District and Population
     */
    public static ArrayList<City> ByWorld()
    {
        return ReportHelper.getCityReport("SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "ORDER BY city.Population DESC");
    }

    /**
     * Gets a list of all the cities in a specific continent by their population number in descending order
     * @param continent is the city of the country that is being fetched from the db
     * @return ArrayList that contains Country objects in the specific Continent with their respective properties : Name, Country, District and Population
     */
    public static ArrayList<City> ByContinent(String continent)
    {
        return ReportHelper.getCityReport("SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Continent = '" + continent + "' " +
                "ORDER BY city.Population DESC");
    }

    /**
     * Gets a list of all the cities in a specific region by their population number in descending order
     * @param region is the city of the country that is being fetched from the db
     * @return ArrayList that contains City objects in the specific region with their respective properties : Name, Country, District and Population
     */
    public static ArrayList<City> ByRegion(String region)
    {
        return ReportHelper.getCityReport("SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Region = '" + region + "' " +
                "ORDER BY city.Population DESC");
    }

    /**
     * Gets a list of all the cities for a specific country by their population number in descending order
     * @param countryName is the name of the country for which to get the list of cities
     * @return An ArrayList of City objects representing the cities in the specific country with their respective properties : Name, Country, District and Population
     */
    public static ArrayList<City> ByCountry(String countryName)
    {
        return ReportHelper.getCityReport("SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Name = '" + countryName + "' " +
                "ORDER BY city.Population DESC");
    }

    /**
     * Gets a list of all the cities for a specific district by their population number in descending order
     * @param districtName is the name of the district for which to get the list of cities
     * @return An ArrayList of City objects representing the cities in the specific district with their respective properties : Name, Country, District and Population
     */
    public static ArrayList<City> ByDistrict(String districtName)
    {
        return ReportHelper.getCityReport("SELECT city.Name, city.District, city.Population, country.Name as CountryName " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE city.District = '" + districtName + "' " +
                "ORDER BY city.Population DESC");

        //TODO: Investigate quirk where the district  Cordoba shows up two different countries - might be a db issue. unless we let the user input
        // for which country they need
    }
}