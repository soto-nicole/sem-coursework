package com.napier.sem.Features;

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
        return getCityReport("SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "ORDER BY city.Population DESC");
    }

    /**
     * Gets a list of all the cities in a specific region by their population number in descending order
     * @param region is the city of the country that is being fetched from the db
     * @return ArrayList that contains City objects in the world with their respective properties : Name, Country, District and Population
     */
    public static ArrayList<City> ByRegion(String region)
    {
        return getCityReport("SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Region = '" + region + "' " +
                "ORDER BY city.Population DESC");
    }

    /**
     * Gets a list of all the cities in a specific continent by their population number in descending order
     * @param continent is the city of the country that is being fetched from the db
     * @return ArrayList that contains Country objects in the world with their respective properties : Name, Country, District and Population
     */
    public static ArrayList<City> ByContinent(String continent)
    {
        return getCityReport("SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Continent = '" + continent + "' " +
                "ORDER BY city.Population DESC");
    }


    /**
     * Helper method to get the country information by using the SQL queries
     * @param strSelect SQL query that will return the city information needed for the reports
     * @return ArrayList from City objects that contains the required data being fetched
     */
    private static ArrayList<City> getCityReport(String strSelect)
    {
        ArrayList<City> cities = new ArrayList<>();
        try
        {
            Connection con = DatabaseUtil.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                City city = new City();
                city.name = rset.getString("Name");
                city.countryCode = rset.getString("CountryName");
                city.district = rset.getString("District");
                city.population = rset.getInt("Population");
                cities.add(city);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city report");
            return null;
        }
        return cities;
    }
}