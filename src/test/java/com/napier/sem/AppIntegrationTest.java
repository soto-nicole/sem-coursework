package com.napier.sem;

import com.napier.sem.Features.*;
import com.napier.sem.Helpers.ReportHelper;
import com.napier.sem.Models.City;
import com.napier.sem.Models.Country;
import com.napier.sem.Models.Population;
import com.napier.sem.Utils.DatabaseUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static App app;
    static AllCountries allCountries;
    static AllCities allCities;
    static TopNCountries topNCountries;
    static AllCapitalCities allCapitalCities;
    static TopNCities topNCities;
    static TopNCapitalCities topNCapitalCities;
    static AllPopulations allPopulations;



    static final String TEST_CONTINENT = "Africa";
    static final String TEST_REGION = "Caribbean";
    static final String TEST_COUNTRY = "Spain";
    static final String TEST_DISTRICT = "Buenos Aires";
    static final int N = 5;

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
    }

    //-----------------------Population Within and Outwith Cities Integration Tests---------------------------//
    @Test
    void testPopulationByContinent_ShouldProvideCorrectPopulationData()
    {
        ArrayList<Population> populations = allPopulations.ByContinent();
        assertNotNull(populations);
        assertFalse(populations.isEmpty());

        Population asia = null;
        for (Population population : populations)
        {
            if ("Asia".equals(population.areaName))
            {
                asia = population;
                break;
            }
        }

        assertNotNull(asia);
        assertEquals(3705025700L, asia.population);
        assertEquals(697604103L, asia.populationCities);
        assertEquals(18.8286, asia.populationCitiesPercentage, 0.001);
        assertEquals(3007421597L, asia.populationOutsideCities);
        assertEquals(81.1714, asia.populationOutsideCitiesPercentage, 0.001);
    }

    @Test
    void testPopulationByRegion_ShouldProvideCorrectPopulationData()
    {
        ArrayList<Population> populations = allPopulations.ByRegion();
        assertNotNull(populations);
        assertFalse(populations.isEmpty());

        Population caribbean = null;
        for (Population population : populations)
        {
            if ("Caribbean".equals(population.areaName))
            {
                caribbean = population;
                break;
            }
        }

        assertNotNull(caribbean);
        assertEquals(38140000, caribbean.population);
        assertEquals(11067550, caribbean.populationCities);
        assertEquals(29.0182, caribbean.populationCitiesPercentage, 0.001);
        assertEquals(27072450, caribbean.populationOutsideCities);
        assertEquals(70.9818, caribbean.populationOutsideCitiesPercentage, 0.001);
    }

    @Test
    void testPopulationByCountry_ShouldProvideCorrectPopulationData()
    {
        ArrayList<Population> populations = allPopulations.ByCountry();
        Population angola = null;
        for (Population population : populations)
        {
            if ("Angola".equals(population.areaName))
            {
                angola = population;
                break;
            }
        }

        assertNotNull(angola);
        assertEquals(12878000, angola.population);
        assertEquals(2561600, angola.populationCities);
        assertEquals(19.8913, angola.populationCitiesPercentage, 0.001);
        assertEquals(10316400, angola.populationOutsideCities);
        assertEquals(80.1087, angola.populationOutsideCitiesPercentage, 0.001);
    }


    //---------------------------------- Top N Populated Capital cities-----------------------------------------//
    @Test
    void testByWorld_TopNCapitalCities_ShouldProvide_CitiesFromLargestPopulationFirst()
    {
        ArrayList<City> topCapitalCities = topNCapitalCities.ByWorld(N);
        assertNotNull(topCapitalCities);
        assertFalse(topCapitalCities.isEmpty());
        assertEquals(N, topCapitalCities.size());

        assertEquals("Seoul", topCapitalCities.get(0).name);
        assertEquals(9981619, topCapitalCities.get(0).population);
        assertEquals("South Korea", topCapitalCities.get(0).countryCode);
    }

    @Test
    void testByContinent_TopNCapitalCities_ShouldProvide_CitiesFromLargestPopulationFirst()
    {
        ArrayList<City> topCapitalCities = topNCapitalCities.ByContinent(N,TEST_CONTINENT);
        assertNotNull(topCapitalCities);
        assertFalse(topCapitalCities.isEmpty());
        assertEquals(N, topCapitalCities.size());

        assertEquals("Cairo", topCapitalCities.get(0).name);
        assertEquals(6789479, topCapitalCities.get(0).population);
        assertEquals("Egypt", topCapitalCities.get(0).countryCode);
    }

    @Test
    void testByRegion_TopNCapitalCities_ShouldProvide_CitiesFromLargestPopulationFirst()
    {
        ArrayList<City> topCapitalCities = topNCapitalCities.ByRegion(N,TEST_REGION);
        assertNotNull(topCapitalCities);
        assertFalse(topCapitalCities.isEmpty());
        assertEquals(N, topCapitalCities.size());

        assertEquals("La Habana", topCapitalCities.get(0).name);
        assertEquals(2256000, topCapitalCities.get(0).population);
        assertEquals("Cuba", topCapitalCities.get(0).countryCode);
    }

    //---------------------------------- Top N Populated cities-----------------------------------------//
    @Test
    void testByWorld_TopNCities_ShouldProvide_CitiesFromLargestPopulationFirst()
    {
        ArrayList<City> topCities = topNCities.ByWorld(N);
        assertNotNull(topCities);
        assertFalse(topCities.isEmpty());
        assertEquals(N, topCities.size());

        assertEquals("Mumbai (Bombay)", topCities.get(0).name);
        assertEquals("Maharashtra", topCities.get(0).district);
        assertEquals(10500000, topCities.get(0).population);
        assertEquals("India", topCities.get(0).countryCode);
    }

    @Test
    void testByContinent_TopNCities_ShouldProvide_CitiesFromLargestPopulationFirst()
    {
        ArrayList<City> topCities = topNCities.ByContinent(N,TEST_CONTINENT);
        assertNotNull(topCities);
        assertFalse(topCities.isEmpty());
        assertEquals(N, topCities.size());

        assertEquals("Cairo", topCities.get(0).name);
        assertEquals("Kairo", topCities.get(0).district);
        assertEquals(6789479, topCities.get(0).population);
        assertEquals("Egypt", topCities.get(0).countryCode);
    }

    @Test
    void testByRegion_TopNCities_ShouldProvide_CitiesFromLargestPopulationFirst()
    {
        ArrayList<City> topCities = topNCities.ByRegion(N,TEST_REGION);
        assertNotNull(topCities);
        assertFalse(topCities.isEmpty());
        assertEquals(N, topCities.size());

        assertEquals("La Habana", topCities.get(0).name);
        assertEquals("La Habana", topCities.get(0).district);
        assertEquals(2256000, topCities.get(0).population);
        assertEquals("Cuba", topCities.get(0).countryCode);
    }

    @Test
    void testByCountry_TopNCities_ShouldProvide_CitiesFromLargestPopulationFirst()
    {
        ArrayList<City> topCities = topNCities.ByCountry(N,TEST_COUNTRY);
        assertNotNull(topCities);
        assertFalse(topCities.isEmpty());
        assertEquals(N, topCities.size());

        assertEquals("Madrid", topCities.get(0).name);
        assertEquals("Madrid", topCities.get(0).district);
        assertEquals(2879052, topCities.get(0).population);
        assertEquals("Spain", topCities.get(0).countryCode);
    }

    @Test
    void testByDistrict_TopNCities_ShouldProvide_CitiesFromLargestPopulationFirst()
    {
        ArrayList<City> topCities = topNCities.ByDistrict(N,TEST_DISTRICT);
        assertNotNull(topCities);
        assertFalse(topCities.isEmpty());
        assertEquals(N, topCities.size());

        assertEquals("La Matanza", topCities.get(0).name);
        assertEquals("Buenos Aires", topCities.get(0).district);
        assertEquals(1266461, topCities.get(0).population);
        assertEquals("Argentina", topCities.get(0).countryCode);
    }
    //---------------------------------- Top N Populated countries-----------------------------------------//

    @Test
    void testByWorld_TopNPopulatedCountries_ShouldProvide_CountriesFromLargestPopulationFirst()
    {
        ArrayList<Country> topCountries = topNCountries.ByWorld(N);
        assertNotNull(topCountries);
        assertFalse(topCountries.isEmpty());
        assertEquals(5, topCountries.size());

        assertEquals("China", topCountries.get(0).name);
        assertEquals(1277558000, topCountries.get(0).population);
        assertEquals("Brazil", topCountries.get(4).name);
        assertEquals(170115000, topCountries.get(4).population);
    }

    @Test
    void testByContinent_TopNPopulatedCountries_ShouldProvide_CountriesFromLargestPopulationFirst()
    {
        ArrayList<Country> topCountries = topNCountries.ByContinent(N, TEST_CONTINENT);
        assertNotNull(topCountries);
        assertFalse(topCountries.isEmpty());
        assertEquals(N, topCountries.size());

        assertEquals("Nigeria", topCountries.get(0).name);
        assertEquals(111506000, topCountries.get(0).population);
        assertEquals("South Africa", topCountries.get(4).name);
        assertEquals(40377000, topCountries.get(4).population);
    }

    @Test
    void testByRegion_TopNPopulatedCountries_ShouldProvide_CountriesFromLargestPopulationFirst()
    {
        ArrayList<Country> topCountries = topNCountries.ByRegion(N, TEST_REGION);
        assertNotNull(topCountries);
        assertFalse(topCountries.isEmpty());
        assertEquals(5, topCountries.size());

        assertEquals("Cuba", topCountries.get(0).name);
        assertEquals(11201000, topCountries.get(0).population);
        assertEquals("Jamaica", topCountries.get(4).name);
        assertEquals(2583000, topCountries.get(4).population);
    }


    //---------------------------------- All Capital Cities Population -----------------------------------------//

    @Test
    void testByWorld_ShouldProvide_CapitalCitiesFromLargestPopulationFirst()
    {
        ArrayList<City> CapitalCities = allCapitalCities.ByWorld();
        assertNotNull(CapitalCities);
        assertFalse(CapitalCities.isEmpty());

        City largestPopulationCapitalCity = CapitalCities.get(0);
        assertEquals("Seoul", largestPopulationCapitalCity.name);
        assertEquals(9981619, largestPopulationCapitalCity.population);
        assertEquals("South Korea", largestPopulationCapitalCity.countryCode);
    }

    @Test
    void testByContinent_ShouldProvide_CapitalCitiesFromLargestPopulationFirst()
    {
        ArrayList<City> CapitalCities = allCapitalCities.ByContinent(TEST_CONTINENT);
        assertNotNull(CapitalCities);
        assertFalse(CapitalCities.isEmpty());

        City largestPopulationCapitalCity = CapitalCities.get(0);
        assertEquals("Cairo", largestPopulationCapitalCity.name);
        assertEquals(6789479, largestPopulationCapitalCity.population);
        assertEquals("Egypt", largestPopulationCapitalCity.countryCode);
    }

    @Test
    void testByRegion_ShouldProvide_CapitalCitiesFromLargestPopulationFirst()
    {
        ArrayList<City> CapitalCities = allCapitalCities.ByRegion(TEST_REGION);
        assertNotNull(CapitalCities);
        assertFalse(CapitalCities.isEmpty());

        City largestPopulationCapitalCity = CapitalCities.get(0);
        assertEquals("La Habana", largestPopulationCapitalCity.name);
        assertEquals(2256000, largestPopulationCapitalCity.population);
        assertEquals("Cuba", largestPopulationCapitalCity.countryCode);
    }

    //---------------------------------- All Cities Population -----------------------------------------//
    @Test
    void testByWorld_ShouldProvide_CitiesFromLargestPopulationFirst()
    {
        ArrayList<City> cities = allCities.ByWorld();
        assertNotNull(cities);
        assertFalse(cities.isEmpty());

        City largestPopulationCity = cities.get(0);
        assertEquals("Mumbai (Bombay)", largestPopulationCity.name);
        assertEquals("Maharashtra", largestPopulationCity.district);
        assertEquals(10500000, largestPopulationCity.population);
        assertEquals("India", largestPopulationCity.countryCode);
    }

    @Test
    void testByContinent_ShouldProvide_CitiesFromLargestPopulationFirst()
    {
        ArrayList<City> cities = allCities.ByContinent(TEST_CONTINENT);
        assertNotNull(cities);

        City largestPopulationCity = cities.get(0);
        assertEquals("Cairo", largestPopulationCity.name);
        assertEquals("Kairo", largestPopulationCity.district);
        assertEquals(6789479, largestPopulationCity.population);
        assertEquals("Egypt", largestPopulationCity.countryCode);
    }

    @Test
    void testByRegion_ShouldProvide_CitiesFromLargestPopulationFirst()
    {
        ArrayList<City> cities = allCities.ByRegion(TEST_REGION);
        assertNotNull(cities);
        assertFalse(cities.isEmpty());

        City largestPopulationCity = cities.get(0);

        assertEquals("La Habana", largestPopulationCity.name);
        assertEquals("La Habana", largestPopulationCity.district);
        assertEquals("Cuba", largestPopulationCity.countryCode);
        assertEquals(2256000, largestPopulationCity.population);
    }

    @Test
    void testByCountry_ShouldProvide_CitiesFromLargestPopulationFirst()
    {
        ArrayList<City> cities = allCities.ByCountry(TEST_COUNTRY);
        assertNotNull(cities);
        assertFalse(cities.isEmpty());

        City largestPopulationCity = cities.get(0);

        assertEquals("Madrid", largestPopulationCity.name);
        assertEquals("Madrid", largestPopulationCity.district);
        assertEquals("Spain", largestPopulationCity.countryCode);
        assertEquals(2879052, largestPopulationCity.population);
    }

    @Test
    void testByDistrict_ShouldProvide_CitiesFromLargestPopulationFirst()
    {
        ArrayList<City> cities = allCities.ByDistrict(TEST_DISTRICT);
        assertNotNull(cities);
        assertFalse(cities.isEmpty());

        City largestPopulationCity = cities.get(0);

        assertEquals("La Matanza", largestPopulationCity.name);
        assertEquals("Buenos Aires", largestPopulationCity.district);
        assertEquals("Argentina", largestPopulationCity.countryCode);
        assertEquals(1266461, largestPopulationCity.population);
    }

    //---------------------------------- All Countries Population -----------------------------------------//

    @Test
    void testByWorld_ShouldProvide_CountriesFromLargestPopulationFirst()
    {
        ArrayList<Country> countries = allCountries.ByWorld();
        assertNotNull(countries);
        assertFalse(countries.isEmpty());

        Country largestPopulationCountry = countries.get(0);

        assertEquals("CHN", largestPopulationCountry.code);
        assertEquals("China", largestPopulationCountry.name);
        assertEquals("Asia", largestPopulationCountry.continent);
        assertEquals("Eastern Asia", largestPopulationCountry.region);
        assertEquals("Peking", largestPopulationCountry.capitalName);
        assertEquals(1277558000, largestPopulationCountry.population);
    }

    @Test
    void testByContinent_ShouldProvide_CountriesLargestPopulationFirst()
    {
        ArrayList<Country> countries = allCountries.ByContinent(TEST_CONTINENT);
        assertNotNull(countries);
        assertFalse(countries.isEmpty());

        Country largestPopulationCountry = countries.get(0);

        assertEquals("NGA", largestPopulationCountry.code);
        assertEquals("Nigeria", largestPopulationCountry.name);
        assertEquals("Africa", largestPopulationCountry.continent);
        assertEquals("Western Africa", largestPopulationCountry.region);
        assertEquals("Abuja", largestPopulationCountry.capitalName);
        assertEquals(111506000, largestPopulationCountry.population);
    }

    @Test
    void testByRegion_ShouldProvide_CountriesLargestPopulationFirst()
    {
        ArrayList<Country> countries = allCountries.ByRegion(TEST_REGION);
        assertNotNull(countries);
        assertFalse(countries.isEmpty());

        Country largestPopulationCountry = countries.get(0);

        assertEquals("CUB", largestPopulationCountry.code);
        assertEquals("Cuba", largestPopulationCountry.name);
        assertEquals("North America", largestPopulationCountry.continent);
        assertEquals("Caribbean", largestPopulationCountry.region);
        assertEquals("La Habana", largestPopulationCountry.capitalName);
        assertEquals(11201000, largestPopulationCountry.population);
    }
}
