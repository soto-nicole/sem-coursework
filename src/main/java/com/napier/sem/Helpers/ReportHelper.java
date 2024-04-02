package com.napier.sem.Helpers;

import com.napier.sem.Models.City;
import com.napier.sem.Models.Country;
import com.napier.sem.Models.LanguageSpeakers;
import com.napier.sem.Models.Population;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

public class ReportHelper
{
    private Connection con;

    public ReportHelper(Connection connection)
    {
        this.con = connection;
    }

    /**
     * Helper method to get the country information by using the SQL queries
     * @param strSelect SQL query that will return the country information needed for the reports
     * @return ArrayList from country objects that contains the required data being fetched
     */
    public ArrayList<Country> getCountryReport(String strSelect)
    {
        ArrayList<Country> countries = new ArrayList<>();
        try
        {
            Statement stmt = this.con.createStatement();
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
    public ArrayList<City> getCityReport(String strSelect)
    {
        ArrayList<City> cities = new ArrayList<>();
        try
        {
            Statement stmt = this.con.createStatement();
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

    /**
     * Helper method to get the population information by using the SQL queries
     * @param strSelect SQL query that will return the population information needed for the reports
     * @return ArrayList from Population objects that contains the required data being fetched
     */
    public ArrayList<Population> getPopulationReport(String strSelect)
    {
        ArrayList<Population> populations = new ArrayList<>();
        try
        {
            Statement stmt = this.con.createStatement();
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                Population population = new Population();
                population.areaName = rset.getString("AreaName");
                population.population = rset.getLong("TotalPopulation");
                population.populationCities = rset.getLong("PopulationCities");
                population.populationCitiesPercentage = rset.getFloat("PopulationCityPercentage");
                population.populationOutsideCities = rset.getLong("PopulationOutsideCities");
                population.populationOutsideCitiesPercentage = rset.getFloat("PopulationOutsideCityPercentage");
                populations.add(population);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population report");
            return null;
        }
        return populations;
    }

    /**
     * Helper method to get the population information by using the SQL queries
     * @param strSelect SQL query that will return the population information needed for the reports
     * @param type A string that denotes the area type that the query will be conducted for, affecting the resulting columns
     * @return Population object that contains the required data being fetched
     */

    public Population getSpecificPopulationReport(String strSelect, String type)
    {
        Population population = new Population();
        try
        {
            Statement stmt = this.con.createStatement();
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                if (Objects.equals(type, "World"))
                {
                    population.areaName = "World";
                } else {
                    population.areaName = rset.getString("AreaName");
                }

                if (!Objects.equals(type, "District") && !Objects.equals(type, "City")) {
                    population.populationCities = rset.getLong("PopulationCities");
                    population.populationCitiesPercentage = rset.getFloat("PopulationCityPercentage");
                    population.populationOutsideCities = rset.getLong("PopulationOutsideCities");
                    population.populationOutsideCitiesPercentage = rset.getFloat("PopulationOutsideCityPercentage");
                }

                population.population = rset.getLong("TotalPopulation");

            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population report");
            return null;
        }
        return population;
    }

    public ArrayList<LanguageSpeakers> getLanguageReport(String strSelect)
    {
        ArrayList<LanguageSpeakers> languages = new ArrayList<>();
        try
        {
            Statement stmt = this.con.createStatement();
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                LanguageSpeakers language = new LanguageSpeakers();
                language.languageName = rset.getString("LanguageName");
                language.totalSpeakers = rset.getLong("TotalLanguageSpeakers");
                language.totalSpeakersPercentage = rset.getFloat("WorldPercentage");
                languages.add(language);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get languages report");
            return null;
        }
        return languages;
    }
}
