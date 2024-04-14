package com.napier.sem;

import com.napier.sem.Features.*;
import com.napier.sem.Helpers.ReportHelper;
import com.napier.sem.Models.City;
import com.napier.sem.Models.Country;
import com.napier.sem.Models.Language;
import com.napier.sem.Models.Population;
import com.napier.sem.Utils.DatabaseUtil;
import com.napier.sem.View.Index;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class AppIntegrationTest
{
    /**
     * Static fields declaration for all the report classes
     */
    static App app;                                   // Defines a new instance of the App class
    static AllCountries allCountries;                 // Handles country related reports
    static AllCities allCities;                        // Handles city related reports
    static TopNCountries topNCountries;                // Handles reports for top N countries by population
    static AllCapitalCities allCapitalCities;         // Handles capital city related reports
    static TopNCities topNCities;                     // Handles reports for top N cities by population
    static TopNCapitalCities topNCapitalCities;       // Handles reports for top N capital cities by population
    static AllPopulations allPopulations;             // Handles population related reporting
    static SpecificPopulation specificPopulation;     // Handles specific population queries and reporting
    static LanguageByPopulation languageByPopulation; // Handles language distribution by population


    /**
     *  Constants used as parameters for the test queries
     */
    static final String TEST_CONTINENT = "Africa";         //Defines a static continent name to help test methods
    static final String TEST_REGION = "Caribbean";         //Defines a static region name to help test methods
    static final String TEST_COUNTRY = "Spain";            //Defines a static country name to help test methods
    static final String TEST_DISTRICT = "Buenos Aires";    //Defines a static district name to help test methods
    static final String TEST_CITY = "Seoul";                //Defines a static city name to help test methods
    static final int N = 5;                                 //Defines a static value for "N", mimicking user input, to help test methods


    private final PrintStream originalOut = System.out;      //Defines a print stream, to help test methods which print to the console.
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream(); //Defines a byte array output stream, to help test methods which print to the console.


    /**
     * Sets up database connections and initializes report helpers and class instances before all tests.
     */
    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060", 30000);
        ReportHelper reportHelper = new ReportHelper(DatabaseUtil.getConnection());
        allCountries = new AllCountries(reportHelper);
        allCities = new AllCities(reportHelper);
        topNCountries = new TopNCountries(reportHelper);
        allCapitalCities = new AllCapitalCities(reportHelper);
        topNCities = new TopNCities(reportHelper);
        topNCapitalCities = new TopNCapitalCities(reportHelper);
        allPopulations = new AllPopulations(reportHelper);
        specificPopulation = new SpecificPopulation(reportHelper);
        languageByPopulation = new LanguageByPopulation(reportHelper);
    }

    //---------------------------------- 1. All Countries Population integration tests -----------------------------------------//

    /**
     * This test verifies that the `ByWorld` method correctly retrieves a list of all countries sorted by population in descending order.
     * This test ensures that:
     * - The list of countries is not null, which would indicate the data was successfully retrieved.
     * - The list of countries is not empty, which indicates the data is fetches correctly from the db
     * - The first country in the list has the highest population, which would indicate the correct descending sorting order.
     */
    @Test
    void testByWorld_ShouldProvide_CountriesFromLargestPopulationFirst()
    {
        //Arrange
        ArrayList<Country> countries = allCountries.ByWorld();

        //Act
        Country largestPopulationCountry = countries.get(0);

        //Assert
        assertNotNull(countries);
        assertFalse(countries.isEmpty());
        assertTrue(countries.stream().noneMatch(country -> country.population > largestPopulationCountry.population),"The first country should have the highest population");
    }

    /**
     * This test that the `ByContinent` method correctly retrieves a list of all countries from a specified continent sorted by population in descending order.
     * This test ensures that:
     * - The list of countries is not null, which would indicate the data was successfully retrieved.
     * - All countries in the list belong to the specified continent, confirming correct filtering by continent.
     * - The list of countries is not empty, which indicates the data is fetches correctly from the db
     * - The first country in the list has the highest population, which would indicate the correct descending sorting order.
     */
    @Test
    void testByContinent_ShouldProvide_CountriesLargestPopulationFirst()
    {
        //Arrange
        ArrayList<Country> countries = allCountries.ByContinent(TEST_CONTINENT);

        //Act
        Country largestPopulationCountry = countries.get(0);

        //Assert
        assertNotNull(countries);
        assertFalse(countries.isEmpty());
        assertTrue(countries.stream().allMatch(c -> c.continent.equals(TEST_CONTINENT)), "All countries should be from the continent: " + TEST_CONTINENT);
        assertTrue(countries.stream().noneMatch(country -> country.population > largestPopulationCountry.population), "The first country should have the highest population");
    }

    /**
     * This test verifies that the `ByRegion` method correctly retrieves a list of all countries from a specified region sorted by population in descending order.
     * This test ensures that:
     * - The list of countries is not null, which would indicate the data was successfully retrieved.
     * - The list of countries is not empty, which indicates the data is fetches correctly from the db
     * - All countries in the list are part of the specified region, confirming correct filtering by region.
     * - The first country in the list has the highest population, which would indicate the correct descending sorting order.
     */
    @Test
    void testByRegion_ShouldProvide_CountriesLargestPopulationFirst()
    {
        //Arrange
        ArrayList<Country> countries = allCountries.ByRegion(TEST_REGION);

        //Act
        Country largestPopulationCountry = countries.get(0);

        //Assert
        assertNotNull(countries);
        assertFalse(countries.isEmpty());
        assertTrue(countries.stream().allMatch(c -> c.region.equals(TEST_REGION)), "All countries should be from the region: " + TEST_REGION);
        assertTrue(countries.stream().noneMatch(country -> country.population > largestPopulationCountry.population), "The first country should have the highest population");
    }


    //---------------------------------- 2. All Cities Population integration tests -----------------------------------------//

    /**
     * Verifies that the `ByWorld` method correctly retrieves a list of all cities sorted by population in descending order.
     * This test ensures that:
     * - The list of cities is not null, which would indicate the data was successfully retrieved.
     * - The list of cities is not empty, which indicates the data is fetches correctly from the db
     * - The first city in the list has the highest population, which would indicate the correct descending sorting order.
     */
    @Test
    void testByWorld_ShouldProvide_CitiesFromLargestPopulationFirst()
    {
        //Arrange
        ArrayList<City> cities = allCities.ByWorld();

        //Act
        City largestPopulationCity = cities.get(0);

        //Assert
        assertNotNull(cities);
        assertFalse(cities.isEmpty());
        assertTrue(cities.stream().noneMatch(city -> city.population > largestPopulationCity.population), "The first city should have the highest population");
    }

    /**
     * Verifies that the `ByContinent` method correctly retrieves a list of all cities from a specified continent sorted by population in descending order.
     * This test ensures that:
     * - The list of cities is not null, which would indicate the data was successfully retrieved.
     * - The list of cities is not empty, which indicates the data is fetches correctly from the db
     * - The first city in the list has the highest population, which would indicate the correct descending sorting order.
     */
    @Test
    void testByContinent_ShouldProvide_CitiesFromLargestPopulationFirst()
    {
        //Arrange
        ArrayList<City> cities = allCities.ByContinent(TEST_CONTINENT);

        //Act
        City largestPopulationCity = cities.get(0);

        //Assert
        assertNotNull(cities);
        assertFalse(cities.isEmpty());
        assertTrue(cities.stream().noneMatch(city -> city.population > largestPopulationCity.population), "The first city should have the highest population");
    }

    /**
     * Verifies that the `ByRegion` method correctly retrieves a list of all cities from a specified region sorted by population in descending order.
     * This test ensures that:
     * - The list of cities is not null, which would indicate the data was successfully retrieved.
     * - The list of cities is not empty, which indicates the data is fetches correctly from the db
     * - The first city in the list has the highest population, which would indicate the correct descending sorting order.
     */
    @Test
    void testByRegion_ShouldProvide_CitiesFromLargestPopulationFirst()
    {
        //Arrange
        ArrayList<City> cities = allCities.ByRegion(TEST_REGION);

        //Act
        City largestPopulationCity = cities.get(0);

        //Assert
        assertNotNull(cities);
        assertFalse(cities.isEmpty());
        assertTrue(cities.stream().noneMatch(city -> city.population > largestPopulationCity.population), "The first city should; have the highest population");
    }

    /**
     * Verifies that the `ByCountry` method correctly retrieves a list of all cities from a specified country sorted by population in descending order.
     * This test ensures that:
     * - The list of cities is not null, which would indicate the data was successfully retrieved.
     * - The list of cities is not empty, which indicates the data is fetches correctly from the db
     * - All cities in the list belong to the specified country, confirming correct filtering by country.
     * - The first city in the list has the highest population, which would indicate the correct descending sorting order.
     */
    @Test
    void testByCountry_ShouldProvide_CitiesFromLargestPopulationFirst()
    {
        //Arrange
        ArrayList<City> cities = allCities.ByCountry(TEST_COUNTRY);

        //Act
        City largestPopulationCity = cities.get(0);

        //Assert
        assertNotNull(cities);
        assertFalse(cities.isEmpty());
        assertTrue(cities.stream().allMatch(city -> city.countryCode.equals(TEST_COUNTRY)),"All cities should be from the country: " + TEST_COUNTRY);
        assertTrue(cities.stream().noneMatch(city -> city.population > largestPopulationCity.population), "The first city should have the highest population");
    }

    /**
     * Verifies that the `ByDistrict` method correctly retrieves a list of all cities from a specified district sorted by population in descending order.
     * This test ensures that:
     * - The list of cities is not null, which would indicate the data was successfully retrieved.
     * - The list of cities is not empty, which indicates the data is fetches correctly from the db.
     * - All cities in the list belong to the specified district, confirming correct filtering by district.
     * - The first city in the list has the highest population, which would indicate the correct descending sorting order.
     */
    @Test
    void testByDistrict_ShouldProvide_CitiesFromLargestPopulationFirst()
    {
        //Arrange
        ArrayList<City> cities = allCities.ByDistrict(TEST_DISTRICT);

        //Act
        City largestPopulationCity = cities.get(0);

        //Assert
        assertNotNull(cities);
        assertFalse(cities.isEmpty());
        assertTrue(cities.stream().allMatch(city -> city.district.equals(TEST_DISTRICT)),"All cities should be from the district: " + TEST_DISTRICT);
        assertTrue(cities.stream().noneMatch(city -> city.population > largestPopulationCity.population), "The first city should have the highest population");
    }


    //---------------------------------- 3. All Capital Cities Population integration tests -----------------------------------------//

    /**
     * Verifies that the `ByWorld` method correctly retrieves a list of all capital cities sorted by population in descending order.
     * This test ensures that:
     * - The list of capital cities is not null or empty, which would indicate the data was successfully retrieved.
     * - The first capital city in the list has the highest population, which would indicate the correct descending sorting order.
     */
    @Test
    void testByWorld_ShouldProvide_CapitalCitiesFromLargestPopulationFirst()
    {
        //Arrange
        ArrayList<City> CapitalCities = allCapitalCities.ByWorld();

        //Act
        City largestPopulationCapitalCity = CapitalCities.get(0);

        //Assert
        assertNotNull(CapitalCities);
        assertFalse(CapitalCities.isEmpty());
        assertTrue(CapitalCities.stream().noneMatch(city -> city.population > largestPopulationCapitalCity.population), "The first capital city should have the highest population");
    }

    /**
     * Verifies that the `ByContinent` method correctly retrieves a list of all capital cities from a specified continent sorted by population in descending order.
     * This test ensures that:
     * - The list of capital cities is not null or empty, which would indicate the data was successfully retrieved.
     * - The first capital city in the list has the highest population, which would indicate the correct descending sorting order.
     */
    @Test
    void testByContinent_ShouldProvide_CapitalCitiesFromLargestPopulationFirst()
    {
        //Arrange
        ArrayList<City> CapitalCities = allCapitalCities.ByContinent(TEST_CONTINENT);

        //Act
        City largestPopulationCapitalCity = CapitalCities.get(0);

        //Assert
        assertNotNull(CapitalCities);
        assertFalse(CapitalCities.isEmpty());
        assertTrue(CapitalCities.stream().noneMatch(city -> city.population > largestPopulationCapitalCity.population), "The first capital city should have the highest population");
    }

    /**
     * Verifies that the `ByRegion` method correctly retrieves a list of all capital cities from a specified region sorted by population in descending order.
     * This test ensures that:
     * - The list of capital cities is not null or empty, which would indicate the data was successfully retrieved.
     * - The first capital city in the list has the highest population, which would indicate the correct descending sorting order.
     */
    @Test
    void testByRegion_ShouldProvide_CapitalCitiesFromLargestPopulationFirst()
    {
        //Arrange
        ArrayList<City> CapitalCities = allCapitalCities.ByRegion(TEST_REGION);

        //Act
        City largestPopulationCapitalCity = CapitalCities.get(0);

        //Assert
        assertNotNull(CapitalCities);
        assertFalse(CapitalCities.isEmpty());
        assertTrue(CapitalCities.stream().noneMatch(city -> city.population > largestPopulationCapitalCity.population),"The first capital city should have the highest population");
    }



    //---------------------------------- 4. Top N Populated countries integration tests-----------------------------------------//

    /**
     * Verifies that the `ByWorld` method in `TopNCountries` class correctly retrieves the top N countries globally, sorted by population in descending order.
     * This test ensures that:
     * - The list of top countries is not null or empty, and contains exactly N entries, confirming correct data retrieval and N limit
     * - The first country in the list has the highest population, confirming the correct sorting order.
     */
    @Test
    void testByWorld_TopNPopulatedCountries_ShouldProvide_CountriesFromLargestPopulationFirst()
    {
        //Arrange
        ArrayList<Country> topCountries = topNCountries.ByWorld(N);

        //Act
        Country largestPopulationCountry = topCountries.get(0);

        //Assert
        assertNotNull(topCountries);
        assertFalse(topCountries.isEmpty());
        assertEquals(N, topCountries.size(), "List should contain exactly " + N + " countries");
        assertTrue(topCountries.stream().noneMatch(country -> country.population > largestPopulationCountry.population), "The first country should have the highest population");
    }

    /**
     * Verifies that the `ByContinent` method in `TopNCountries` class correctly retrieves the top N countries from a specified continent, sorted by population in descending order.
     * This test ensures that:
     * - The list of top countries is not null and not empty, and contains exactly N entries, confirming correct data retrieval and N limit
     * - All countries in the list belong to the specified continent, verifying proper filtering.
     * - The first country in the list has the highest population, confirming correct sorting.
     */
    @Test
    void testByContinent_TopNPopulatedCountries_ShouldProvide_CountriesFromLargestPopulationFirst()
    {
        //Arrange
        ArrayList<Country> topCountries = topNCountries.ByContinent(N, TEST_CONTINENT);

        //Act
        Country largestPopulationCountry = topCountries.get(0);

        //Assert
        assertNotNull(topCountries);
        assertFalse(topCountries.isEmpty());
        assertEquals(N, topCountries.size());
        assertTrue(topCountries.stream().allMatch(country -> country.continent.equals(TEST_CONTINENT)),"All countries should be from the continent: " + TEST_CONTINENT);
        assertTrue(topCountries.stream().noneMatch(country -> country.population > largestPopulationCountry.population), "The first country should have the highest population");
    }

    /**
     * Verifies that the `ByRegion` method in `TopNCountries` class correctly retrieves the top N countries from a specified region, sorted by population in descending order.
     * This test ensures that:
     * - The list of top countries is not null or empty, and contains exactly N entries, confirming correct data retrieval and N limit
     * - The first country in the list has the highest population, validating the sort order.
     */
    @Test
    void testByRegion_TopNPopulatedCountries_ShouldProvide_CountriesFromLargestPopulationFirst()
    {
        //Arrange
        ArrayList<Country> topCountries = topNCountries.ByRegion(N, TEST_REGION);

        //Act
        Country largestPopulationCountry = topCountries.get(0);

        //Assert
        assertNotNull(topCountries);
        assertFalse(topCountries.isEmpty());
        assertEquals(5, topCountries.size());
        assertTrue(topCountries.stream().allMatch(country -> country.region.equals(TEST_REGION)), "All countries should be from the region: " + TEST_REGION);
        assertTrue(topCountries.stream().noneMatch(country -> country.population > largestPopulationCountry.population), "The first country should have the highest population");
    }


    //---------------------------------- 5. Top N Populated cities integration tests-----------------------------------------//
    /**
     * Verifies that the `ByWorld` method in `TopNCities` class correctly retrieves the top N cities globally, sorted by population in descending order.
     * This test ensures that:
     * - The list of top cities is not nul or empty, and contains exactly N entries, confirming correct data retrieval and N limit
     * - The first city in the list has the highest population, confirming the correct sorting order.
     */
    @Test
    void testByWorld_TopNCities_ShouldProvide_CitiesFromLargestPopulationFirst()
    {
        //Arrange
        ArrayList<City> topCities = topNCities.ByWorld(N);

        //Act
        City largestPopulationCity = topCities.get(0);

        //Assert
        assertNotNull(topCities);
        assertFalse(topCities.isEmpty());
        assertEquals(N, topCities.size());
        assertTrue(topCities.stream().noneMatch(city -> city.population > largestPopulationCity.population),"The first city should have the highest population");
    }

    /**
     * Verifies that the `ByContinent` method in `TopNCities` class retrieves the top N cities from a specified continent, sorted by population in descending order.
     * This test ensures that:
     * - The list of top cities is not null and not empty, nd contains exactly N entries, confirming correct data retrieval and N limit
     * - The first city in the list has the highest population, validating the sorting order.
     */
    @Test
    void testByContinent_TopNCities_ShouldProvide_CitiesFromLargestPopulationFirst()
    {
        //Arrange
        ArrayList<City> topCities = topNCities.ByContinent(N,TEST_CONTINENT);

        //Act
        City largestPopulationCity = topCities.get(0);

        //Assert
        assertNotNull(topCities);
        assertFalse(topCities.isEmpty());
        assertEquals(N, topCities.size());
        assertTrue(topCities.stream().noneMatch(city -> city.population > largestPopulationCity.population),"The first city should have the highest population");
    }

    /**
     * Verifies that the `ByRegion` method in `TopNCities` class retrieves the top N cities from a specified region, sorted by population in descending order.
     * This test ensures that:
     * - The list of top cities is not null or empty, and contains exactly N entries, confirming correct data retrieval and N limit
     * - The first city in the list has the highest population, validating the sorting order.
     */
    @Test
    void testByRegion_TopNCities_ShouldProvide_CitiesFromLargestPopulationFirst()
    {
        //Arrange
        ArrayList<City> topCities = topNCities.ByRegion(N,TEST_REGION);

        //Act
        City largestPopulationCity = topCities.get(0);

        //Assert
        assertNotNull(topCities);
        assertFalse(topCities.isEmpty());
        assertEquals(N, topCities.size());

        assertTrue(topCities.stream().noneMatch(city -> city.population > largestPopulationCity.population), "The first city should have the highest population");
    }

    /**
     * Verifies that the `ByCountry` method in `TopNCities` class retrieves the top N cities from a specified country, sorted by population in descending order.
     * This test ensures that:
     * - The list of top cities is not null and not empty, and contains exactly N entries, confirming correct data retrieval and N limit
     * - All cities in the list belong to the specified country, verifying proper filtering.
     * - The first city in the list has the highest population, validating the sort order.
     */
    @Test
    void testByCountry_TopNCities_ShouldProvide_CitiesFromLargestPopulationFirst()
    {
        //Arrange
        ArrayList<City> topCities = topNCities.ByCountry(N,TEST_COUNTRY);

        //Act
        City largestPopulationCity = topCities.get(0);

        //Assert
        assertNotNull(topCities);
        assertFalse(topCities.isEmpty());
        assertEquals(N, topCities.size());
        assertTrue(topCities.stream().allMatch(city -> city.countryCode.equals(TEST_COUNTRY)), "All cities should be from the country: " + TEST_COUNTRY);
        assertTrue(topCities.stream().noneMatch(city -> city.population > largestPopulationCity.population), "The first city should have the highest population");
    }

    /**
     * Verifies that the `ByDistrict` method in `TopNCities` class retrieves the top N cities from a specified district, sorted by population in descending order.
     * This test ensures that:
     * - The list of top cities is not null, and contains exactly N entries, confirming correct data retrieval and N limit
     * - The list is not empty and all cities belong to the specified district, verifying correct filtering.
     * - The first city in the list has the highest population, validating the sorting order.
     */
    @Test
    void testByDistrict_TopNCities_ShouldProvide_CitiesFromLargestPopulationFirst()
    {
        //Arrange
        ArrayList<City> topCities = topNCities.ByDistrict(N,TEST_DISTRICT);

        //Act
        City largestPopulationCity = topCities.get(0);

        //Assert
        assertNotNull(topCities);
        assertFalse(topCities.isEmpty());
        assertEquals(N, topCities.size());
        assertTrue(topCities.stream().allMatch(city -> city.district.equals(TEST_DISTRICT)),"All cities should be from the district: " + TEST_DISTRICT);
        assertTrue(topCities.stream().noneMatch(city -> city.population > largestPopulationCity.population), "The first city should have the highest population");
    }

    //---------------------------------- 6. Top N Populated Capital cities integration tests-----------------------------------------//
    /**
     * Verifies that the `ByWorld` method in `TopNCapitalCities` class correctly retrieves the top N capital cities globally, sorted by population in descending order.
     * This test ensures that:
     * - The list of top capital cities is not null or empty, and contains exactly N entries, confirming correct data retrieval and N limit
     * - The first capital city in the list has the highest population, confirming the correct sorting order.
     */
    @Test
    void testByWorld_TopNCapitalCities_ShouldProvide_CitiesFromLargestPopulationFirst()
    {
        //Arrange
        ArrayList<City> topCapitalCities = topNCapitalCities.ByWorld(N);

        //Act
        City largestPopulationCity = topCapitalCities.get(0);

        //Assert
        assertNotNull(topCapitalCities);
        assertFalse(topCapitalCities.isEmpty());
        assertEquals(N, topCapitalCities.size());
        assertTrue(topCapitalCities.stream().noneMatch(city -> city.population > largestPopulationCity.population), "The first capital city should have the highest population");
    }

    /**
     * Verifies that the `ByContinent` method in `TopNCapitalCities` class retrieves the top N capital cities from a specified continent, sorted by population in descending order.
     * This test ensures that:
     * - The list of top capital cities is not null and not empty and contains exactly N entries, confirming correct data retrieval and N limit
     * - The first capital city in the list has the highest population, validating the sorting order.
     */
    @Test
    void testByContinent_TopNCapitalCities_ShouldProvide_CitiesFromLargestPopulationFirst()
    {
        //Arrange
        ArrayList<City> topCapitalCities = topNCapitalCities.ByContinent(N,TEST_CONTINENT);

        //act
        City largestPopulationCity = topCapitalCities.get(0);

        //Assert
        assertNotNull(topCapitalCities);
        assertFalse(topCapitalCities.isEmpty());
        assertEquals(N, topCapitalCities.size());
        assertTrue(topCapitalCities.stream().noneMatch(city -> city.population > largestPopulationCity.population), "The first capital city should have the highest population");
    }

    /**
     * Verifies that the `ByRegion` method in `TopNCapitalCities` class retrieves the top N capital cities from a specified region, sorted by population in descending order.
     * This test ensures that:
     * - The list of top capital cities is not null or empty and contains exactly N entries, confirming correct data retrieval and N limit.
     * - The first capital city in the list has the highest population, validating the sorting order.
     */
    @Test
    void testByRegion_TopNCapitalCities_ShouldProvide_CitiesFromLargestPopulationFirst()
    {
        //Arrange
        ArrayList<City> topCapitalCities = topNCapitalCities.ByRegion(N,TEST_REGION);

        //Act
        City largestPopulationCity = topCapitalCities.get(0);

        //Assert
        assertNotNull(topCapitalCities);
        assertFalse(topCapitalCities.isEmpty());
        assertEquals(N, topCapitalCities.size());
        assertTrue(topCapitalCities.stream().noneMatch(city -> city.population > largestPopulationCity.population), "The first capital city should have the highest population");
    }

    //-----------------------7. Population Within and Outwith Cities Integration Tests---------------------------//
    /**
     * Verifies that the `ByContinent` method in `AllPopulations` class correctly retrieves population data for each continent.
     * This test ensures that:
     * - The list of population data is not null, confirming successful data retrieval.
     * - The list is not empty, indicating that population data for at least one continent was fetched.
     * - At least one continent has a population greater than 0, ensuring that the data includes actual population figures.
     * - The population percentage in cities for each country is within the expected range between 0 and 100.
     */
    @Test
    void testPopulationByContinent_ShouldProvideCorrectPopulationData()
    {
        //Arrange & Act
        ArrayList<Population> populations = allPopulations.ByContinent();

        //Assert
        assertNotNull(populations);
        assertFalse(populations.isEmpty());
        assertTrue(populations.stream().anyMatch(pop -> pop.population > 0));
        assertTrue(populations.stream().anyMatch(pop -> pop.populationCitiesPercentage <= 100 && pop.populationCitiesPercentage >= 0));
    }

    /**
     * Verifies that the `ByRegion` method in `AllPopulations` class retrieves population data for each region.
     * This test checks:
     * - The list of population data is not null or empty, confirming successful data retrieval.
     * - At least one region has a population greater than 0, ensuring that the data includes actual population figures.
     * - The population percentage in cities for each country is within the expected range between 0 and 100.
     */
    @Test
    void testPopulationByRegion_ShouldProvideCorrectPopulationData()
    {
        //Arrange & Act
        ArrayList<Population> populations = allPopulations.ByRegion();

        //Assert
        assertNotNull(populations);
        assertFalse(populations.isEmpty());
        assertTrue(populations.stream().anyMatch(pop -> pop.population > 0));
        assertTrue(populations.stream().anyMatch(pop -> pop.populationCitiesPercentage <= 100 && pop.populationCitiesPercentage >= 0));
    }

    /**
     * Verifies that the `ByCountry` method in `AllPopulations` class retrieves population data for each country.
     * This test ensures that:
     * - The list of population data is not null or empty, confirming successful data retrieval.
     * - At least one country has a population greater than 0, ensuring that the data includes actual population figures.
     * - The population percentage in cities for each country is within the expected range between 0 and 100.
     */
    @Test
    void testPopulationByCountry_ShouldProvideCorrectPopulationData()
    {
        //Arrange & Act
        ArrayList<Population> populations = allPopulations.ByCountry();

        //Assert
        assertNotNull(populations);
        assertFalse(populations.isEmpty());
        assertTrue(populations.stream().anyMatch(pop -> pop.population > 0));
        assertTrue(populations.stream().anyMatch(pop -> pop.populationCitiesPercentage <= 100 && pop.populationCitiesPercentage >= 0));
    }

    // --------------------- 8. Specific population different areas integration tests --------------------------------------------//

    /**
     * Verifies that the `ByWorld` method in `SpecificPopulation` class correctly retrieves overall world population data.
     * This test ensures that:
     * - The retrieved world population data is not null, confirming successful data retrieval.
     * - The area name is correctly set to "World".
     * - All population values (total population, population in cities, population outside cities) are greater than zero, indicating valid data retrieval
     * - Both city and outside city population percentages are within logical bounds (0 to 100%).
     */
    @Test
    void testByWorld_ShouldProvideCorrectPopulationData()
    {
        //Arrange
        Population worldPopulation = specificPopulation.ByWorld();

        //Assert
        assertNotNull(worldPopulation);
        assertEquals("World", worldPopulation.areaName);
        assertTrue(worldPopulation.population > 0);
        assertTrue(worldPopulation.populationCities > 0);
        assertTrue(worldPopulation.populationCitiesPercentage <= 100 && worldPopulation.populationCitiesPercentage >= 0);
        assertTrue(worldPopulation.populationOutsideCities > 0);
        assertTrue(worldPopulation.populationOutsideCitiesPercentage <= 100 && worldPopulation.populationOutsideCitiesPercentage >= 0);
    }

    /**
     * Verifies that the `ByContinent` method in `SpecificPopulation` class retrieves population data for a specified continent, ensuring accurate demographic metrics.
     * This test checks:
     * - The population object is not null and correctly named after the tested continent.
     * - All population are positive values, indicating valid data retrieval
     * - Both city and outside city population percentages are within logical bounds (0 to 100%).
     */
    @Test
    void testByContinent_ShouldProvideCorrectPopulationData()
    {
        //Arrange
        Population africaPopulation = specificPopulation.ByContinent(TEST_CONTINENT);

        //Assert
        assertNotNull(africaPopulation);
        assertEquals(TEST_CONTINENT, africaPopulation.areaName);
        assertTrue(africaPopulation.population > 0);
        assertTrue(africaPopulation.populationCities > 0);
        assertTrue(africaPopulation.populationCitiesPercentage <= 100 && africaPopulation.populationCitiesPercentage >= 0);
        assertTrue(africaPopulation.populationOutsideCities > 0);
        assertTrue(africaPopulation.populationOutsideCitiesPercentage <= 100 && africaPopulation.populationOutsideCitiesPercentage >= 0);
    }

    /**
     * Tests the `ByRegion` method of the `SpecificPopulation` class to verify that it accurately retrieves population data for a specified region.
     * Ensures:
     * - Non-null population data with correct region name.
     * - Positive values for total, urban, and rural populations.
     * - Population percentages within logical boundaries (0-100%).
     */
    @Test
    void testByRegion_ShouldProvideCorrectPopulationData()
    {
        //Arrange
        Population caribbeanPopulation = specificPopulation.ByRegion(TEST_REGION);

        //Assert
        assertNotNull(caribbeanPopulation);
        assertEquals(TEST_REGION, caribbeanPopulation.areaName);
        assertTrue(caribbeanPopulation.population > 0);
        assertTrue(caribbeanPopulation.populationCities > 0);
        assertTrue(caribbeanPopulation.populationCitiesPercentage <= 100 && caribbeanPopulation.populationCitiesPercentage >= 0);
        assertTrue(caribbeanPopulation.populationOutsideCities > 0);
        assertTrue(caribbeanPopulation.populationOutsideCitiesPercentage <= 100 && caribbeanPopulation.populationOutsideCitiesPercentage >= 0);
    }

    /**
     * Verifies that the `ByCountry` method in `SpecificPopulation` class retrieves accurate population data for a specific country.
     * This test checks:
     * - Non-null population data with the country correctly identified.
     * - Positive population ensuring valid data.
     * - Both city and outside city population percentages are within logical bounds (0 to 100%).
     */
    @Test
    void testByCountry_ShouldProvideCorrectPopulationData()
    {
        //Arrange
        Population spainPopulation = specificPopulation.ByCountry(TEST_COUNTRY);

        //Assert
        assertNotNull(spainPopulation);
        assertEquals(TEST_COUNTRY, spainPopulation.areaName);
        assertTrue(spainPopulation.population > 0);
        assertTrue(spainPopulation.populationCities > 0);
        assertTrue(spainPopulation.populationCitiesPercentage <= 100 && spainPopulation.populationCitiesPercentage >= 0);
        assertTrue(spainPopulation.populationOutsideCities > 0);
        assertTrue(spainPopulation.populationOutsideCitiesPercentage <= 100 && spainPopulation.populationOutsideCitiesPercentage >= 0);
    }

    /**
     * Tests the accuracy of population data retrieval by district using the `ByDistrict` method of the `SpecificPopulation` class.
     * Confirms:
     * - Non-null return of population data.
     * - Correct district naming in the data.
     * - Total population greater than zero, indicating valid data retrieval
     */
    @Test
    void testByDistrict_ShouldProvideCorrectPopulationData()
    {
        //Arrange
        Population buenosAiresPopulation = specificPopulation.ByDistrict(TEST_DISTRICT);

        //Assert
        assertNotNull(buenosAiresPopulation);
        assertEquals(TEST_DISTRICT, buenosAiresPopulation.areaName);
        assertTrue(buenosAiresPopulation.population > 0);
    }

    /**
     * Verifies that population data retrieval for a specific city is accurate when using the `ByCity` method in the `SpecificPopulation` class.
     * This test ensures:
     * - Non-null population data which is correctly named after the city.
     * - Total population figure is positive, indicating valid data retrieval.
     */
    @Test
    void testByCity_ShouldProvideCorrectPopulationData()
    {
        //Arrange
        Population cityPopulation = specificPopulation.ByCity(TEST_CITY);

        //Assert
        assertNotNull(cityPopulation);
        assertEquals(TEST_CITY, cityPopulation.areaName);
        assertTrue(cityPopulation.population > 0);
    }

    //-------------------- 8. Spoken languages integration tests -----------------------------------------------------------//
    /**
     * Verifies that the `ByWorld` method in `LanguageByPopulation` class correctly retrieves a list of languages in the world, sorted by the total number of speakers in descending order.
     * This test ensures that:
     * - The list of languages is not null or empty, confirming successful data retrieval.
     * - The language with the most speakers appears first in the list, validating the sorting order.
     * - All other languages in the list have fewer or an equal number of speakers compared to the first language, ensuring correct descending sort order across the entire list.
     */
    @Test
    void testByWorld_ShouldProvide_LanguagesFromLargestAmountOfSpeakers()
    {
        //Arrange
        ArrayList<Language> languages = languageByPopulation.ByWorld();

        //Act
        Language languageWithMostSpeakers = languages.get(0);


        //Assert
        assertNotNull(languages);
        assertFalse(languages.isEmpty());
        assertTrue(languages.stream().allMatch(language -> language.totalSpeakers <= languageWithMostSpeakers.totalSpeakers), "All languages should have fewer or equal speakers than the first language");
    }


    //---------------------- Tests App ---------------------------------------------------//

    /**
     * Prepares the system to capture text output during tests.
     * This method changes the normal route of text that usually goes to the console (System.out), so instead it gets stored.
     * This allows us to see exactly what would have been printed out during the tests without actually using the console
     */
    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Puts everything back to normal after tests are done. This method changes the text output back, making it go back to the usual console (System.out) again,
     * just like it did before we started testing. This ensures that after tests, everything work like it usually does, and text goes where it's supposed to go
     */
    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }


    //----Tests display countries ------------//
    /**
     * This test verifies that the displayCountries method with a null input handles null lists appropriately.
     * Ensures that the method outputs a specific message indicating no countries are available to display.
     */
    @Test
    void testDisplayCountriesWithNull()
    {
        app.displayCountries(null);
        String capturedOutput = outContent.toString();
        assertContains(capturedOutput, "No countries");
    }

    /**
     * This test verifies that the displayCountries method with a list of countries including null elements is correctly displaying the country details.
     */
    @Test
    void testDisplayCountries()
    {
        //Arrange
        ArrayList<Country> countries = new ArrayList<>();
        Country country = new Country();
        country.code = "CHN";
        country.name = "China";
        countries.add(country);
        countries.add(null);

        //Act
        app.displayCountries(countries);

        //Assert
        String capturedOutput = outContent.toString();
        assertContains(capturedOutput, "CHN");
        assertContains(capturedOutput, "China");
    }

    //----Tests display cities ------------//
    /**
     * This test verifies that the displayCities method with a null input outputs a message indicating no cities are available.
     */
    @Test
    void testDisplayCitiesWithNull()
    {
        app.displayCities(null);
        String capturedOutput = outContent.toString();
        assertContains(capturedOutput, "No cities");
    }

    /**
     * This test verifies that the displayCities method with a list contains valid cities and null elements.
     * Ensures the method correctly displays city details and handles null values without errors.
     */
    @Test
    void testDisplayCities()
    {
        //Arrange
        ArrayList<City> cities = new ArrayList<>();
        City city = new City();
        city.name = "Seoul";
        city.countryCode = "South Korea";
        cities.add(city);
        cities.add(null);

        //Act
        app.displayCities(cities);

        //Assert
        String capturedOutput = outContent.toString();
        assertContains(capturedOutput, "Seoul");
        assertContains(capturedOutput, "South Korea");
    }

    //----Tests display capital cities ------------//
    /**
     * This test verifies that the displayCapitalCities method with a null input checks for correct message output indicating no capital cities available.
     */
    @Test
    void testDisplayCapitalCitiesWithNull()
    {
        app.displayCapitalCities(null);
        String capturedOutput = outContent.toString();
        assertContains(capturedOutput, "No capital cities");
    }

    /**
     * This test verifies that the displayCapitalCities method can display details of capital cities from a list that also contains null elements.
     * Confirms that the method outputs city and country codes correctly and handles nulls gracefully.
     */
    @Test
    void testDisplayCapitalCities()
    {
        //Arrange
        ArrayList<City> capitalCities = new ArrayList<>();
        City city = new City();
        city.name = "Jakarta";
        city.countryCode = "Indonesia";
        capitalCities.add(city);
        capitalCities.add(null);

        //Act
        app.displayCapitalCities(capitalCities);

        //Assert
        String capturedOutput = outContent.toString();
        assertContains(capturedOutput, "Jakarta");
        assertContains(capturedOutput, "Indonesia");
    }

    //----Tests display populations ------------//
    /**
     * This test verifies that the displayPopulations method with a null input is correctly being handled.
     * This test checks that the appropriate message "No populations" is displayed, indicating that no data is available to present.
     */
    @Test
    void testDisplayPopulationsWithNull()
    {
        app.displayPopulations(null);
        String capturedOutput = outContent.toString();
        assertContains(capturedOutput, "No populations");
    }

    /**
     * This test verifies the displayPopulations method with a list of populations including null elements to confirm it displays correct population details.
     * Ensures that the application outputs the area name for non-null population entries and handles null values within the list.
     */
    @Test
    void testDisplayPopulations()
    {
        //Arrange
        ArrayList<Population> populations = new ArrayList<>();
        Population population = new Population();
        population.areaName = "World";
        populations.add(population);
        populations.add(null);

        //Act
        app.displayPopulations(populations);

        //Assert
        String capturedOutput = outContent.toString();
        assertContains(capturedOutput, "World");
    }

    //----Tests display specific populations ------------//

    /**
     * This test verifies that the displaySpecificPopulation method with a null population object handles null inputs correctly.
     * Ensures that the method outputs a specific message "No population" when no population data is available to display.
     */
    @Test
    void testDisplaySpecificPopulationWithNull()
    {
        app.displaySpecificPopulation(null, "City");
        String capturedOutput = outContent.toString();
        assertContains(capturedOutput, "No population");
    }

    /**
     * This test verifies that displaySpecificPopulation method displays details of a specified population object accurately, and it confirms that the population area name is correctly shown in the output
     */
    @Test
     void testDisplaySpecificPopulation()
    {
        //Arrange
        Population population = new Population();
        population.areaName = "Seoul";

        //Act
        app.displaySpecificPopulation(population, "City");

        //Assert
        String capturedOutput = outContent.toString();
        assertContains(capturedOutput, "Seoul");
    }

    /**
     * This test verifies that the displaySpecificPopulation method for a continent accurately displays the relevant population information.
     * It confirms that the application correctly outputs the area name
     */
    @Test
    void testDisplaySpecificPopulationNonCityOrDistrict()
    {
        //Arrange
        Population population = new Population();
        population.areaName = "Africa";

        //Act
        app.displaySpecificPopulation(population, "Continent");

        //Assert
        String capturedOutput = outContent.toString();
        assertContains(capturedOutput, "Africa");
    }

    //-------- Test languages --------------//

    /**
     * This test verifies that the displayLanguages method correctly handles the case where no languages are provided (null), making sure the output is a
     * specific message of "No languages".
     */
    @Test
    void testDisplayLanguagesWithNull()
    {
        app.displayLanguages(null);
        String capturedOutput = outContent.toString();
        assertContains(capturedOutput, "No languages");
    }

    /**
     * This test verifies that displayLanguages method correctly displays language information.
     * It does this by checking that the method handles a list containing both populated Language objects and null values.
     * The test captures the output stream to evaluate the behaviour of the method and ensures that the languages has valid entries.
     */
    @Test
    void testDisplayLanguages()
    {
        //Arrange
        ArrayList<Language> languages = new ArrayList<>();
        Language chineseLanguage = new Language();
        chineseLanguage.languageName = "Chinese";

        languages.add(chineseLanguage);
        languages.add(null);

        //Act
        app.displayLanguages(languages);

        //Assert
        String capturedOutput = outContent.toString();
        assertContains(capturedOutput, "Chinese");
    }

    /**
     * This is a helper method that will help assert that specified content is present in the captured output during tests and contains the expected strings.
     * @param capturedOutput Output captured from System.out after redirecting the standard output stream.
     * @param expectedContent Expected content that is found within the captured output.
     */
    private void assertContains(String capturedOutput, String expectedContent)
    {
        assertTrue(capturedOutput.contains(expectedContent), String.format("Output should contain '%s'. Captured output: %s", expectedContent, capturedOutput));
    }


    //---------------------- Exception tests in ReportHelper ----------------------//
    /**
     * This test verifies the getCountryReport method's error handling capabilities when dealing with an invalid SQL query.
     */
    @Test
    void testGetCountryReport_ExceptionThrowDueToWithInvalidQuery_ShouldDealWithException()
    {
        ReportHelper reportHelper = new ReportHelper(DatabaseUtil.getConnection());
        String queryNotValidThrowsException = "SELECT * FROM NonExistingTableToFail";

        reportHelper.getCountryReport(queryNotValidThrowsException);

        String output = outContent.toString();
        assertTrue(output.contains("Failed to get country report"));
    }

    /**
     * This test verifies the getCityReport method's error handling capabilities when dealing with an invalid SQL query.
     */
    @Test
    void testGetCityReport_ExceptionThrowDueToWithInvalidQuery_ShouldDealWithException()
    {
        ReportHelper reportHelper = new ReportHelper(DatabaseUtil.getConnection());
        String queryNotValidThrowsException = "SELECT * FROM NonExistingTableToFail";

        reportHelper.getCityReport(queryNotValidThrowsException);
        String output = outContent.toString();
        assertTrue(output.contains("Failed to get city report"));
    }

    /**
     * This test verifies the getPopulationReport method's error handling capabilities when dealing with an invalid SQL query.
     */
    @Test
    void testGetPopulationReport_ExceptionThrowDueToWithInvalidQuery_ShouldDealWithException()
    {
        ReportHelper reportHelper = new ReportHelper(DatabaseUtil.getConnection());
        String queryNotValidThrowsException = "SELECT * FROM NonExistingTableToFail";

        reportHelper.getPopulationReport(queryNotValidThrowsException);

        String output = outContent.toString();
        assertTrue(output.contains("Failed to get population report"));
    }

    /**
     * This test verifies the getSpecificPopulationReport method's error handling capabilities when dealing with an invalid SQL query.
     */
    @Test
    void testGetSpecificPopulationReport_ExceptionThrowDueToWithInvalidQuery_ShouldDealWithException()
    {
        ReportHelper reportHelper = new ReportHelper(DatabaseUtil.getConnection());
        String queryNotValidThrowsException = "SELECT * FROM NonExistingTableToFail";

        reportHelper.getSpecificPopulationReport(queryNotValidThrowsException, "World");

        String output = outContent.toString();
        assertTrue(output.contains("Failed to get population report"));
    }

    /**
     * This test verifies the getSpecificPopulationReport method's error handling capabilities when dealing with an invalid SQL query.
     */
    @Test
    void testGetLanguageReport_ExceptionThrowDueToWithInvalidQuery_ShouldDealWithException()
    {
        ReportHelper reportHelper = new ReportHelper(DatabaseUtil.getConnection());
        String queryNotValidThrowsException = "SELECT * FROM NonExistingTableToFail";

        reportHelper.getLanguageReport(queryNotValidThrowsException);

        String output = outContent.toString();
        assertTrue(output.contains("Failed to get languages report"));
    }

    //----------------------------------Database util integration test ----------------------------------------------//
    /**
     * This test verifies that the disconnect method in the DatabaseUtil properly closes the db connection. It does this by checking the connection is active
     * by asserting NotNull before trying to disconnect from the database.
     * After it is disconnected, it verifies that an SQLException is thrown, which would indicate that the connection is indeed closed.
     * This is validated by attempting to execute a simple query which would fail if the connection is closed.
     */
    @Test
    void testDisconnectShouldCloseConnection()
    {
        assertNotNull(DatabaseUtil.getConnection(), "Connection should not be null before disconnect.");
        DatabaseUtil.disconnect();

        SQLException exception = assertThrows(SQLException.class, () ->
                DatabaseUtil.getConnection().createStatement().executeQuery("SELECT 1"), "Expected SQLException to be thrown if the connection is closed.");

        assertTrue(exception.getMessage().contains("closed"));
    }

    /**
     * This method cleans up database connections after all tests have been executed, ensuring a clean start during reconnection
     */
    @AfterAll
    static void tearDown()
    {
        DatabaseUtil.connect("localhost:33060", 30000);
        DatabaseUtil.disconnect();
    }


    //---------------------------------- Index tests --------------------------------------//

    /**
     * This test verifies that the getN method in the Index class returns the correct value. Sets standard input to a specific value, simulating user input,
     * then checks if the returned value matches the expected value.
     */
    @Test
    void testGetN_ReturnsCorrectValue()
    {
        String input = "5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Scanner scanner = new Scanner(System.in);
        Index index = new Index();

        assertEquals(5, index.getN(scanner));

        System.setIn(System.in);
    }

    /**
     * This test verifies that the generateMenuOptionsText method in the Index class generates menu text containing the correct options
     */
    @Test
    void testGenerateMenuOptionsText_ShouldContainCorrectMenuOptions()
    {
        Index index = new Index();
        String menuText = index.generateMenuOptionsText();

        assertTrue(menuText.contains("Welcome to the report system"));
        assertTrue(menuText.contains("All the countries in the world by population in descending order"));
    }

    /**
     *  This test verifies that the displayOptions method in the Index class outputs text containing the correct menu options which is hardcoded in the Index class
     */
    @Test
    void testDisplayOptions_OutputContainsCorrectMenuOptions()
    {
        //Arrange
        System.setOut(new PrintStream(outContent));
        Index index = new Index();

        //Act
        index.displayOptions();

        //Assert
        String output = outContent.toString();
        assertTrue(output.contains("Welcome to the report system"));
        System.setOut(System.out);
    }

    /**
     * This test verifies that the getUserOption method in the Index class returns the correct action for a valid user choice from the menu selection
     */
    @Test
    void testGetUserOption_ValidChoice_ReturnsCorrectAction()
    {
        Scanner scanner = new Scanner(System.in);
        Index index = new Index();

        Runnable action = index.getUserOption(app, "1", scanner);
        action.run();
        assertNotNull(action, "Action should not be null for valid choice");
    }

    /**
     * This test verifies that the getUserOption method in the Index class returns the default action for an invalid user choice from the menu selection
     */
    @Test
    void testGetUserOption_InvalidChoice_ReturnsDefaultAction()
    {
        Scanner scanner = new Scanner(System.in);
        Index index = new Index();

        Runnable action = index.getUserOption(app, "87", scanner);
        action.run();
        assertNotNull(action, "Action should not be null, even for invalid choice.");
    }

    /**
     * This test verifies that the connect method in the App class successfully establish a connection with the database
     */
    @Test
    void testDatabaseConnection()
    {
        app.connect("localhost:33060", 30000);
        assertNotNull(DatabaseUtil.getConnection(), "The database connection should be established.");
    }
}
