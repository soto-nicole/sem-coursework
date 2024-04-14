package com.napier.sem.Features;

import com.napier.sem.Helpers.ReportHelper;
import com.napier.sem.Models.Country;
import java.util.ArrayList;

/**
 * A class that contains methods relevant to querying a specified number of the top populated countries in specific areas
 */
public class TopNCountries
{
    /**
     * An instance of the ReportHelper class, which can be accessed by each method to help run db queries
     */
    private final ReportHelper reportHelper;

    /**
     * Constructor for the TopNCountries object
     * @param reportHelper sets an object of class ReportHelper to be used by TopNCountries when running methods which will query the database
     */
    public TopNCountries(ReportHelper reportHelper)
    {
        this.reportHelper = reportHelper;
    }

    /**
     * Gets a list of the top N populated countries in the world by their population number in descending order
     *
     * @param N The number of top populated countries to be returned
     * @return ArrayList that contains Country objects in the world with their respective properties : code, name, continent, region, population and capital name
     */
    public ArrayList<Country> ByWorld(int N)
    {
        return reportHelper.getCountryReport("SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name as CapitalName " +
                "FROM country " +
                "JOIN city ON country.Capital = city.ID " +
                "ORDER BY country.Population DESC " +
                "LIMIT " + N);
    }

    /**
     * Gets a list of the top N populated countries in a given continent by their population number in descending order
     *
     * @param N The number of top populated countries to be returned
     * @param continent The name of the continent, constraining the list of countries returned to those within the specified continent
     * @return ArrayList that contains Country objects in a specific continent with their respective properties : code, name, continent, region, population and capital name
     */
    public ArrayList<Country> ByContinent(int N, String continent)
    {
        return reportHelper.getCountryReport("SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name as CapitalName " +
                "FROM country " +
                "JOIN city ON country.Capital = city.ID " +
                "WHERE country.Continent = '" + continent + "' " +
                "ORDER BY country.Population DESC " +
                "LIMIT " + N);
    }

    /**
     * Gets a list of the top N populated countries in a given region by their population number in descending order
     *
     * @param N      The number of top populated countries to be returned
     * @param region The name of the region, constraining the list of countries returned to those within the specified region
     * @return ArrayList that contains Country objects in a specific region with their respective properties : code, name, continent, region, population and capital name
     */
    public ArrayList<Country> ByRegion(int N, String region)
    {
        return reportHelper.getCountryReport("SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name as CapitalName " +
                "FROM country " +
                "JOIN city ON country.Capital = city.ID " +
                "WHERE country.Region = '" + region + "' " +
                "ORDER BY country.Population DESC " +
                "LIMIT " + N);
    }
}