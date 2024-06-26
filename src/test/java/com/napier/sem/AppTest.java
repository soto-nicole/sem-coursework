package com.napier.sem;

import com.napier.sem.Features.*;
import com.napier.sem.Helpers.ReportHelper;
import com.napier.sem.Models.City;
import com.napier.sem.Models.Country;
import com.napier.sem.Models.Population;
import com.napier.sem.Models.Language;
import com.napier.sem.View.Index;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Defines Unit Tests for the application
 */
public class AppTest
{
    /**
     * Defines a static continent name to help test methods
     */
    static final String TEST_CONTINENT = "Africa";
    /**
     * Defines a static region name to help test methods
     */
    static final String TEST_REGION = "Caribbean";
    /**
     * Defines a static country name to help test methods
     */
    static final String TEST_COUNTRY = "Spain";
    /**
     * Defines a static district name to help test methods
     */
    static final String TEST_DISTRICT = "Buenos Aires";
    /**
     * Defines a static city name to help test methods
     */
    static final String TEST_CITY = "Seoul";
    /**
     * Defines a static value for "N", mimicking user input, to help test methods
     */
    static final int TEST_N = 5;
    /**
     * Defines a byte array output stream, to help test methods which print to the console.
     */
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    /**
     * Defines a print stream, to help test methods which print to the console.
     */
    private final PrintStream originalOut = System.out;
    /**
     * Defines a new instance of the App class
     */
    static App app;

    /**
     * Before each test is run, create an instance of app to test with
     */
    @BeforeAll
    static void init()
    {
        app = new App();
    }

    /**
     * Before each test is run, set up the streams which help the test print to the console.
     */
    @BeforeEach
    public void setUpStreams()
    {
        System.setOut(new PrintStream(outContent));
    }

    /**
     * After each test is run, remove the print stream that was set
     */
    @AfterEach
    public void restoreStreams()
    {
        System.setOut(originalOut);
    }

    //----------------------- 1. Unit tests: Countries ------------------------------------//

    /**
     * Tests that the displayCountries() method handles null input without error
     */
    @Test
    void printCountriesTestNull()
    {
        app.displayCountries(null);
    }

    /**
     * Tests that the displayCountries() method handles an empty array input without error
     */
    @Test
    void printCountriesTestEmpty()
    {
        ArrayList<Country> countries = new ArrayList<>();
        app.displayCountries(countries);
    }

    /**
     * Tests that the displayCountries() method handles an array which contains a null value without error
     */
    @Test
    void printCountriesTestContainsNull()
    {
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(null);
        app.displayCountries(countries);
    }

    /**
     * Tests that the displayCountries() method successfully prints Country array objects to the console
     */
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

    /**
     * Uses a mocked ReportHelper class to help simulate querying the db
     * This tests that the AllCountries.ByWorld() method successfully returns Country object(s) after a query
     */
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

    /**
     * Uses a mocked ReportHelper class to help simulate querying the db
     * This tests that the AllCountries.ByContinent() method successfully returns Country object(s) after a query
     */
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

    /**
     * Uses a mocked ReportHelper class to help simulate querying the db
     * This tests that the AllCountries.ByRegion() method successfully returns Country object(s) after a query
     */
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

    /**
     * Uses a mocked ReportHelper class to help simulate querying the db
     * This tests that the TopNCountries.ByWorld() method successfully returns the correct number of Country object(s) after a query
     */
    @Test
    void testTopNCountries_ByWorld_ShouldRetrieve_TopNCountriesInDB()
    {
        //Arrange
        ReportHelper mockReportHelperClass = mock(ReportHelper.class);
        TopNCountries topNCountries = new TopNCountries(mockReportHelperClass);
        ArrayList<Country> expectedCountries = new ArrayList<>();
        for (int i = 0; i < TEST_N; i++)
        {
            expectedCountries.add(new Country());
        }

        String queryWorld = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name as CapitalName " +
                "FROM country " +
                "JOIN city ON country.Capital = city.ID " +
                "ORDER BY country.Population DESC " +
                "LIMIT " + TEST_N;

        //Act
        when(mockReportHelperClass.getCountryReport(queryWorld)).thenReturn(expectedCountries);
        List<Country> countries = topNCountries.ByWorld(TEST_N);
        int countriesLength = countries.size();

        //Assert
        assertEquals(expectedCountries, countries);
        assertEquals(countriesLength, TEST_N);
        verify(mockReportHelperClass).getCountryReport(queryWorld);
    }

    /**
     * Uses a mocked ReportHelper class to help simulate querying the db
     * This tests that the TopNCountries.ByContinent() method successfully returns the correct number of Country object(s) after a query
     */
    @Test
    void testTopNCountries_ByContinent_ShouldRetrieve_TopNCountriesInDB_ForSpecificContinent()
    {
        //Arrange
        ReportHelper mockReportHelperClass = mock(ReportHelper.class);
        TopNCountries topNCountries = new TopNCountries(mockReportHelperClass);
        ArrayList<Country> expectedCountries = new ArrayList<>();
        for (int i = 0; i < TEST_N; i++)
        {
            expectedCountries.add(new Country());
        }

        String queryContinent = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name as CapitalName " +
                "FROM country " +
                "JOIN city ON country.Capital = city.ID " +
                "WHERE country.Continent = '" + TEST_CONTINENT + "' " +
                "ORDER BY country.Population DESC " +
                "LIMIT " + TEST_N;

        //Act
        when(mockReportHelperClass.getCountryReport(queryContinent)).thenReturn(expectedCountries);
        List<Country> countries = topNCountries.ByContinent(TEST_N, TEST_CONTINENT);
        int countriesLength = countries.size();

        //Assert
        assertEquals(expectedCountries, countries);
        assertEquals(countriesLength, TEST_N);
        verify(mockReportHelperClass).getCountryReport(queryContinent);
    }

    /**
     * Uses a mocked ReportHelper class to help simulate querying the db
     * This tests that the TopNCountries.ByRegion() method successfully returns the correct number of Country object(s) after a query
     */
    @Test
    void testTopNCountries_ByRegion_ShouldRetrieve_TopNCountriesInDB_ForSpecificRegion()
    {
        //Arrange
        ReportHelper mockReportHelperClass = mock(ReportHelper.class);
        TopNCountries topNCountries = new TopNCountries(mockReportHelperClass);
        ArrayList<Country> expectedCountries = new ArrayList<>();
        for (int i = 0; i < TEST_N; i++)
        {
            expectedCountries.add(new Country());
        }

        String queryRegion = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name as CapitalName " +
                "FROM country " +
                "JOIN city ON country.Capital = city.ID " +
                "WHERE country.Region = '" + TEST_REGION + "' " +
                "ORDER BY country.Population DESC " +
                "LIMIT " + TEST_N;

        //Act
        when(mockReportHelperClass.getCountryReport(queryRegion)).thenReturn(expectedCountries);
        List<Country> countries = topNCountries.ByRegion(TEST_N, TEST_REGION);
        int countriesLength = countries.size();

        //Assert
        assertEquals(expectedCountries, countries);
        assertEquals(countriesLength, TEST_N);
        verify(mockReportHelperClass).getCountryReport(queryRegion);
    }

    //----------------------- 2. Unit tests: Cities ------------------------------------//

    /**
     * Tests that the displayCities() method handles null input without error
     */
    @Test
    void printCitiesTestNull()
    {
        app.displayCities(null);
    }

    /**
     * Tests that the displayCities() method handles an empty array input without error
     */
    @Test
    void printCitiesTestEmpty()
    {
        ArrayList<City> cities = new ArrayList<>();
        app.displayCities(cities);
    }

    /**
     * Tests that the displayCities() method handles an array which contains a null value without error
     */
    @Test
    void printCitiesTestContainsNull()
    {
        ArrayList<City> cities = new ArrayList<>();
        cities.add(null);
        app.displayCities(cities);
    }

    /**
     * Tests that the displayCities() method successfully prints City array objects to the console
     */
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

    /**
     * Uses a mocked ReportHelper class to help simulate querying the db
     * This tests that the AllCities.ByWorld() method successfully returns City object(s) after a query
     */
    @Test
    void testAllCities_ByWorld_ShouldRetrieve_AllCitiesInDB()
    {
        //Arrange
        ReportHelper mockReportHelperClass = mock(ReportHelper.class);
        AllCities allCities = new AllCities(mockReportHelperClass);
        ArrayList<City> expectedCities = new ArrayList<>();
        expectedCities.add(new City());

        String queryWorld = "SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "ORDER BY city.Population DESC";

        //Act
        when(mockReportHelperClass.getCityReport(queryWorld)).thenReturn(expectedCities);
        List<City> cities = allCities.ByWorld();

        //Assert
        assertEquals(expectedCities, cities);
        verify(mockReportHelperClass).getCityReport(queryWorld);
    }

    /**
     * Uses a mocked ReportHelper class to help simulate querying the db
     * This tests that the AllCities.ByContinent() method successfully returns City object(s) after a query
     */
    @Test
    void testAllCities_ByContinent_ShouldRetrieve_AllCitiesInDB_ForSpecificContinent()
    {
        //Arrange
        ReportHelper mockReportHelperClass = mock(ReportHelper.class);
        AllCities allCities = new AllCities(mockReportHelperClass);
        ArrayList<City> expectedCities = new ArrayList<>();
        expectedCities.add(new City());

        String queryContinent = "SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Continent = '" + TEST_CONTINENT + "' " +
                "ORDER BY city.Population DESC";

        //Act
        when(mockReportHelperClass.getCityReport(queryContinent)).thenReturn(expectedCities);
        List<City> cities = allCities.ByContinent(TEST_CONTINENT);

        //Assert
        assertEquals(expectedCities, cities);
        verify(mockReportHelperClass).getCityReport(queryContinent);
    }

    /**
     * Uses a mocked ReportHelper class to help simulate querying the db
     * This tests that the AllCities.ByRegion() method successfully returns City object(s) after a query
     */
    @Test
    void testAllCities_ByRegion_ShouldRetrieve_AllCitiesInDB_ForSpecificRegion()
    {
        //Arrange
        ReportHelper mockReportHelperClass = mock(ReportHelper.class);
        AllCities allCities = new AllCities(mockReportHelperClass);
        ArrayList<City> expectedCities = new ArrayList<>();
        expectedCities.add(new City());

        String queryRegion = "SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Region = '" + TEST_REGION + "' " +
                "ORDER BY city.Population DESC";

        //Act
        when(mockReportHelperClass.getCityReport(queryRegion)).thenReturn(expectedCities);
        List<City> cities = allCities.ByRegion(TEST_REGION);

        //Assert
        assertEquals(expectedCities, cities);
        verify(mockReportHelperClass).getCityReport(queryRegion);
    }

    /**
     * Uses a mocked ReportHelper class to help simulate querying the db
     * This tests that the AllCities.ByCountry() method successfully returns City object(s) after a query
     */
    @Test
    void testAllCities_ByCountry_ShouldRetrieve_AllCitiesInDB_ForSpecificCountry()
    {
        //Arrange
        ReportHelper mockReportHelperClass = mock(ReportHelper.class);
        AllCities allCities = new AllCities(mockReportHelperClass);
        ArrayList<City> expectedCities = new ArrayList<>();
        expectedCities.add(new City());

        String queryCountry = "SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Name = '" + TEST_COUNTRY + "' " +
                "ORDER BY city.Population DESC";

        //Act
        when(mockReportHelperClass.getCityReport(queryCountry)).thenReturn(expectedCities);
        List<City> cities = allCities.ByCountry(TEST_COUNTRY);

        //Assert
        assertEquals(expectedCities, cities);
        verify(mockReportHelperClass).getCityReport(queryCountry);
    }

    /**
     * Uses a mocked ReportHelper class to help simulate querying the db
     * This tests that the AllCities.ByDistrict() method successfully returns City object(s) after a query
     */
    @Test
    void testAllCities_ByDistrict_ShouldRetrieve_AllCitiesInDB_ForSpecificDistrict()
    {
        //Arrange
        ReportHelper mockReportHelperClass = mock(ReportHelper.class);
        AllCities allCities = new AllCities(mockReportHelperClass);
        ArrayList<City> expectedCities = new ArrayList<>();
        expectedCities.add(new City());

        String queryDistrict = "SELECT city.Name, city.District, city.Population, country.Name as CountryName " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE city.District = '" + TEST_DISTRICT + "' " +
                "ORDER BY city.Population DESC";

        //Act
        when(mockReportHelperClass.getCityReport(queryDistrict)).thenReturn(expectedCities);
        List<City> cities = allCities.ByDistrict(TEST_DISTRICT);

        //Assert
        assertEquals(expectedCities, cities);
        verify(mockReportHelperClass).getCityReport(queryDistrict);
    }

    /**
     * Uses a mocked ReportHelper class to help simulate querying the db
     * This tests that the TopNCities.ByWorld() method successfully returns the correct number of City object(s) after a query
     */
    @Test
    void testTopNCities_ByWorld_ShouldRetrieve_TopNCitiesInDB()
    {
        //Arrange
        ReportHelper mockReportHelperClass = mock(ReportHelper.class);
        TopNCities topNCities = new TopNCities(mockReportHelperClass);
        ArrayList<City> expectedCities = new ArrayList<>();
        for (int i = 0; i < TEST_N; i++)
        {
            expectedCities.add(new City());
        }

        String queryWorld = "SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "ORDER BY city.Population DESC " +
                "LIMIT " + TEST_N;

        //Act
        when(mockReportHelperClass.getCityReport(queryWorld)).thenReturn(expectedCities);
        List<City> cities = topNCities.ByWorld(TEST_N);
        int citiesLength = cities.size();

        //Assert
        assertEquals(expectedCities, cities);
        assertEquals(citiesLength, TEST_N);
        verify(mockReportHelperClass).getCityReport(queryWorld);
    }

    /**
     * Uses a mocked ReportHelper class to help simulate querying the db
     * This tests that the TopNCities.ByContinent() method successfully returns the correct number of City object(s) after a query
     */
    @Test
    void testTopNCities_ByContinent_ShouldRetrieve_TopNCitiesInDB_ForSpecificContinent()
    {
        //Arrange
        ReportHelper mockReportHelperClass = mock(ReportHelper.class);
        TopNCities topNCities = new TopNCities(mockReportHelperClass);
        ArrayList<City> expectedCities = new ArrayList<>();
        for (int i = 0; i < TEST_N; i++)
        {
            expectedCities.add(new City());
        }

        String queryContinent = "SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.continent = '" + TEST_CONTINENT + "'" +
                "ORDER BY city.Population DESC " +
                "LIMIT " + TEST_N;

        //Act
        when(mockReportHelperClass.getCityReport(queryContinent)).thenReturn(expectedCities);
        List<City> cities = topNCities.ByContinent(TEST_N, TEST_CONTINENT);
        int citiesLength = cities.size();

        //Assert
        assertEquals(expectedCities, cities);
        assertEquals(citiesLength, TEST_N);
        verify(mockReportHelperClass).getCityReport(queryContinent);
    }

    /**
     * Uses a mocked ReportHelper class to help simulate querying the db
     * This tests that the TopNCities.ByRegion() method successfully returns the correct number of City object(s) after a query
     */
    @Test
    void testTopNCities_ByRegion_ShouldRetrieve_TopNCitiesInDB_ForSpecificRegion()
    {
        //Arrange
        ReportHelper mockReportHelperClass = mock(ReportHelper.class);
        TopNCities topNCities = new TopNCities(mockReportHelperClass);
        ArrayList<City> expectedCities = new ArrayList<>();
        for (int i = 0; i < TEST_N; i++)
        {
            expectedCities.add(new City());
        }

        String queryRegion = "SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.region = '" + TEST_REGION + "'" +
                "ORDER BY city.Population DESC " +
                "LIMIT " + TEST_N;

        //Act
        when(mockReportHelperClass.getCityReport(queryRegion)).thenReturn(expectedCities);
        List<City> cities = topNCities.ByRegion(TEST_N, TEST_REGION);
        int citiesLength = cities.size();

        //Assert
        assertEquals(expectedCities, cities);
        assertEquals(citiesLength, TEST_N);
        verify(mockReportHelperClass).getCityReport(queryRegion);
    }

    /**
     * Uses a mocked ReportHelper class to help simulate querying the db
     * This tests that the TopNCities.ByCountry() method successfully returns the correct number of City object(s) after a query
     */
    @Test
    void testTopNCities_ByCountry_ShouldRetrieve_TopNCitiesInDB_ForSpecificCountry()
    {
        //Arrange
        ReportHelper mockReportHelperClass = mock(ReportHelper.class);
        TopNCities topNCities = new TopNCities(mockReportHelperClass);
        ArrayList<City> expectedCities = new ArrayList<>();
        for (int i = 0; i < TEST_N; i++)
        {
            expectedCities.add(new City());
        }

        String queryCountry = "SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Name = '" + TEST_COUNTRY + "'" +
                "ORDER BY city.Population DESC " +
                "LIMIT " + TEST_N;

        //Act
        when(mockReportHelperClass.getCityReport(queryCountry)).thenReturn(expectedCities);
        List<City> cities = topNCities.ByCountry(TEST_N, TEST_COUNTRY);
        int citiesLength = cities.size();

        //Assert
        assertEquals(expectedCities, cities);
        assertEquals(citiesLength, TEST_N);
        verify(mockReportHelperClass).getCityReport(queryCountry);
    }

    /**
     * Uses a mocked ReportHelper class to help simulate querying the db
     * This tests that the TopNCities.ByDistrict() method successfully returns the correct number of City object(s) after a query
     */
    @Test
    void testTopNCities_ByDistrict_ShouldRetrieve_TopNCitiesInDB_ForSpecificDistrict()
    {
        //Arrange
        ReportHelper mockReportHelperClass = mock(ReportHelper.class);
        TopNCities topNCities = new TopNCities(mockReportHelperClass);
        ArrayList<City> expectedCities = new ArrayList<>();
        for (int i = 0; i < TEST_N; i++)
        {
            expectedCities.add(new City());
        }

        String queryDistrict = "SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE city.District = '" + TEST_DISTRICT + "'" +
                "ORDER BY city.Population DESC " +
                "LIMIT " + TEST_N;

        //Act
        when(mockReportHelperClass.getCityReport(queryDistrict)).thenReturn(expectedCities);
        List<City> cities = topNCities.ByDistrict(TEST_N, TEST_DISTRICT);
        int citiesLength = cities.size();

        //Assert
        assertEquals(expectedCities, cities);
        assertEquals(citiesLength, TEST_N);
        verify(mockReportHelperClass).getCityReport(queryDistrict);
    }
    //----------------------- 3. Unit tests: Capital Cities ------------------------------------//
    /**
     * Tests that the displayCapitalCities() method handles null input without error
     */
    @Test
    void printCapitalCitiesTestNull()
    {
        app.displayCapitalCities(null);
    }

    /**
     * Tests that the displayCapitalCities() method handles an empty array input without error
     */
    @Test
    void printCapitalCitiesTestEmpty()
    {
        ArrayList<City> capitalCities = new ArrayList<>();
        app.displayCapitalCities(capitalCities);
    }

    /**
     * Tests that the displayCapitalCities() method handles an array which contains a null value without error
     */
    @Test
    void printCapitalCitiesTestContainsNull()
    {
        ArrayList<City> capitalCities = new ArrayList<>();
        capitalCities.add(null);
        app.displayCapitalCities(capitalCities);
    }

    /**
     * Tests that the displayCapitalCities() method successfully prints City array objects to the console
     */
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

    /**
     * Uses a mocked ReportHelper class to help simulate querying the db
     * This tests that the AllCapitalCities.ByWorld() method successfully returns City object(s) after a query
     */
    @Test
    void testAllCapitalCities_ByWorld_ShouldRetrieve_AllCapitalCitiesInDB()
    {
        //Arrange
        ReportHelper mockReportHelperClass = mock(ReportHelper.class);
        AllCapitalCities allCapitalCities = new AllCapitalCities(mockReportHelperClass);
        ArrayList<City> expectedCapitalCities = new ArrayList<>();
        expectedCapitalCities.add(new City());

        String queryWorld = "SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.capital = city.id " +
                "ORDER BY city.Population DESC";

        //Act
        when(mockReportHelperClass.getCityReport(queryWorld)).thenReturn(expectedCapitalCities);
        List<City> capitalCities = allCapitalCities.ByWorld();

        //Assert
        assertEquals(expectedCapitalCities, capitalCities);
        verify(mockReportHelperClass).getCityReport(queryWorld);
    }

    /**
     * Uses a mocked ReportHelper class to help simulate querying the db
     * This tests that the AllCapitalCities.ByContinent() method successfully returns City object(s) after a query
     */
    @Test
    void testAllCapitalCities_ByContinent_ShouldRetrieve_AllCapitalCitiesInDB_ForSpecificContinent()
    {
        //Arrange
        ReportHelper mockReportHelperClass = mock(ReportHelper.class);
        AllCapitalCities allCapitalCities = new AllCapitalCities(mockReportHelperClass);
        ArrayList<City> expectedCapitalCities = new ArrayList<>();
        expectedCapitalCities.add(new City());

        String queryContinent = "SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.capital = city.id " +
                "AND country.Continent = '" + TEST_CONTINENT + "' " +
                "ORDER BY city.Population DESC ";

        //Act
        when(mockReportHelperClass.getCityReport(queryContinent)).thenReturn(expectedCapitalCities);
        List<City> capitalCities = allCapitalCities.ByContinent(TEST_CONTINENT);

        //Assert
        assertEquals(expectedCapitalCities, capitalCities);
        verify(mockReportHelperClass).getCityReport(queryContinent);
    }

    /**
     * Uses a mocked ReportHelper class to help simulate querying the db
     * This tests that the AllCapitalCities.ByRegion() method successfully returns City object(s) after a query
     */
    @Test
    void testAllCapitalCities_ByRegion_ShouldRetrieve_AllCapitalCitiesInDB_ForSpecificRegion()
    {
        //Arrange
        ReportHelper mockReportHelperClass = mock(ReportHelper.class);
        AllCapitalCities allCapitalCities = new AllCapitalCities(mockReportHelperClass);
        ArrayList<City> expectedCapitalCities = new ArrayList<>();
        expectedCapitalCities.add(new City());

        String queryContinent = "SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.capital = city.id " +
                "AND country.Region = '" + TEST_REGION + "' " +
                "ORDER BY city.Population DESC ";

        //Act
        when(mockReportHelperClass.getCityReport(queryContinent)).thenReturn(expectedCapitalCities);
        List<City> capitalCities = allCapitalCities.ByRegion(TEST_REGION);

        //Assert
        assertEquals(expectedCapitalCities, capitalCities);
        verify(mockReportHelperClass).getCityReport(queryContinent);
    }

    /**
     * Uses a mocked ReportHelper class to help simulate querying the db
     * This tests that the TopNCapitalCities.ByWorld() method successfully returns the correct number of City object(s) after a query
     */
    @Test
    void testTopNCapitalCities_ByWorld_ShouldRetrieve_TopNCapitalCitiesInDB()
    {
        //Arrange
        ReportHelper mockReportHelperClass = mock(ReportHelper.class);
        TopNCapitalCities topNCapitalCities = new TopNCapitalCities(mockReportHelperClass);
        ArrayList<City> expectedCapitalCities = new ArrayList<>();
        for (int i = 0; i < TEST_N; i++)
        {
            expectedCapitalCities.add(new City());
        }

        String queryWorld = "SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.capital = city.id " +
                "ORDER BY city.Population DESC " +
                "LIMIT " + TEST_N;

        //Act
        when(mockReportHelperClass.getCityReport(queryWorld)).thenReturn(expectedCapitalCities);
        List<City> capitalCities = topNCapitalCities.ByWorld(TEST_N);
        int capitalCitiesLength = capitalCities.size();

        //Assert
        assertEquals(expectedCapitalCities, capitalCities);
        assertEquals(capitalCitiesLength, TEST_N);
        verify(mockReportHelperClass).getCityReport(queryWorld);
    }

    /**
     * Uses a mocked ReportHelper class to help simulate querying the db
     * This tests that the TopNCapitalCities.ByContinent() method successfully returns the correct number of City object(s) after a query
     */
    @Test
    void testTopNCapitalCities_ByContinent_ShouldRetrieve_TopNCapitalCitiesInDB_ForSpecificContinent()
    {
        //Arrange
        ReportHelper mockReportHelperClass = mock(ReportHelper.class);
        TopNCapitalCities topNCapitalCities = new TopNCapitalCities(mockReportHelperClass);
        ArrayList<City> expectedCapitalCities = new ArrayList<>();
        for (int i = 0; i < TEST_N; i++)
        {
            expectedCapitalCities.add(new City());
        }

        String queryContinent = "SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.capital = city.id " +
                "AND country.Continent = '" + TEST_CONTINENT + "' " +
                "ORDER BY city.Population DESC " +
                "LIMIT " + TEST_N;

        //Act
        when(mockReportHelperClass.getCityReport(queryContinent)).thenReturn(expectedCapitalCities);
        List<City> capitalCities = topNCapitalCities.ByContinent(TEST_N, TEST_CONTINENT);
        int capitalCitiesLength = capitalCities.size();

        //Assert
        assertEquals(expectedCapitalCities, capitalCities);
        assertEquals(capitalCitiesLength, TEST_N);
        verify(mockReportHelperClass).getCityReport(queryContinent);
    }

    /**
     * Uses a mocked ReportHelper class to help simulate querying the db
     * This tests that the TopNCapitalCities.ByRegion() method successfully returns the correct number of City object(s) after a query
     */
    @Test
    void testTopNCapitalCities_ByRegion_ShouldRetrieve_TopNCapitalCitiesInDB_ForSpecificRegion()
    {
        //Arrange
        ReportHelper mockReportHelperClass = mock(ReportHelper.class);
        TopNCapitalCities topNCapitalCities = new TopNCapitalCities(mockReportHelperClass);
        ArrayList<City> expectedCapitalCities = new ArrayList<>();
        for (int i = 0; i < TEST_N; i++)
        {
            expectedCapitalCities.add(new City());
        }

        String queryRegion = "SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.capital = city.id " +
                "AND country.Region = '" + TEST_REGION + "' " +
                "ORDER BY city.Population DESC " +
                "LIMIT " + TEST_N;

        //Act
        when(mockReportHelperClass.getCityReport(queryRegion)).thenReturn(expectedCapitalCities);
        List<City> capitalCities = topNCapitalCities.ByRegion(TEST_N, TEST_REGION);
        int capitalCitiesLength = capitalCities.size();

        //Assert
        assertEquals(expectedCapitalCities, capitalCities);
        assertEquals(capitalCitiesLength, TEST_N);
        verify(mockReportHelperClass).getCityReport(queryRegion);
    }

    //----------------------- 4. Unit tests: Population ------------------------------------//
    /**
     * Tests that the displayPopulations() method handles null input without error
     */
    @Test
    void printPopulationsTestNull()
    {
        app.displayPopulations(null);
    }

    /**
     * Tests that the displayPopulations() method handles an empty array input without error
     */
    @Test
    void printPopulationsTestEmpty()
    {
        ArrayList<Population> populations = new ArrayList<>();
        app.displayPopulations(populations);
    }

    /**
     * Tests that the displayPopulations() method handles an array which contains a null value without error
     */
    @Test
    void printPopulationsTestContainsNull()
    {
        ArrayList<Population> populations = new ArrayList<>();
        populations.add(null);
        app.displayPopulations(populations);
    }

    /**
     * Tests that the displayPopulations() method successfully prints Population array objects to the console
     */
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

    /**
     * Uses a mocked ReportHelper class to help simulate querying the db
     * This tests that the AllPopulations.ByContinent() method successfully returns Population object(s) after a query
     */
    @Test
    void testAllPopulation_ByContinent_ShouldRetrieve_AllPopulationInDB()
    {
        //Arrange
        ReportHelper mockReportHelperClass = mock(ReportHelper.class);
        AllPopulations allPopulations = new AllPopulations(mockReportHelperClass);
        ArrayList<Population> expectedPopulation = new ArrayList<>();
        expectedPopulation.add(new Population());

        String queryContinent = "SELECT country.continent AS AreaName, COALESCE(SUM(country.population), 0) AS TotalPopulation, COALESCE(SUM(city_population.population), 0) AS PopulationCities, (COALESCE(SUM(city_population.population), 0) / COALESCE(SUM(country.population), 0) * 100) AS PopulationCityPercentage, SUM(country.population) - COALESCE(SUM(city_population.population), 0) AS PopulationOutsideCities, ((SUM(country.population) - COALESCE(SUM(city_population.population), 0)) / COALESCE(SUM(country.population), 0) * 100) AS PopulationOutsideCityPercentage " +
                "FROM country " +
                "LEFT JOIN (SELECT CountryCode, SUM(population) AS population " +
                "FROM city " +
                "GROUP BY CountryCode) AS city_population ON country.Code = city_population.CountryCode " +
                "GROUP BY country.continent ";

        //Act
        when(mockReportHelperClass.getPopulationReport(queryContinent)).thenReturn(expectedPopulation);
        List<Population> populations = allPopulations.ByContinent();

        //Assert
        assertEquals(expectedPopulation, populations);
        verify(mockReportHelperClass).getPopulationReport(queryContinent);
    }

    /**
     * Uses a mocked ReportHelper class to help simulate querying the db
     * This tests that the AllPopulations.ByRegion() method successfully returns Population object(s) after a query
     */
    @Test
    void testAllPopulation_ByRegion_ShouldRetrieve_AllPopulationInDB_ForSpecificContinent()
    {
        //Arrange
        ReportHelper mockReportHelperClass = mock(ReportHelper.class);
        AllPopulations allPopulations = new AllPopulations(mockReportHelperClass);
        ArrayList<Population> expectedPopulation = new ArrayList<>();
        expectedPopulation.add(new Population());

        String queryRegion = "SELECT country.region AS AreaName, COALESCE(SUM(country.population), 0) AS TotalPopulation, COALESCE(SUM(city_population.population), 0) AS PopulationCities, (COALESCE(SUM(city_population.population), 0) / COALESCE(SUM(country.population), 0) * 100) AS PopulationCityPercentage, SUM(country.population) - COALESCE(SUM(city_population.population), 0) AS PopulationOutsideCities, ((SUM(country.population) - COALESCE(SUM(city_population.population), 0)) / COALESCE(SUM(country.population), 0) * 100) AS PopulationOutsideCityPercentage " +
                "FROM country " +
                "LEFT JOIN (SELECT CountryCode, SUM(population) AS population " +
                "FROM city " +
                "GROUP BY CountryCode) AS city_population ON country.Code = city_population.CountryCode " +
                "GROUP BY country.region";

        //Act
        when(mockReportHelperClass.getPopulationReport(queryRegion)).thenReturn(expectedPopulation);
        List<Population> populations = allPopulations.ByRegion();

        //Assert
        assertEquals(expectedPopulation, populations);
        verify(mockReportHelperClass).getPopulationReport(queryRegion);
    }

    /**
     * Uses a mocked ReportHelper class to help simulate querying the db
     * This tests that the AllPopulations.ByCountry() method successfully returns Population object(s) after a query
     */
    @Test
    void testAllPopulation_ByCountry_ShouldRetrieve_AllPopulationInDB_ForSpecificRegion()
    {
        //Arrange
        ReportHelper mockReportHelperClass = mock(ReportHelper.class);
        AllPopulations allPopulations = new AllPopulations(mockReportHelperClass);
        ArrayList<Population> expectedPopulation = new ArrayList<>();
        expectedPopulation.add(new Population());

        String queryRegion = "SELECT country.Name AS AreaName, COALESCE(SUM(country.population), 0) AS TotalPopulation, COALESCE(SUM(city_population.population), 0) AS PopulationCities, (COALESCE(SUM(city_population.population), 0) / COALESCE(SUM(country.population), 0) * 100) AS PopulationCityPercentage, SUM(country.population) - COALESCE(SUM(city_population.population), 0) AS PopulationOutsideCities, ((SUM(country.population) - COALESCE(SUM(city_population.population), 0)) / COALESCE(SUM(country.population), 0) * 100) AS PopulationOutsideCityPercentage " +
                "FROM country " +
                "LEFT JOIN (SELECT CountryCode, SUM(population) AS population " +
                "FROM city " +
                "GROUP BY CountryCode) AS city_population ON country.Code = city_population.CountryCode " +
                "GROUP BY country.Name";

        //Act
        when(mockReportHelperClass.getPopulationReport(queryRegion)).thenReturn(expectedPopulation);
        List<Population> populations = allPopulations.ByCountry();

        //Assert
        assertEquals(expectedPopulation, populations);
        verify(mockReportHelperClass).getPopulationReport(queryRegion);
    }

    //----------------------- 5. Unit tests: Specific Population ------------------------------------//

    /**
     * Tests that the displaySpecificPopulation () method handles null input without error
     */
    @Test
    void printSpecificPopulationTestNull()
    {
        app.displaySpecificPopulation(null, "World");
    }

    /**
     * Tests that the displaySpecificPopulation() method handles an empty Population object without error
     */
    @Test
    void printSpecificPopulationTestEmpty()
    {
        Population population = new Population();
        app.displaySpecificPopulation(population, "World");
    }

    /**
     * Tests that the displaySpecificPopulation() method handles a District query and successfully prints a Population object to the console
     */
    @Test
    void printSpecificPopulationDistrict()
    {
        Population population = new Population();
        population.areaName = TEST_DISTRICT;
        app.displaySpecificPopulation(population, "District");
    }

    /**
     * Tests that the displaySpecificPopulation() method handles a City query and successfully prints a Population object to the console
     */
    @Test
    void printSpecificPopulationCity()
    {
        Population population = new Population();
        population.areaName = "London";
        app.displaySpecificPopulation(population, "City");
    }

    /**
     * Tests that the displaySpecificPopulation() method handles a Country query and successfully prints a Population object to the console
     */
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

    /**
     * Uses a mocked ReportHelper class to help simulate querying the db
     * This tests that the SpecificPopulation.ByWorld() method successfully returns a Population object after a query
     */
    @Test
    void testSpecificPopulation_ByWorld_ShouldRetrieve_PopulationInDB_ForWorld()
    {
        //Arrange
        ReportHelper mockReportHelperClass = mock(ReportHelper.class);
        SpecificPopulation specificPopulation = new SpecificPopulation(mockReportHelperClass);
        Population expectedPopulation = new Population();

        String queryWorld = "SELECT COALESCE(SUM(country.population), 0) AS TotalPopulation, COALESCE(SUM(city_population.population), 0) AS PopulationCities, (COALESCE(SUM(city_population.population), 0) / COALESCE(SUM(country.population), 0) * 100) AS PopulationCityPercentage, SUM(country.population) - COALESCE(SUM(city_population.population), 0) AS PopulationOutsideCities, ((SUM(country.population) - COALESCE(SUM(city_population.population), 0)) / COALESCE(SUM(country.population), 0) * 100) AS PopulationOutsideCityPercentage " +
                "FROM country " +
                "LEFT JOIN (SELECT CountryCode, SUM(population) AS population " +
                "FROM city " +
                "GROUP BY CountryCode) AS city_population ON country.Code = city_population.CountryCode ";

        //Act
        when(mockReportHelperClass.getSpecificPopulationReport(queryWorld, "World")).thenReturn(expectedPopulation);
        Population population = specificPopulation.ByWorld();

        //Assert
        assertEquals(expectedPopulation, population);
        verify(mockReportHelperClass).getSpecificPopulationReport(queryWorld, "World");
    }

    /**
     * Uses a mocked ReportHelper class to help simulate querying the db
     * This tests that the SpecificPopulation.ByContinent() method successfully returns a Population object after a query
     */
    @Test
    void testSpecificPopulation_ByContinent_ShouldRetrieve_PopulationInDB_ForSpecificContinent()
    {
        //Arrange
        ReportHelper mockReportHelperClass = mock(ReportHelper.class);
        SpecificPopulation specificPopulation = new SpecificPopulation(mockReportHelperClass);
        Population expectedPopulation = new Population();

        String queryContinent = "SELECT country.continent AS AreaName, COALESCE(SUM(country.population), 0) AS TotalPopulation, COALESCE(SUM(city_population.population), 0) AS PopulationCities, (COALESCE(SUM(city_population.population), 0) / COALESCE(SUM(country.population), 0) * 100) AS PopulationCityPercentage, SUM(country.population) - COALESCE(SUM(city_population.population), 0) AS PopulationOutsideCities, ((SUM(country.population) - COALESCE(SUM(city_population.population), 0)) / COALESCE(SUM(country.population), 0) * 100) AS PopulationOutsideCityPercentage " +
                "FROM country " +
                "LEFT JOIN (SELECT CountryCode, SUM(city.population) AS population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Continent = '" + TEST_CONTINENT + "' " +
                "GROUP BY CountryCode) AS city_population ON country.Code = city_population.CountryCode " +
                "WHERE country.continent = '" + TEST_CONTINENT + "' " +
                "GROUP BY country.continent";

        //Act
        when(mockReportHelperClass.getSpecificPopulationReport(queryContinent, "Continent")).thenReturn(expectedPopulation);
        Population population = specificPopulation.ByContinent(TEST_CONTINENT);

        //Assert
        assertEquals(expectedPopulation, population);
        verify(mockReportHelperClass).getSpecificPopulationReport(queryContinent, "Continent");
    }

    /**
     * Uses a mocked ReportHelper class to help simulate querying the db
     * This tests that the SpecificPopulation.ByRegion() method successfully returns a Population object after a query
     */
    @Test
    void testSpecificPopulation_ByRegion_ShouldRetrieve_PopulationInDB_ForSpecificRegion()
    {
        //Arrange
        ReportHelper mockReportHelperClass = mock(ReportHelper.class);
        SpecificPopulation specificPopulation = new SpecificPopulation(mockReportHelperClass);
        Population expectedPopulation = new Population();

        String queryRegion = "SELECT country.Region AS AreaName, COALESCE(SUM(country.population), 0) AS TotalPopulation, COALESCE(SUM(city_population.population), 0) AS PopulationCities, (COALESCE(SUM(city_population.population), 0) / COALESCE(SUM(country.population), 0) * 100) AS PopulationCityPercentage, SUM(country.population) - COALESCE(SUM(city_population.population), 0) AS PopulationOutsideCities, ((SUM(country.population) - COALESCE(SUM(city_population.population), 0)) / COALESCE(SUM(country.population), 0) * 100) AS PopulationOutsideCityPercentage " +
                "FROM country " +
                "LEFT JOIN (SELECT CountryCode, SUM(city.population) AS population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Region = '" + TEST_REGION + "' " +
                "GROUP BY CountryCode) AS city_population ON country.Code = city_population.CountryCode " +
                "WHERE country.Region = '" + TEST_REGION + "' " +
                "GROUP BY country.Region";

        //Act
        when(mockReportHelperClass.getSpecificPopulationReport(queryRegion, "Region")).thenReturn(expectedPopulation);
        Population population = specificPopulation.ByRegion(TEST_REGION);

        //Assert
        assertEquals(expectedPopulation, population);
        verify(mockReportHelperClass).getSpecificPopulationReport(queryRegion, "Region");
    }

    /**
     * Uses a mocked ReportHelper class to help simulate querying the db
     * This tests that the SpecificPopulation.ByCountry() method successfully returns a Population object after a query
     */
    @Test
    void testSpecificPopulation_ByCountry_ShouldRetrieve_PopulationInDB_ForSpecificCountry()
    {
        //Arrange
        ReportHelper mockReportHelperClass = mock(ReportHelper.class);
        SpecificPopulation specificPopulation = new SpecificPopulation(mockReportHelperClass);
        Population expectedPopulation = new Population();

        String queryCountry  = "SELECT country.Name AS AreaName, COALESCE(SUM(country.population), 0) AS TotalPopulation, COALESCE(SUM(city_population.population), 0) AS PopulationCities, (COALESCE(SUM(city_population.population), 0) / COALESCE(SUM(country.population), 0) * 100) AS PopulationCityPercentage, SUM(country.population) - COALESCE(SUM(city_population.population), 0) AS PopulationOutsideCities, ((SUM(country.population) - COALESCE(SUM(city_population.population), 0)) / COALESCE(SUM(country.population), 0) * 100) AS PopulationOutsideCityPercentage " +
                "FROM country " +
                "LEFT JOIN (SELECT CountryCode, SUM(city.population) AS population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Name = '" + TEST_COUNTRY + "' " +
                "GROUP BY CountryCode) AS city_population ON country.Code = city_population.CountryCode " +
                "WHERE country.Name = '" + TEST_COUNTRY + "' " +
                "GROUP BY country.Name";

        //Act
        when(mockReportHelperClass.getSpecificPopulationReport(queryCountry, "Country")).thenReturn(expectedPopulation);
        Population population = specificPopulation.ByCountry(TEST_COUNTRY);

        //Assert
        assertEquals(expectedPopulation, population);
        verify(mockReportHelperClass).getSpecificPopulationReport(queryCountry, "Country");
    }

    /**
     * Uses a mocked ReportHelper class to help simulate querying the db
     * This tests that the SpecificPopulation.ByDistrict() method successfully returns a Population object after a query
     */
    @Test
    void testSpecificPopulation_ByDistrict_ShouldRetrieve_PopulationInDB_ForSpecificDistrict()
    {
        //Arrange
        ReportHelper mockReportHelperClass = mock(ReportHelper.class);
        SpecificPopulation specificPopulation = new SpecificPopulation(mockReportHelperClass);
        Population expectedPopulation = new Population();

        String queryDistrict  = "SELECT city.district AS AreaName, COALESCE(SUM(city.population), 0) AS TotalPopulation " +
                "FROM city " +
                "WHERE city.district = '" + TEST_DISTRICT + "' " +
                "GROUP BY city.district";

        //Act
        when(mockReportHelperClass.getSpecificPopulationReport(queryDistrict, "District")).thenReturn(expectedPopulation);
        Population population = specificPopulation.ByDistrict(TEST_DISTRICT);

        //Assert
        assertEquals(expectedPopulation, population);
        verify(mockReportHelperClass).getSpecificPopulationReport(queryDistrict, "District");
    }

    /**
     * Uses a mocked ReportHelper class to help simulate querying the db
     * This tests that the SpecificPopulation.ByCity() method successfully returns a Population object after a query
     */
    @Test
    void testSpecificPopulation_ByCity_ShouldRetrieve_PopulationInDB_ForSpecificCity()
    {
        //Arrange
        ReportHelper mockReportHelperClass = mock(ReportHelper.class);
        SpecificPopulation specificPopulation = new SpecificPopulation(mockReportHelperClass);
        Population expectedPopulation = new Population();

        String queryCity  = "SELECT city.name AS AreaName, city.population AS TotalPopulation " +
                "FROM city " +
                "WHERE city.name = '" + TEST_CITY + "' ";

        //Act
        when(mockReportHelperClass.getSpecificPopulationReport(queryCity, "City")).thenReturn(expectedPopulation);
        Population population = specificPopulation.ByCity(TEST_CITY);

        //Assert
        assertEquals(expectedPopulation, population);
        verify(mockReportHelperClass).getSpecificPopulationReport(queryCity, "City");
    }

    //----------------------- 6. Unit tests: Languages ------------------------------------//
    /**
     * Tests that the displayLanguages() method handles null input without error
     */
    @Test
    void printLanguagesTestNull()
    {
        app.displayLanguages(null);
    }

    /**
     * Tests that the displayLanguages() method handles an empty array input without error
     */
    @Test
    void printLanguagesTestEmpty()
    {
        ArrayList<Language> languages = new ArrayList<>();
        app.displayLanguages(languages);
    }

    /**
     * Tests that the displayLanguages() method handles an array which contains a null value without error
     */
    @Test
    void printLanguagesTestContainsNull()
    {
        ArrayList<Language> languages = new ArrayList<>();
        languages.add(null);
        app.displayLanguages(languages);
    }

    /**
     * Tests that the displayLanguages() method successfully prints Language array objects to the console
     */
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

    /**
     * Uses a mocked ReportHelper class to help simulate querying the db
     * This tests that the LanguageByPopulation.ByWorld() method successfully returns Language object(s) after a query
     */
    @Test
    void testByWorld_ShouldRetrieve_LanguagesInDB()
    {
        // Arrange
        ReportHelper mockReportHelperClass = mock(ReportHelper.class);
        LanguageByPopulation languageByPopulation = new LanguageByPopulation(mockReportHelperClass);

        ArrayList<Language> expectedLanguages = new ArrayList<>();
        expectedLanguages.add(new Language());

        String queryWorld = "SELECT language as LanguageName, SUM(ROUND(country.population * (percentage/100))) as TotalLanguageSpeakers, (SUM(ROUND(country.population * (percentage/100))) / (SELECT SUM(population) from country) * 100) as WorldPercentage " +
                "From countrylanguage " +
                "JOIN country ON countrylanguage.CountryCode = country.Code " +
                "WHERE language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic') " +
                "GROUP BY language " +
                "ORDER BY TotalLanguageSpeakers DESC";
        //Act
        when(mockReportHelperClass.getLanguageReport(queryWorld)).thenReturn(expectedLanguages);
        List<Language> languages = languageByPopulation.ByWorld();

        // Assert
        assertEquals(expectedLanguages, languages);
        verify(mockReportHelperClass).getLanguageReport(queryWorld);
    }

















    //----------------------- Report Helper tests  ------------------------------------//

    /**
     * 1. Test to ensure that the getCountryReport method in the ReportHelper class is correctly fetching the country data from the database and transforming it into a list of Country objects
     */
    @Test
    void testGetCountryReport_ShouldFetchCountryData() throws Exception
    {
        // Arrange
        String countrySqlQuery = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name as CapitalName " +
                "FROM country " +
                "JOIN city ON country.Capital = city.ID " +
                "ORDER BY country.Population DESC";

        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(countrySqlQuery)).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getString("Code")).thenReturn("USA");
        ReportHelper reportHelper = new ReportHelper(mockConnection);

        // Act
        ArrayList<Country> countries = reportHelper.getCountryReport(countrySqlQuery);

        // Assert
        assertNotNull(countries);
        Country country = countries.get(0);
        assertEquals("USA", country.code);
    }

    /**
     * 2. Test to ensure that the getCountryReport method in the ReportHelper class correctly catches a SQL exception error when querying the db
     */
    @Test
    void testGetCountryReport_ThrowsSQLException() throws Exception
    {
        // Arrange
        String countrySqlQuery = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name as CapitalName " +
                "FROM country " +
                "JOIN city ON country.Capital = city.ID " +
                "ORDER BY country.Population DESC";

        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);

        when(mockStatement.executeQuery(countrySqlQuery)).thenThrow(new SQLException("Failed to get country report"));

        ReportHelper reportHelper = new ReportHelper(mockConnection);

        // Act
        ArrayList<Country> result = reportHelper.getCountryReport(countrySqlQuery);

        // Assert
        assertNull(result);
    }

    /**
     * 3. Test to ensure that the getCountryReport method in the ReportHelper class correctly catches a SQL exception error when there is an error in the results set
     */
    @Test
    void testGetCountryReportWithSQLException_InResultSet() throws Exception
    {
        //Arrange
        ResultSet mockResultSet = mock(ResultSet.class);
        Statement mockStatement = mock(Statement.class);
        Connection mockConnection = mock(Connection.class);

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getString(anyString())).thenThrow(new SQLException("Data could not be accessed"));

        //Act
        ReportHelper reportHelper = new ReportHelper(mockConnection);
        ArrayList<Country> countries = reportHelper.getCountryReport("SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name as CapitalName " +
                "FROM country " +
                "JOIN city ON country.Capital = city.ID " +
                "ORDER BY country.Population DESC");

        //Assert
        assertFalse(countries.isEmpty());
    }

    /**
     * Test to ensure that the getCityReport method in the ReportHelper class is correctly fetching the City data from the database and transforming it into a list of City objects
     */
    @Test
    void testGetCityReport_ShouldFetchCityData() throws Exception
    {
        // Arrange
        String citySqlQuery = "SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "ORDER BY city.Population DESC";

        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(citySqlQuery)).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getString("Name")).thenReturn("London");
        ReportHelper reportHelper = new ReportHelper(mockConnection);

        // Act
        ArrayList<City> cities = reportHelper.getCityReport(citySqlQuery);

        // Assert
        assertNotNull(cities);
        City city = cities.get(0);
        assertEquals("London", city.name);
    }

    /**
     * Test to ensure that the getCityReport method in the ReportHelper class correctly catches a SQL exception error when querying the db
     */
    @Test
    void testGetCityReport_ThrowsSQLException() throws Exception
    {
        // Arrange
        String citySqlQuery = "SELECT city.Name, country.Name as CountryName, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "ORDER BY city.Population DESC";

        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);

        when(mockStatement.executeQuery(citySqlQuery)).thenThrow(new SQLException("Failed to get city report"));

        ReportHelper reportHelper = new ReportHelper(mockConnection);

        // Act
        ArrayList<City> result = reportHelper.getCityReport(citySqlQuery);

        // Assert
        assertNull(result);
    }

    /**
     * Test to ensure that the getCityReport method in the ReportHelper class correctly catches a SQL exception error when there is an error in the results set
     */
    @Test
    void testGetCityReportWithSQLException_InResultSet() throws Exception
    {
        // Arrange
        ResultSet mockResultSet = mock(ResultSet.class);
        Statement mockStatement = mock(Statement.class);
        Connection mockConnection = mock(Connection.class);

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getString(anyString())).thenThrow(new SQLException("Data could not be accessed"));

        ReportHelper reportHelper = new ReportHelper(mockConnection);
        // Act
        ArrayList<City> cities = reportHelper.getCityReport("SELECT city.Name, city.CountryCode, city.District, city.Population FROM city ORDER BY city.Population DESC");

        // Assert
        assertFalse(cities.isEmpty());
    }

    /**
     * Test to ensure that the getPopulationReport method in the ReportHelper class is correctly fetching the Population data from the database and transforming it into a list of Population objects
     */
    @Test
    void testGetPopulationReport_ShouldFetchPopulationData() throws Exception
    {
        // Arrange
        String populationSqlQuery = "SELECT country.continent AS AreaName, COALESCE(SUM(country.population), 0) AS TotalPopulation, COALESCE(SUM(city_population.population), 0) AS PopulationCities, (COALESCE(SUM(city_population.population), 0) / COALESCE(SUM(country.population), 0) * 100) AS PopulationCityPercentage, SUM(country.population) - COALESCE(SUM(city_population.population), 0) AS PopulationOutsideCities, ((SUM(country.population) - COALESCE(SUM(city_population.population), 0)) / COALESCE(SUM(country.population), 0) * 100) AS PopulationOutsideCityPercentage " +
                "FROM country " +
                "LEFT JOIN (SELECT CountryCode, SUM(population) AS population " +
                "FROM city " +
                "GROUP BY CountryCode) AS city_population ON country.Code = city_population.CountryCode " +
                "GROUP BY country.continent ";

        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(populationSqlQuery)).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getString("AreaName")).thenReturn("Europe");
        ReportHelper reportHelper = new ReportHelper(mockConnection);

        // Act
        ArrayList<Population> populations = reportHelper.getPopulationReport(populationSqlQuery);

        // Assert
        assertNotNull(populations);
        Population population = populations.get(0);
        assertEquals("Europe", population.areaName);
    }

    /**
     * Test to ensure that the getPopulationReport method in the ReportHelper class correctly catches a SQL exception error when querying the db
     */
    @Test
    void testGetPopulationReport_ThrowsSQLException() throws Exception
    {
        // Arrange
        String populationSqlQuery = "SELECT country.continent AS AreaName, COALESCE(SUM(country.population), 0) AS TotalPopulation, COALESCE(SUM(city_population.population), 0) AS PopulationCities, (COALESCE(SUM(city_population.population), 0) / COALESCE(SUM(country.population), 0) * 100) AS PopulationCityPercentage, SUM(country.population) - COALESCE(SUM(city_population.population), 0) AS PopulationOutsideCities, ((SUM(country.population) - COALESCE(SUM(city_population.population), 0)) / COALESCE(SUM(country.population), 0) * 100) AS PopulationOutsideCityPercentage " +
                "FROM country " +
                "LEFT JOIN (SELECT CountryCode, SUM(population) AS population " +
                "FROM city " +
                "GROUP BY CountryCode) AS city_population ON country.Code = city_population.CountryCode " +
                "GROUP BY country.continent ";

        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(populationSqlQuery)).thenThrow(new SQLException("Failed to get population report"));

        ReportHelper reportHelper = new ReportHelper(mockConnection);

        // Act
        ArrayList<Population> result = reportHelper.getPopulationReport(populationSqlQuery);

        // Assert
        assertNull(result);
    }

    /**
     * Test to ensure that the getPopulationReport method in the ReportHelper class correctly catches a SQL exception error when there is an error in the results set
     */
    @Test
    void testGetPopulationReportWithSQLException_InResultSet() throws Exception
    {
        // Arrange
        ResultSet mockResultSet = mock(ResultSet.class);
        Statement mockStatement = mock(Statement.class);
        Connection mockConnection = mock(Connection.class);

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getString(anyString())).thenThrow(new SQLException("Data could not be accessed"));

        ReportHelper reportHelper = new ReportHelper(mockConnection);
        // Act
        ArrayList<Population> populations = reportHelper.getPopulationReport("SELECT country.continent AS AreaName, COALESCE(SUM(country.population), 0) AS TotalPopulation, COALESCE(SUM(city_population.population), 0) AS PopulationCities, (COALESCE(SUM(city_population.population), 0) / COALESCE(SUM(country.population), 0) * 100) AS PopulationCityPercentage, SUM(country.population) - COALESCE(SUM(city_population.population), 0) AS PopulationOutsideCities, ((SUM(country.population) - COALESCE(SUM(city_population.population), 0)) / COALESCE(SUM(country.population), 0) * 100) AS PopulationOutsideCityPercentage " +
                "FROM country " +
                "LEFT JOIN (SELECT CountryCode, SUM(population) AS population " +
                "FROM city " +
                "GROUP BY CountryCode) AS city_population ON country.Code = city_population.CountryCode " +
                "GROUP BY country.continent ");

        // Assert
        assertFalse(populations.isEmpty());
    }

    /**
     * Test to ensure that the getSpecificPopulationReport method in the ReportHelper class is correctly fetching the Population data from the database and transforming it into a Population object
     */
    @Test
    void testGetSpecificPopulationReport_ShouldFetchPopulationData() throws Exception
    {
        // Arrange
        String populationSqlQuery = "SELECT country.continent AS AreaName, COALESCE(SUM(country.population), 0) AS TotalPopulation, COALESCE(SUM(city_population.population), 0) AS PopulationCities, (COALESCE(SUM(city_population.population), 0) / COALESCE(SUM(country.population), 0) * 100) AS PopulationCityPercentage, SUM(country.population) - COALESCE(SUM(city_population.population), 0) AS PopulationOutsideCities, ((SUM(country.population) - COALESCE(SUM(city_population.population), 0)) / COALESCE(SUM(country.population), 0) * 100) AS PopulationOutsideCityPercentage " +
                "FROM country " +
                "LEFT JOIN (SELECT CountryCode, SUM(city.population) AS population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Continent = '" + TEST_CONTINENT + "' " +
                "GROUP BY CountryCode) AS city_population ON country.Code = city_population.CountryCode " +
                "WHERE country.continent = '" + TEST_CONTINENT + "' " +
                "GROUP BY country.continent";

        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(populationSqlQuery)).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getString("AreaName")).thenReturn("Africa");
        ReportHelper reportHelper = new ReportHelper(mockConnection);

        // Act
        Population population = reportHelper.getSpecificPopulationReport(populationSqlQuery, "Continent");

        // Assert
        assertNotNull(population);
        assertEquals("Africa", population.areaName);
    }

    /**
     * Test to ensure that the getPopulationReport method in the ReportHelper class correctly catches a SQL exception error when querying the db
     */
    @Test
    void testGetSpecificPopulationReport_ThrowsSQLException() throws Exception
    {
        // Arrange
        String populationSqlQuery = "SELECT country.continent AS AreaName, COALESCE(SUM(country.population), 0) AS TotalPopulation, COALESCE(SUM(city_population.population), 0) AS PopulationCities, (COALESCE(SUM(city_population.population), 0) / COALESCE(SUM(country.population), 0) * 100) AS PopulationCityPercentage, SUM(country.population) - COALESCE(SUM(city_population.population), 0) AS PopulationOutsideCities, ((SUM(country.population) - COALESCE(SUM(city_population.population), 0)) / COALESCE(SUM(country.population), 0) * 100) AS PopulationOutsideCityPercentage " +
                "FROM country " +
                "LEFT JOIN (SELECT CountryCode, SUM(city.population) AS population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Continent = '" + TEST_CONTINENT + "' " +
                "GROUP BY CountryCode) AS city_population ON country.Code = city_population.CountryCode " +
                "WHERE country.continent = '" + TEST_CONTINENT + "' " +
                "GROUP BY country.continent";

        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(populationSqlQuery)).thenThrow(new SQLException("Failed to get population report"));

        ReportHelper reportHelper = new ReportHelper(mockConnection);

        // Act
        Population result = reportHelper.getSpecificPopulationReport(populationSqlQuery, "Continent");

        // Assert
        assertNull(result);
    }

    /**
     * Test to ensure that the getLanguageReport method in the ReportHelper class is correctly fetching the Language data from the database and transforming it into a list of Language objects
     */
    @Test
    void testGetLanguageReport_ShouldFetchLanguageData() throws Exception
    {
        // Arrange
        String languageSqlQuery = "SELECT language as LanguageName, SUM(ROUND(country.population * (percentage/100))) as TotalLanguageSpeakers, (SUM(ROUND(country.population * (percentage/100))) / (SELECT SUM(population) from country) * 100) as WorldPercentage " +
                "From countrylanguage " +
                "JOIN country ON countrylanguage.CountryCode = country.Code " +
                "WHERE language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic') " +
                "GROUP BY language " +
                "ORDER BY TotalLanguageSpeakers DESC";

        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(languageSqlQuery)).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getString("LanguageName")).thenReturn("Chinese");
        ReportHelper reportHelper = new ReportHelper(mockConnection);

        // Act
        ArrayList<Language> languages = reportHelper.getLanguageReport(languageSqlQuery);

        // Assert
        assertNotNull(languages);
        Language language = languages.get(0);
        assertEquals("Chinese", language.languageName);
    }

    /**
     * Test to ensure that the getLanguageReport method in the ReportHelper class correctly catches a SQL exception error when querying the db
     */
    @Test
    void testGetLanguageReport_ThrowsSQLException() throws Exception
    {
        // Arrange
        String languageSqlQuery  = "SELECT language as LanguageName, SUM(ROUND(country.population * (percentage/100))) as TotalLanguageSpeakers, (SUM(ROUND(country.population * (percentage/100))) / (SELECT SUM(population) from country) * 100) as WorldPercentage " +
                "From countrylanguage " +
                "JOIN country ON countrylanguage.CountryCode = country.Code " +
                "WHERE language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic') " +
                "GROUP BY language " +
                "ORDER BY TotalLanguageSpeakers DESC";

        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);

        when(mockStatement.executeQuery(languageSqlQuery)).thenThrow(new SQLException("Failed to get language report"));

        ReportHelper reportHelper = new ReportHelper(mockConnection);

        // Act
        ArrayList<Language> result = reportHelper.getLanguageReport(languageSqlQuery);

        // Assert
        assertNull(result);
    }

    /**
     * Test to ensure that the getLanguageReport method in the ReportHelper class correctly catches a SQL exception error when there is an error in the results set
     */
    @Test
    void testGetLanguageReportWithSQLException_InResultSet() throws Exception
    {
        //Arrange
        ResultSet mockResultSet = mock(ResultSet.class);
        Statement mockStatement = mock(Statement.class);
        Connection mockConnection = mock(Connection.class);

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getString(anyString())).thenThrow(new SQLException("Data could not be accessed"));

        // Act
        ReportHelper reportHelper = new ReportHelper(mockConnection);
        ArrayList<Language> languages = reportHelper.getLanguageReport("SELECT language as LanguageName, SUM(ROUND(country.population * (percentage/100))) as TotalLanguageSpeakers, (SUM(ROUND(country.population * (percentage/100))) / (SELECT SUM(population) from country) * 100) as WorldPercentage " +
                "From countrylanguage " +
                "JOIN country ON countrylanguage.CountryCode = country.Code " +
                "WHERE language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic') " +
                "GROUP BY language " +
                "ORDER BY TotalLanguageSpeakers DESC");

        //Assert
        assertFalse(languages.isEmpty());
    }

    /**
     * Test which mocks user input to ensure the Index.getUserOption() method takes the correct mock input and runs the resulting action
     */
    @Test
    void testGetUserOptionValidInput()
    {
        //Arrange
        App mockApp = mock(App.class);
        Scanner mockScanner = new Scanner(new ByteArrayInputStream("5\n".getBytes()));
        Index index = new Index();

        //Act
        Runnable action = index.getUserOption(mockApp, "1", mockScanner);

        //Assert
        assertNotNull(action);
        action.run();
    }

    /**
     * Tests that the Index.displayOptions() method returns the correct console output to display the user's options
     */
    @Test
    public void testDisplayOptions()
    {

        Index index = new Index();
        index.displayOptions();

        String expectedOutput = index.generateMenuOptionsText() + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    /**
     * Tests that the Index.GetN(scanner) method correctly takes the user's numeric input
     */
    @Test
    public void testGetN()
    {
        String simulatedUserInput = "5\n";
        ByteArrayInputStream in = new ByteArrayInputStream(simulatedUserInput.getBytes());
        Scanner scanner = new Scanner(in);

        Index index = new Index();
        int result = index.getN(scanner);

        assertEquals(5, result);
        assertEquals("How many top N would you like to see?:" + System.lineSeparator(), outContent.toString());
    }
}
