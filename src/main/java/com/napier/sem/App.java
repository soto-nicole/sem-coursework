package com.napier.sem;
import Models.Country;

import java.sql.*;
import java.util.ArrayList;

public class App
{
    public static void main(String[] args)
    {
        Utils.DatabaseUtil.connect();

        ArrayList<Country> countries = getCountryReport();
        displayCountries(countries);

        Utils.DatabaseUtil.disconnect();
    }

    /**
     * Fetches a list of countries sorted in descending order by population number
     * @return  A list of Country objects with their respective code, name, continent, region, population and capital city name. Otherwise, returns null if there is an error.
     *
     */
    public static ArrayList<Country> getCountryReport() {
        ArrayList<Country> countries = new ArrayList<>();
        try {
            Connection con = Utils.DatabaseUtil.getConnection();
            Statement stmt = con.createStatement();
            String strSelect =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name as CapitalName " +
                            "FROM country " +
                            "JOIN city ON country.Capital = city.ID " +
                            "ORDER BY country.Population DESC";
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next()) {
                Country country = new Country();
                country.code = rset.getString("Code");
                country.name = rset.getString("Name");
                country.continent = rset.getString("Continent");
                country.region = rset.getString("Region");
                country.population = rset.getInt("Population");
                country.capitalName = rset.getString("CapitalName");
                countries.add(country);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country report");
        }
        return countries;
    }

    /**
     * Displays the countries' details in a formatted table
     * @param countries The list of Country objects that will be displayed
     *
     */
    public static void displayCountries(ArrayList<Country> countries) {

        System.out.println(String.format("%-5s %-40s %-20s %-35s %-15s %-20s", "Code", "Name", "Continent", "Region", "Population", "Capital"));
        for (Country country : countries) {
            String countryString = String.format("%-5s %-40s %-20s %-35s %-15d %-20s",
                    country.code, country.name, country.continent, country.region, country.population, country.capitalName);
            System.out.println(countryString);
        }
    }
}