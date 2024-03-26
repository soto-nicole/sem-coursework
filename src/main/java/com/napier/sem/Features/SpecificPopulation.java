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
}