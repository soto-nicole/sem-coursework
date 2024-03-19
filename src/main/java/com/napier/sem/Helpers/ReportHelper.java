package com.napier.sem.Helpers;

import com.napier.sem.Models.City;
import com.napier.sem.Models.Country;
import com.napier.sem.Utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ReportHelper
{
    /**
     * Helper method to get the country information by using the SQL queries
     * @param strSelect SQL query that will return the country information needed for the reports
     * @return ArrayList from country objects that contains the required data being fetched
     */
    public static ArrayList<Country> getCountryReport(String strSelect)
    {
        ArrayList<Country> countries = new ArrayList<>();
        try
        {
            Connection con = DatabaseUtil.getConnection();
            Statement stmt = con.createStatement();
            ResultSet set = stmt.executeQuery(strSelect);

            while (set.next())
            {
                Country country = new Country();
                country.code = set.getString("Code");
                country.name = set.getString("Name");
                country.continent = set.getString("Continent");
                country.region = set.getString("Region");
                country.population = set.getInt("Population");
                country.capitalName = set.getString("CapitalName");
                countries.add(country);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country report");
            return null;
        }
        return countries;
    }

    /**
     * Helper method to get the country information by using the SQL queries
     * @param strSelect SQL query that will return the city information needed for the reports
     * @return ArrayList from City objects that contains the required data being fetched
     */
    public static ArrayList<City> getCityReport(String strSelect)
    {
        ArrayList<City> cities = new ArrayList<>();
        try
        {
            Connection con = DatabaseUtil.getConnection();
            Statement stmt = con.createStatement();
            ResultSet set = stmt.executeQuery(strSelect);

            while (set.next())
            {
                City city = new City();
                city.name = set.getString("Name");
                city.countryCode = set.getString("CountryName");
                city.district = set.getString("District");
                city.population = set.getInt("Population");
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
