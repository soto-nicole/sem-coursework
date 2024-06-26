package com.napier.sem.Features;

import com.napier.sem.Helpers.ReportHelper;
import com.napier.sem.Models.Population;

/**
 * A class that contains methods relevant to querying a specific area's population
 */
public class SpecificPopulation
{
    /**
     * An instance of the ReportHelper class, which can be accessed by each method to help run db queries
     */
    private final ReportHelper reportHelper;

    /**
     * Constructor for the SpecificPopulation object
     * @param reportHelper sets an object of class ReportHelper to be used by SpecificPopulation when running methods which will query the database
     */
    public SpecificPopulation(ReportHelper reportHelper) {
        this.reportHelper = reportHelper;
    }

    /**
     * Gets a report of the total population of the world, including the number of people living within and outside of cities     *
     *
     * @return a Population object that contains the respective properties : AreaName, Population, PopulationCities, PopulationOutsideCityPercentage PopulationOutsideCities, PopulationOutsideCityPercentage
     */

    public Population ByWorld()
    {
        return reportHelper.getSpecificPopulationReport("SELECT COALESCE(SUM(country.population), 0) AS TotalPopulation, COALESCE(SUM(city_population.population), 0) AS PopulationCities, (COALESCE(SUM(city_population.population), 0) / COALESCE(SUM(country.population), 0) * 100) AS PopulationCityPercentage, SUM(country.population) - COALESCE(SUM(city_population.population), 0) AS PopulationOutsideCities, ((SUM(country.population) - COALESCE(SUM(city_population.population), 0)) / COALESCE(SUM(country.population), 0) * 100) AS PopulationOutsideCityPercentage " +
                        "FROM country " +
                        "LEFT JOIN (SELECT CountryCode, SUM(population) AS population " +
                        "FROM city " +
                        "GROUP BY CountryCode) AS city_population ON country.Code = city_population.CountryCode "
                , "World");
    }

    /**
     * Gets a report of the total population of a specific continent, including the number of people living within and outside of cities     *
     *
     * @param continent The name of the continent, constraining the population returned to within the specified continent
     * @return a Population object that contains the respective properties : AreaName, Population, PopulationCities, PopulationOutsideCityPercentage PopulationOutsideCities, PopulationOutsideCityPercentage
     */

    public Population ByContinent(String continent)
    {
        return reportHelper.getSpecificPopulationReport("SELECT country.continent AS AreaName, COALESCE(SUM(country.population), 0) AS TotalPopulation, COALESCE(SUM(city_population.population), 0) AS PopulationCities, (COALESCE(SUM(city_population.population), 0) / COALESCE(SUM(country.population), 0) * 100) AS PopulationCityPercentage, SUM(country.population) - COALESCE(SUM(city_population.population), 0) AS PopulationOutsideCities, ((SUM(country.population) - COALESCE(SUM(city_population.population), 0)) / COALESCE(SUM(country.population), 0) * 100) AS PopulationOutsideCityPercentage " +
                "FROM country " +
                "LEFT JOIN (SELECT CountryCode, SUM(city.population) AS population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Continent = '" + continent + "' " +
                "GROUP BY CountryCode) AS city_population ON country.Code = city_population.CountryCode " +
                "WHERE country.continent = '" + continent + "' " +
                "GROUP BY country.continent", "Continent");
    }

    /**
     * Gets a report of the total population of a specific region, including the number of people living within and outside of cities     *
     *
     * @param region The name of the region, constraining the population returned to within the specified region
     * @return a Population object that contains the respective properties : AreaName, Population, PopulationCities, PopulationOutsideCityPercentage PopulationOutsideCities, PopulationOutsideCityPercentage
     */

    public Population ByRegion(String region)
    {
        return reportHelper.getSpecificPopulationReport("SELECT country.Region AS AreaName, COALESCE(SUM(country.population), 0) AS TotalPopulation, COALESCE(SUM(city_population.population), 0) AS PopulationCities, (COALESCE(SUM(city_population.population), 0) / COALESCE(SUM(country.population), 0) * 100) AS PopulationCityPercentage, SUM(country.population) - COALESCE(SUM(city_population.population), 0) AS PopulationOutsideCities, ((SUM(country.population) - COALESCE(SUM(city_population.population), 0)) / COALESCE(SUM(country.population), 0) * 100) AS PopulationOutsideCityPercentage " +
                "FROM country " +
                "LEFT JOIN (SELECT CountryCode, SUM(city.population) AS population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Region = '" + region + "' " +
                "GROUP BY CountryCode) AS city_population ON country.Code = city_population.CountryCode " +
                "WHERE country.Region = '" + region + "' " +
                "GROUP BY country.Region", "Region");
    }

    /**
     * Gets a report of the total population of a specific country, including the number of people living within and outside of cities     *
     *
     * @param country The name of the country, constraining the population returned to within the specified country
     * @return a Population object that contains the respective properties : AreaName, Population, PopulationCities, PopulationOutsideCityPercentage PopulationOutsideCities, PopulationOutsideCityPercentage
     */

    public Population ByCountry(String country)
    {
        return reportHelper.getSpecificPopulationReport("SELECT country.Name AS AreaName, COALESCE(SUM(country.population), 0) AS TotalPopulation, COALESCE(SUM(city_population.population), 0) AS PopulationCities, (COALESCE(SUM(city_population.population), 0) / COALESCE(SUM(country.population), 0) * 100) AS PopulationCityPercentage, SUM(country.population) - COALESCE(SUM(city_population.population), 0) AS PopulationOutsideCities, ((SUM(country.population) - COALESCE(SUM(city_population.population), 0)) / COALESCE(SUM(country.population), 0) * 100) AS PopulationOutsideCityPercentage " +
                "FROM country " +
                "LEFT JOIN (SELECT CountryCode, SUM(city.population) AS population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Name = '" + country + "' " +
                "GROUP BY CountryCode) AS city_population ON country.Code = city_population.CountryCode " +
                "WHERE country.Name = '" + country + "' " +
                "GROUP BY country.Name", "Country");
    }

    /**
     * Gets a report of the total population of a specific district, including the number of people living within and outside of cities     *
     *
     * @param district The name of the country, constraining the population returned to within the specified district
     * @return a Population object that contains the respective properties : AreaName, Population, PopulationCities, PopulationOutsideCityPercentage PopulationOutsideCities, PopulationOutsideCityPercentage
     */

    public Population ByDistrict(String district)
    {

        return reportHelper.getSpecificPopulationReport("SELECT city.district AS AreaName, COALESCE(SUM(city.population), 0) AS TotalPopulation " +
                "FROM city " +
                "WHERE city.district = '" + district + "' " +
                "GROUP BY city.district", "District");
    }

    /**
     * Gets a report of the total population of a specific city
     *
     * @param city The name of the city, constraining the population returned to within the specified city
     * @return a Population object that contains the respective properties : AreaName, Population,
     */

    public Population ByCity(String city)
    {
        return reportHelper.getSpecificPopulationReport("SELECT city.name AS AreaName, city.population AS TotalPopulation " +
                "FROM city " +
                "WHERE city.name = '" + city + "' ","City");
    }
}