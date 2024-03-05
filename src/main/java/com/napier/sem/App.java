package com.napier.sem;

import Models.City;

import java.sql.*;

public class App
{
    public static void main(String[] args)
    {
        Utils.DatabaseUtil.connect();

        City city = getCity(1);
        displayCity(city);

        Utils.DatabaseUtil.disconnect();
    }


    public static City getCity(int id)
    {
        try {
            Connection con = Utils.DatabaseUtil.getConnection();
            Statement stmt = con.createStatement();
            String strSelect =
                    "SELECT ID, Name, CountryCode, District, Population " +
                            "FROM city " +
                            "WHERE ID = " + id;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            if (rset.next())
            {
                City city = new City();
                city.id = rset.getInt("ID");
                city.name = rset.getString("Name");
                city.countryCode = rset.getString("CountryCode");
                city.district = rset.getString("District");
                city.population = rset.getInt("Population");
                return city;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    public static void displayCity(City city)
    {
        if (city != null)
        {
            System.out.println(
                    "City ID: " + city.id + "\nName: " + city.name + "\nCountry Code: " + city.countryCode
                    + "\nDistrict: " + city.district + "\nPopulation: " + city.population);
        }
        else
        {
            System.out.println("City not found!");
        }
    }
}