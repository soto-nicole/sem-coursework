package com.napier.sem.Features;

import com.napier.sem.Helpers.ReportHelper;
import com.napier.sem.Models.Language;

import java.util.ArrayList;

public class LanguageByPopulation
{
    private final ReportHelper reportHelper;

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