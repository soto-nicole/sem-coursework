package com.napier.sem.Features;

import com.napier.sem.Helpers.ReportHelper;
import com.napier.sem.Models.Population;
import java.util.ArrayList;

public class AllPopulations
{
    private ReportHelper reportHelper;

    public AllPopulations(ReportHelper reportHelper)
    {
        this.reportHelper = reportHelper;
    }

    /**
     * Gets a list of all the populations in each continent by their population number in descending order
     *
     * @return ArrayList that contains Population objects in a continent with their respective properties : AreaName, Population, PopulationCities, PopulationOutsideCities
     */
    public ArrayList<Population> ByContinent()
    {
        return reportHelper.getPopulationReport("SELECT country.continent as AreaName, SUM(country.population) " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "GROUP BY country.continent");
    }
}