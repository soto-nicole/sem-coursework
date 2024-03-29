package com.napier.sem.Features;

import com.napier.sem.Helpers.ReportHelper;
import com.napier.sem.Models.City;
import com.napier.sem.Models.LanguageSpeakers;
import com.napier.sem.Models.Population;

import java.util.ArrayList;

public class LanguageByPopulation {

    private ReportHelper reportHelper;

    public LanguageByPopulation(ReportHelper reportHelper) {
        this.reportHelper = reportHelper;
    }

    /**
     * Gets a report of the total number of speakers of certain languages in the world, including the number of speakers as a percentage of the world *
     *
     * @return ArrayList that contains LanguageSpeakers objects in the world with their respective properties : LanguageName, TotalSpeakers and TotalSpeakersPercentage
     */

    public ArrayList<LanguageSpeakers> ByWorld()
    {
        //(speakers / SUM(country.population) FROM country  * 100) AS % of world_pop

        return reportHelper.getLanguageReport("SELECT language as LanguageName, ROUND(country.population * (percentage/100)) as TotalLanguageSpeakers, percentage " +
                "From countrylanguage " +
                "JOIN country ON countrylanguage.CountryCode = country.Code " +
                "WHERE language = 'Chinese' " +
                "OR language = 'English' " +
                "OR language = 'Hindi' " +
                "OR language = 'Spanish' " +
                "OR language = 'Arabic' " +
                "ORDER BY language DESC ");
    }
}