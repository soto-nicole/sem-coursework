package com.napier.sem;

import com.napier.sem.Features.AllCities;
import com.napier.sem.Features.AllCountries;
import com.napier.sem.Helpers.ReportHelper;
import com.napier.sem.Models.City;
import com.napier.sem.Models.Country;
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

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060", 30000);
        ReportHelper reportHelper = new ReportHelper(DatabaseUtil.getConnection());
        allCountries = new AllCountries(reportHelper);
        allCities = new AllCities(reportHelper);
    }

    //---------------------------------- Top N Populated countries-----------------------------------------//







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
        ArrayList<City> cities = allCities.ByContinent("Africa");
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
        ArrayList<City> cities = allCities.ByRegion("Caribbean");
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
        ArrayList<City> cities = allCities.ByCountry("Spain");
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
        ArrayList<City> cities = allCities.ByDistrict("Buenos Aires");
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
        ArrayList<Country> countries = allCountries.ByContinent("Africa");
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
        ArrayList<Country> countries = allCountries.ByRegion("Caribbean");
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
