package com.napier.sem.Features;

import com.napier.sem.Models.Country;
import com.napier.sem.Utils.DatabaseUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TopNCountries
{
    /**
     * Gets a list of the top N populated countries in the world by their population number in descending order
     * @param N The number of top populated countries to be returned
     * @return ArrayList that contains Country objects in the world with their respective properties : code, name, continent, region, population and capital name
     */
    public static ArrayList<Country> ByWorld(int N)
    {
        return getCountryReport("SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name as CapitalName " +
                "FROM country " +
                "JOIN city ON country.Capital = city.ID " +
                "ORDER BY country.Population DESC " +
                "LIMIT " + N);
    }

    /**
     * Helper method to get the country information by using the SQL queries
     * @param strSelect SQL query that will return the country information needed for the reports
     * @return ArrayList from country objects that contains the required data being fetched
     */
    private static ArrayList<Country> getCountryReport(String strSelect)
    {
        ArrayList<Country> countries = new ArrayList<>();
        try
        {
            Connection con = DatabaseUtil.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                Country country = new Country();
                country.code = rset.getString("Code");
                country.name = rset.getString("Name");
                country.continent = rset.getString("Continent");
                country.region = rset.getString("Region");
                country.population = rset.getInt("Population");
                country.capitalName = rset.getString("CapitalName");
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
}