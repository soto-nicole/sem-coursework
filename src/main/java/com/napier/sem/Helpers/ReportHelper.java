package com.napier.sem.Helpers;

import com.napier.sem.Models.City;
import com.napier.sem.Models.Country;
import com.napier.sem.Models.Language;
import com.napier.sem.Models.Population;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A class which contains methods to execute db query statements and populate relevant Class objects based on the result set
 */
public class ReportHelper
{
    /**
     * Allows connection to the database
     */
    private final Connection con;

    /**
     * Constructor for ReportHelper objects
     * @param connection Database connection
     */
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
                Country country = countryResultSet(set);
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
     * Converts a ResultSet row into a Country object
     * @param set The ResultSet to extract the country data.
     * @return Country object which is populated with the data of ResultSet
     */
    private Country countryResultSet(ResultSet set)
    {
        Country country = new Country();
        try
        {
            country.code = set.getString("Code");
            country.name = set.getString("Name");
            country.continent = set.getString("Continent");
            country.region = set.getString("Region");
            country.population = set.getInt("Population");
            country.capitalName = set.getString("CapitalName");
        }
        catch (Exception e)
        {
            System.out.println("Error converting ResultSet to Country: " + e.getMessage());
            return  null;
        }
        return country;
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
                City city = cityResultSet(set);
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
     * Converts a ResultSet row into a City object
     * @param set The ResultSet to extract the city data.
     * @return City object which is populated with the data of ResultSet
     */
    private City cityResultSet(ResultSet set)
    {
        City city = new City();
        try
        {
            city.name = set.getString("Name");
            city.countryCode = set.getString("CountryName");
            city.district = set.getString("District");
            city.population = set.getInt("Population");
        }
        catch (Exception e)
        {
            System.out.println("Error converting ResultSet to City: " + e.getMessage());
        }
        return city;
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
            ResultSet set = stmt.executeQuery(strSelect);

            while (set.next())
            {
                Population population = populationResultSet(set);
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
     * Converts a ResultSet row into a Population object
     * @param set The ResultSet to extract the population data.
     * @return Population object which is populated with the data of ResultSet.
     */
    private Population populationResultSet(ResultSet set)
    {
        Population population = new Population();
        try
        {
            population.areaName = set.getString("AreaName");
            population.population = set.getLong("TotalPopulation");
            population.populationCities = set.getLong("PopulationCities");
            population.populationCitiesPercentage = set.getFloat("PopulationCityPercentage");
            population.populationOutsideCities = set.getLong("PopulationOutsideCities");
            population.populationOutsideCitiesPercentage = set.getFloat("PopulationOutsideCityPercentage");
        }
        catch (Exception e)
        {
            System.out.println("Error converting ResultSet to Population: " + e.getMessage());
        }
        return population;
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
            ResultSet set = stmt.executeQuery(strSelect);

            while (set.next())
            {
                 population = processPopulationResultSet(set, type);
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

    /**
     *
     * @param set The ResultSet from which to extract population data
     * @param type is a string which wil indicate the type of population report that will be generated (world, district...)
     * @return Population object which is populated with the data of ResultSet for a specific type.
     */
    private Population processPopulationResultSet(ResultSet set, String type)
    {
        Population population = new Population();
        try
        {
            if (Objects.equals(type, "World"))
            {
                population.areaName = "World";
            } else {
                population.areaName = set.getString("AreaName");
            }

            if (!Objects.equals(type, "District") && !Objects.equals(type, "City")) {
                population.populationCities = set.getLong("PopulationCities");
                population.populationCitiesPercentage = set.getFloat("PopulationCityPercentage");
                population.populationOutsideCities = set.getLong("PopulationOutsideCities");
                population.populationOutsideCitiesPercentage = set.getFloat("PopulationOutsideCityPercentage");
            }

            population.population = set.getLong("TotalPopulation");
        }
        catch (Exception e)
        {
            System.out.println("Error processing ResultSet for Population: " + e.getMessage());
        }
        return population;
    }

    /**
     * Helper method to get the languages information by using the SQL queries
     * @param strSelect SQL query that will return the languages information needed for the reports
     * @return Language object that contains the required data being fetched
     */
    public ArrayList<Language> getLanguageReport(String strSelect)
    {
        ArrayList<Language> languages = new ArrayList<>();
        try
        {
            Statement stmt = this.con.createStatement();
            ResultSet set = stmt.executeQuery(strSelect);

            while (set.next())
            {
                Language language = languageResultSet(set);
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

    /**
     *
     * @param set The ResultSet to extract the language data from
     * @return Language object populated with data from the ResultSet row.
     */
    private Language languageResultSet(ResultSet set)
    {
        Language language = new Language();
        try
        {
            language.languageName = set.getString("LanguageName");
            language.totalSpeakers = set.getLong("TotalLanguageSpeakers");
            language.totalSpeakersPercentage = set.getFloat("WorldPercentage");
        }
        catch (Exception e)
        {
            System.out.println("Error processing ResultSet for Language: " + e.getMessage());
        }
        return language;
    }
}
