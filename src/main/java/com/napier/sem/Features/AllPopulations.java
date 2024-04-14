package com.napier.sem.Features;

import com.napier.sem.Helpers.ReportHelper;
import com.napier.sem.Models.Population;
import java.util.ArrayList;

/**
 * A class that contains methods relevant to querying all the populations within specific area types
 */
public class AllPopulations
{
    /**
     * An instance of the ReportHelper class, which can be accessed by each method to help run db queries
     */
    private final ReportHelper reportHelper;

    /**
     * Constructor for the AllPopulations object
     * @param reportHelper sets an object of class ReportHelper to be used by AllPopulations when running methods which will query the database
     */
    public AllPopulations(ReportHelper reportHelper) {
        this.reportHelper = reportHelper;
    }

    /**
     * Gets a list of all the populations in each continent by their population number
     *
     * @return ArrayList that contains Population objects in a continent with their respective properties : AreaName, TotalPopulation, PopulationCities, PopulationCityPercentage, PopulationOutsideCities, PopulationOutsideCityPercentage
     */
    public ArrayList<Population> ByContinent()
    {
        return reportHelper.getPopulationReport("SELECT country.continent AS AreaName, COALESCE(SUM(country.population), 0) AS TotalPopulation, COALESCE(SUM(city_population.population), 0) AS PopulationCities, (COALESCE(SUM(city_population.population), 0) / COALESCE(SUM(country.population), 0) * 100) AS PopulationCityPercentage, SUM(country.population) - COALESCE(SUM(city_population.population), 0) AS PopulationOutsideCities, ((SUM(country.population) - COALESCE(SUM(city_population.population), 0)) / COALESCE(SUM(country.population), 0) * 100) AS PopulationOutsideCityPercentage " +
                "FROM country " +
                "LEFT JOIN (SELECT CountryCode, SUM(population) AS population " +
                "FROM city " +
                "GROUP BY CountryCode) AS city_population ON country.Code = city_population.CountryCode " +
                "GROUP BY country.continent ");
    }

    /**
     * Gets a list of all the populations in each region by their population number
     *
     * @return ArrayList that contains Population objects in a continent with their respective properties : AreaName, TotalPopulation, PopulationCities, PopulationCityPercentage, PopulationOutsideCities, PopulationOutsideCityPercentage
     */
    public ArrayList<Population> ByRegion()
    {
        return reportHelper.getPopulationReport("SELECT country.region AS AreaName, COALESCE(SUM(country.population), 0) AS TotalPopulation, COALESCE(SUM(city_population.population), 0) AS PopulationCities, (COALESCE(SUM(city_population.population), 0) / COALESCE(SUM(country.population), 0) * 100) AS PopulationCityPercentage, SUM(country.population) - COALESCE(SUM(city_population.population), 0) AS PopulationOutsideCities, ((SUM(country.population) - COALESCE(SUM(city_population.population), 0)) / COALESCE(SUM(country.population), 0) * 100) AS PopulationOutsideCityPercentage " +
                "FROM country " +
                "LEFT JOIN (SELECT CountryCode, SUM(population) AS population " +
                "FROM city " +
                "GROUP BY CountryCode) AS city_population ON country.Code = city_population.CountryCode " +
                "GROUP BY country.region");
    }

    /**
     * Gets a list of all the populations in each country by their population number
     *
     * @return ArrayList that contains Population objects in a country with their respective properties : AreaName, TotalPopulation, PopulationCities, PopulationCityPercentage, PopulationOutsideCities, PopulationOutsideCityPercentage
     */
    public ArrayList<Population> ByCountry()
    {
        return reportHelper.getPopulationReport("SELECT country.Name AS AreaName, COALESCE(SUM(country.population), 0) AS TotalPopulation, COALESCE(SUM(city_population.population), 0) AS PopulationCities, (COALESCE(SUM(city_population.population), 0) / COALESCE(SUM(country.population), 0) * 100) AS PopulationCityPercentage, SUM(country.population) - COALESCE(SUM(city_population.population), 0) AS PopulationOutsideCities, ((SUM(country.population) - COALESCE(SUM(city_population.population), 0)) / COALESCE(SUM(country.population), 0) * 100) AS PopulationOutsideCityPercentage " +
                "FROM country " +
                "LEFT JOIN (SELECT CountryCode, SUM(population) AS population " +
                "FROM city " +
                "GROUP BY CountryCode) AS city_population ON country.Code = city_population.CountryCode " +
                "GROUP BY country.Name");
    }
}