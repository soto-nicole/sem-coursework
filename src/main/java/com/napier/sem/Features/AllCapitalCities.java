package com.napier.sem.Features;

import com.napier.sem.Helpers.ReportHelper;
import com.napier.sem.Models.City;
import java.util.ArrayList;

/**
 * A class that contains methods relevant to querying all the capital cities in specific areas
 */
public class AllCapitalCities
{
    /**
     * An instance of the ReportHelper class, which can be accessed by each method to help run db queries
     */
    private final ReportHelper reportHelper;

    /**
     * Constructor for the AllCapitalCities object
     * @param reportHelper sets an object of class ReportHelper to be used by AllCapitalCities when running methods which will query the database
     */
    public AllCapitalCities(ReportHelper reportHelper)
    {
        this.reportHelper = reportHelper;
    }

    /**
     * Gets a list of all the capital cities in the world by their population number in descending order
     *
     * @return ArrayList that contains City objects in the world with their respective properties : Name, Country and Population
     */
    public ArrayList<City> ByWorld()
    {
        return reportHelper.getCityReport("SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
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
    public ArrayList<City> ByContinent(String continent)
    {
        return reportHelper.getCityReport("SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
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
    public ArrayList<City> ByRegion(String region)
    {
        return reportHelper.getCityReport("SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.capital = city.id " +
                "AND country.Region = '" + region + "' " +
                "ORDER BY city.Population DESC ");
    }
}
