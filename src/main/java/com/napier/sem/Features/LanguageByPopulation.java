package com.napier.sem.Features;

import com.napier.sem.Helpers.ReportHelper;
import com.napier.sem.Models.Language;

import java.util.ArrayList;

/**
 * A class that contains a method relevant to querying all the speakers of specific languages in the world
 */
public class LanguageByPopulation
{
    /**
     * An instance of the ReportHelper class, which can be accessed by the ByWorld method to help run the db query
     */
    private final ReportHelper reportHelper;

    /**
     * Constructor for the LanguageByPopulation object
     * @param reportHelper sets an object of class ReportHelper to be used by LanguageByPopulation when running the method which will query the database
     */
    public LanguageByPopulation(ReportHelper reportHelper) {
        this.reportHelper = reportHelper;
    }

    /**
     * Gets a report of the total number of speakers of certain languages in the world, including the number of speakers as a percentage of the world *
     *
     * @return ArrayList that contains LanguageSpeakers objects in the world with their respective properties : LanguageName, TotalSpeakers and TotalSpeakersPercentage
     */

    public ArrayList<Language> ByWorld()
    {
        return reportHelper.getLanguageReport("SELECT language as LanguageName, SUM(ROUND(country.population * (percentage/100))) as TotalLanguageSpeakers, (SUM(ROUND(country.population * (percentage/100))) / (SELECT SUM(population) from country) * 100) as WorldPercentage " +
                "From countrylanguage " +
                "JOIN country ON countrylanguage.CountryCode = country.Code " +
                "WHERE language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic') " +
                "GROUP BY language " +
                "ORDER BY TotalLanguageSpeakers DESC"
        );
    }
}