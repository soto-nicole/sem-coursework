package com.napier.sem;

import com.napier.sem.Features.AllCountries;
import com.napier.sem.Helpers.ReportHelper;
import com.napier.sem.Models.City;
import com.napier.sem.Models.Country;
import com.napier.sem.Models.Population;
import com.napier.sem.Models.Language;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AppTest
{
    static final String TEST_CONTINENT = "Africa";
    static final String TEST_REGION = "Caribbean";
    static final String TEST_COUNTRY = "Spain";
    static final String TEST_DISTRICT = "Buenos Aires";
    static  App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    //----------------------- 1. Unit tests: Countries ------------------------------------//
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
    void testAllCountries_ByWorld_ShouldRetrieve_AllCountriesInDB()
    {
        //Arrange
        ReportHelper mockReportHelperClass = mock(ReportHelper.class);
        AllCountries allCountries = new AllCountries(mockReportHelperClass);
        ArrayList<Country> expectedCountries = new ArrayList<>();
        expectedCountries.add(new Country());

        String queryWorld = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name as CapitalName " +
                "FROM country " +
                "JOIN city ON country.Capital = city.ID " +
                "ORDER BY country.Population DESC";

        //Act
        when(mockReportHelperClass.getCountryReport(queryWorld)).thenReturn(expectedCountries);
        List<Country> countries = allCountries.ByWorld();

        //Assert
        assertEquals(expectedCountries, countries);
        verify(mockReportHelperClass).getCountryReport(queryWorld);
    }

    @Test
    void testAllCountries_ByContinent_ShouldRetrieve_AllCountriesInDB_ForSpecificContinent()
    {
        //Arrange
        ReportHelper mockReportHelperClass = mock(ReportHelper.class);
        AllCountries allCountries = new AllCountries(mockReportHelperClass);
        ArrayList<Country> expectedCountries = new ArrayList<>();
        expectedCountries.add(new Country());

        String queryContinent = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name as CapitalName " +
                "FROM country " +
                "JOIN city ON country.Capital = city.ID " +
                "WHERE country.Continent = '" + TEST_CONTINENT + "' " +
                "ORDER BY country.Population DESC";

        //Act
        when(mockReportHelperClass.getCountryReport(queryContinent)).thenReturn(expectedCountries);
        List<Country> countries = allCountries.ByContinent(TEST_CONTINENT);

        //Assert
        assertEquals(expectedCountries, countries);
        verify(mockReportHelperClass).getCountryReport(queryContinent);
    }

    @Test
    void testAllCountries_ByRegion_ShouldRetrieve_AllCountriesInDB_ForSpecificRegion()
    {
        //Arrange
        ReportHelper mockReportHelperClass = mock(ReportHelper.class);
        AllCountries allCountries = new AllCountries(mockReportHelperClass);
        ArrayList<Country> expectedCountries = new ArrayList<>();
        expectedCountries.add(new Country());

        String queryRegion = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name as CapitalName " +
                "FROM country " +
                "JOIN city ON country.Capital = city.ID " +
                "WHERE country.Region = '" + TEST_REGION + "' " +
                "ORDER BY country.Population DESC";

        //Act
        when(mockReportHelperClass.getCountryReport(queryRegion)).thenReturn(expectedCountries);
        List<Country> countries = allCountries.ByRegion(TEST_REGION);

        //Assert
        assertEquals(expectedCountries, countries);
        verify(mockReportHelperClass).getCountryReport(queryRegion);
    }


    //----------------------- 2. Unit tests: Cities ------------------------------------//
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
