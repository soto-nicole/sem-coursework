package com.napier.sem.Features;

import com.napier.sem.Helpers.ReportHelper;
import com.napier.sem.Models.Population;
import java.util.ArrayList;

public class SpecificPopulation  {
    private ReportHelper reportHelper;

    public SpecificPopulation (ReportHelper reportHelper) {
        this.reportHelper = reportHelper;
    }

    /**
     * Gets a report of the total population of the world, including the number of people living within and outside of cities     *
     * @return a Population object that contains the respective properties : AreaName, Population, PopulationCities, PopulationOutsideCityPercentage PopulationOutsideCities, PopulationOutsideCityPercentage
     */

    public Population ByWorld() {
        return reportHelper.getSpecificPopulationReport("SELECT COALESCE(SUM(country.population), 0) AS TotalPopulation, COALESCE(SUM(city_population.population), 0) AS PopulationCities, (COALESCE(SUM(city_population.population), 0) / COALESCE(SUM(country.population), 0) * 100) AS PopulationCityPercentage, SUM(country.population) - COALESCE(SUM(city_population.population), 0) AS PopulationOutsideCities, ((SUM(country.population) - COALESCE(SUM(city_population.population), 0)) / COALESCE(SUM(country.population), 0) * 100) AS PopulationOutsideCityPercentage " +
                "FROM country " +
                "LEFT JOIN (SELECT CountryCode, SUM(population) AS population " +
                "FROM city " +
                "GROUP BY CountryCode) AS city_population ON country.Code = city_population.CountryCode "
        , "World");
    }

    /**
     * Gets a report of the total population of a specific continent, including the number of people living within and outside of cities     *
     * @param continent The name of the continent, constraining the population returned to within the specified continent
     * * @return a Population object that contains the respective properties : AreaName, Population, PopulationCities, PopulationOutsideCityPercentage PopulationOutsideCities, PopulationOutsideCityPercentage
     */

    public Population ByContinent(String continent) {
        return reportHelper.getSpecificPopulationReport("SELECT country.continent AS AreaName, COALESCE(SUM(country.population), 0) AS TotalPopulation, COALESCE(SUM(city_population.population), 0) AS PopulationCities, (COALESCE(SUM(city_population.population), 0) / COALESCE(SUM(country.population), 0) * 100) AS PopulationCityPercentage, SUM(country.population) - COALESCE(SUM(city_population.population), 0) AS PopulationOutsideCities, ((SUM(country.population) - COALESCE(SUM(city_population.population), 0)) / COALESCE(SUM(country.population), 0) * 100) AS PopulationOutsideCityPercentage " +
                        "FROM country " +
                        "LEFT JOIN (SELECT CountryCode, SUM(city.population) AS population " +
                        "FROM city " +
                        "JOIN country ON city.CountryCode = country.Code " +
                        "WHERE country.Continent = '" + continent + "' " +
                        "GROUP BY CountryCode) AS city_population ON country.Code = city_population.CountryCode " +
                        "WHERE country.continent = '" + continent  + "' " +
                        "GROUP BY country.continent", continent);
    }

}