package com.napier.sem.Features;

import com.napier.sem.Helpers.ReportHelper;
import com.napier.sem.Models.City;
import java.util.ArrayList;

/**
 * A class that contains methods relevant to querying a specified number of the top populated cities in specific areas
 */
public class TopNCities
{
    /**
     * An instance of the ReportHelper class, which can be accessed by each method to help run db queries
     */
    private final ReportHelper reportHelper;

    /**
     * Constructor for the TopNCities object
     * @param reportHelper sets an object of class ReportHelper to be used by TopNCities when running methods which will query the database
     */
    public TopNCities(ReportHelper reportHelper)
    {
        this.reportHelper = reportHelper;
    }

    /**
     * Gets a list of the top N populated cities in the world by their population number in descending order
     *
     * @param N The number of top populated cities to be returned
     * @return ArrayList that contains City objects in the world with their respective properties : Name, Country, District and Population
     */
    public ArrayList<City> ByWorld(int N)
    {
        return reportHelper.getCityReport("SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
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
    public ArrayList<City> ByContinent(int N, String continent)
    {
        return reportHelper.getCityReport("SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
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
    public ArrayList<City> ByRegion(int N, String region)
    {
        return reportHelper.getCityReport("SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
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
    public ArrayList<City> ByCountry(int N, String country)
    {
        return reportHelper.getCityReport("SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Name = '" + country + "'" +
                "ORDER BY city.Population DESC " +
                "LIMIT " + N
        );
    }

    /**
     * Gets a list of the top N populated cities in a district by their population number in descending order
     *
     * @param N The number of top populated cities to be returned
     * @param district The name of the district, constraining the list of cities returned to those within the specified district
     * @return ArrayList that contains City objects in a specific district with their respective properties : Name, Country, District and Population
     */
    public ArrayList<City> ByDistrict(int N, String district)
    {
        return reportHelper.getCityReport("SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE city.District = '" + district + "'" +
                "ORDER BY city.Population DESC " +
                "LIMIT " + N
        );
    }
}