package com.napier.sem;

import com.napier.sem.Models.City;
import com.napier.sem.Models.Country;
import com.napier.sem.Models.Population;
import com.napier.sem.Models.Language;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class AppTest
{
    static  App app;
    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printCountriesTestNull()
    {
        app.displayCountries(null);
    }

    @Test
    void printCountriesTestEmpty()
    {
        ArrayList<Country> countries = new ArrayList<>();
        app.displayCountries(countries);
    }
    @Test
    void printCountriesTestContainsNull()
    {
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(null);
        app.displayCountries(countries);
    }

    @Test
    void printCountries()
    {
        ArrayList<Country> countries = new ArrayList<>();
        Country country = new Country();
        country.code = "ABW";
        country.name = "Aruba";
        country.continent = "North America";
        country.region = "Caribbean";
        country.population = 103000;
        country.capitalName = "Oranjestad";
        countries.add(country);

        app.displayCountries(countries);
    }
    @Test
    void printCitiesTestNull()
    {
        app.displayCities(null);
    }

    @Test
    void printCitiesTestEmpty()
    {
        ArrayList<City> cities = new ArrayList<>();
        app.displayCities(cities);
    }

    @Test
    void printCitiesTestContainsNull()
    {
        ArrayList<City> cities = new ArrayList<>();
        cities.add(null);
        app.displayCities(cities);
    }

    @Test
    void displayCities()
    {
        ArrayList<City> cities = new ArrayList<>();
        City city = new City();
        city.name = "Kabul";
        city.countryCode = "Afghanistan";
        city.district = "Kabul";
        city.population = 1780000;
        cities.add(city);

        app.displayCities(cities);
    }

    @Test
    void printCapitalCitiesTestNull()
    {
        app.displayCapitalCities(null);
    }

    @Test
    void printCapitalCitiesTestEmpty()
    {
        ArrayList<City> capitalCities = new ArrayList<>();
        app.displayCapitalCities(capitalCities);
    }
    @Test
    void printCapitalCitiesTestContainsNull()
    {
        ArrayList<City> capitalCities = new ArrayList<>();
        capitalCities.add(null);
        app.displayCapitalCities(capitalCities);
    }

    @Test
    void printCapitalCities()
    {
        ArrayList<City> capitalCities = new ArrayList<>();
        City capitalCity = new City();
        capitalCity.name = "Kabul";
        capitalCity.countryCode = "Afghanistan";
        capitalCity.population = 1780000;
        capitalCities.add(capitalCity);


        app.displayCapitalCities(capitalCities);
    }

    @Test
    void printPopulationsTestNull()
    {
        app.displayPopulations(null);
    }

    @Test
    void printPopulationsTestEmpty()
    {
        ArrayList<Population> populations = new ArrayList<>();
        app.displayPopulations(populations);
    }
    @Test
    void printPopulationsTestContainsNull()
    {
        ArrayList<Population> populations = new ArrayList<>();
        populations.add(null);
        app.displayPopulations(populations);
    }

    @Test
    void printPopulations()
    {
        ArrayList<Population> populations = new ArrayList<>();
        Population population = new Population();
        population.areaName = "China";
        population.population = 3000000;
        population.populationCities = 1500000;
        population.populationCitiesPercentage = 50.00F;
        population.populationOutsideCities = 1500000;
        population.populationOutsideCitiesPercentage = 50.00F;
        populations.add(population);

        app.displayPopulations(populations);
    }


    @Test
    void printSpecificPopulationTestNull()
    {
        app.displaySpecificPopulation(null, "World");
    }

    @Test
    void printSpecificPopulationTestEmpty()
    {
        Population population = new Population();
        app.displaySpecificPopulation(population, "World");
    }

    @Test
    void printSpecificPopulation()
    {
        Population population = new Population();
        population.areaName = "China";
        population.population = 3000000;
        population.populationCities = 1500000;
        population.populationCitiesPercentage = 50.00F;
        population.populationOutsideCities = 1500000;
        population.populationOutsideCitiesPercentage = 50.00F;

        app.displaySpecificPopulation(population, "Country");
    }

    @Test
    void printLanguagesTestNull()
    {
        app.displayLanguages(null);
    }

    @Test
    void printLanguagesTestEmpty()
    {
        ArrayList<Language> languages = new ArrayList<>();
        app.displayLanguages(languages);
    }
    @Test
    void printLanguagesTestContainsNull()
    {
        ArrayList<Language> languages = new ArrayList<>();
        languages.add(null);
        app.displayLanguages(languages);
    }

    @Test
    void printLanguages()
    {
        ArrayList<Language> languages = new ArrayList<>();
        Language language = new Language();
        language.languageName = "English";
        language.totalSpeakers = 347077867;
        language.totalSpeakersPercentage = 5.7097F;
        languages.add(language);

        app.displayLanguages(languages);
    }
}
