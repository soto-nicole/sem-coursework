package com.napier.sem.Features;

import com.napier.sem.Helpers.ReportHelper;
import com.napier.sem.Models.City;
import java.util.ArrayList;

/**
 * A class that contains methods relevant to querying all the cities in specific areas
 */
public class AllCities
{
    /**
     * An instance of the ReportHelper class, which can be accessed by each method to help run db queries
     */
    private final ReportHelper reportHelper;

    /**
     * Constructor for the AllCities object
     * @param reportHelper sets an object of class ReportHelper to be used by AllCities when running methods which will query the database
     */
    public AllCities(ReportHelper reportHelper)
    {
        this.reportHelper = reportHelper;
    }

    /**
     * Gets a list of all the cities in the world by their population number in descending order
     * @return ArrayList that contains City objects in the world with their respective properties : Name, Country, District and Population
     */
    public ArrayList<City> ByWorld()
    {
        return reportHelper.getCityReport("SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "ORDER BY city.Population DESC");
    }

    /**
     * Gets a list of all the cities in a specific continent by their population number in descending order
     * @param continent is the name of the continent from which cities are being fetched from the db
     * @return ArrayList that contains City objects in the specific Continent with their respective properties : Name, Country, District and Population
     */
    public ArrayList<City> ByContinent(String continent)
    {
        return reportHelper.getCityReport("SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Continent = '" + continent + "' " +
                "ORDER BY city.Population DESC");
    }

    /**
     * Gets a list of all the cities in a specific region by their population number in descending order
     * @param region is the name of the region from which cities are being fetched from the db
     * @return ArrayList that contains City objects in the specific region with their respective properties : Name, Country, District and Population
     */
    public ArrayList<City> ByRegion(String region)
    {
        return reportHelper.getCityReport("SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Region = '" + region + "' " +
                "ORDER BY city.Population DESC");
    }

    /**
     * Gets a list of all the cities from a specific country by their population number in descending order
     * @param countryName is the name of the country from which to get the list of cities
     * @return An ArrayList of City objects representing the cities in the specific country with their respective properties : Name, Country, District and Population
     */
    public ArrayList<City> ByCountry(String countryName)
    {
        return reportHelper.getCityReport("SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
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
    public ArrayList<City> ByDistrict(String districtName)
    {
        return reportHelper.getCityReport("SELECT city.Name, city.District, city.Population, country.Name as CountryName " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE city.District = '" + districtName + "' " +
                "ORDER BY city.Population DESC");
    }
}