package com.napier.sem.Features;

import com.napier.sem.Helpers.ReportHelper;
import com.napier.sem.Models.Country;
import com.napier.sem.Utils.DatabaseUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AllCountries
{
    /**
     * Gets a list of all the countries in the world by their population number in descending order
     * @return ArrayList that contains Country objects in the world with their respective properties : code, name, continent, region, population and capital name
     */
    public static ArrayList<Country> ByWorld()
    {
        return ReportHelper.getCountryReport("SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name as CapitalName " +
                "FROM country " +
                "JOIN city ON country.Capital = city.ID " +
                "ORDER BY country.Population DESC");
    }

    /**
     * Gets a list of all the countries in a specific continent by their population number in descending order
     * @param continent is the continent of the country that is being fetched from the db
     * @return ArrayList that contains Country objects in a specific continent with their respective properties: code, name, continent, region, population and capital name
     */
    public static ArrayList<Country> ByContinent(String continent)
    {
        return ReportHelper.getCountryReport("SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name as CapitalName " +
                "FROM country " +
                "JOIN city ON country.Capital = city.ID " +
                "WHERE country.Continent = '" + continent + "' " +
                "ORDER BY country.Population DESC");
    }

    /**
     * Gets a list of all the countries in a specific region by their population number in descending order
     * @param region is the region of the country that is being fetched from the db
     * @return ArrayList that contains Country objects in a specific region  with their respective properties: code, name, continent, region, population and capital name
     */
    public static ArrayList<Country> ByRegion(String region)
    {
        return ReportHelper.getCountryReport("SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name as CapitalName " +
                "FROM country " +
                "JOIN city ON country.Capital = city.ID " +
                "WHERE country.Region = '" + region + "' " +
                "ORDER BY country.Population DESC");
    }
}