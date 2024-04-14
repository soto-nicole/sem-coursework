package com.napier.sem.Features;

import com.napier.sem.Helpers.ReportHelper;
import com.napier.sem.Models.Country;
import java.util.ArrayList;

/**
 * A class that contains methods relevant to querying all the countries in specific areas
 */
public class AllCountries
{
    /**
     * An instance of the ReportHelper class, which can be accessed by each method to help run db queries
     */
    private final ReportHelper reportHelper;

    /**
     * Constructor for the AllCountries object
     * @param reportHelper sets an object of class ReportHelper to be used by AllCountries when running methods which will query the database
     */
    public AllCountries(ReportHelper reportHelper)
    {
        this.reportHelper = reportHelper;
    }

    /**
     * Gets a list of all the countries in the world by their population number in descending order
     * @return ArrayList that contains Country objects in the world with their respective properties : code, name, continent, region, population and capital name
     */
    public ArrayList<Country> ByWorld()
    {
        return reportHelper.getCountryReport("SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name as CapitalName " +
                "FROM country " +
                "JOIN city ON country.Capital = city.ID " +
                "ORDER BY country.Population DESC");
    }

    /**
     * Gets a list of all the countries in a specific continent by their population number in descending order
     * @param continent is the name of the continent from which countries are being fetched from the db
     * @return ArrayList that contains Country objects in a specific continent with their respective properties: code, name, continent, region, population and capital name
     */
    public ArrayList<Country> ByContinent(String continent)
    {
        return reportHelper.getCountryReport("SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name as CapitalName " +
                "FROM country " +
                "JOIN city ON country.Capital = city.ID " +
                "WHERE country.Continent = '" + continent + "' " +
                "ORDER BY country.Population DESC");
    }

    /**
     * Gets a list of all the countries in a specific region by their population number in descending order
     * @param region is the name of the region from which countries are being fetched from the db
     * @return ArrayList that contains Country objects in a specific region with their respective properties: code, name, continent, region, population and capital name
     */
    public ArrayList<Country> ByRegion(String region)
    {
        return reportHelper.getCountryReport("SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name as CapitalName " +
                "FROM country " +
                "JOIN city ON country.Capital = city.ID " +
                "WHERE country.Region = '" + region + "' " +
                "ORDER BY country.Population DESC");
    }
}