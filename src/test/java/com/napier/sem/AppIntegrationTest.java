package com.napier.sem;

import com.napier.sem.Features.AllCountries;
import com.napier.sem.Models.Country;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060", 30000);

    }

    //---------------------------------- All Countries Population -----------------------------------------//

    @Test
    void testByWorld_ShouldProvide_LargestPopulationFirst()
    {
        ArrayList<Country> countries = AllCountries.ByWorld();
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
    void testByWorld_ShouldProvide_CountriesSmallestPopulationLast()
    {
        ArrayList<Country> countries = AllCountries.ByWorld();
        assertNotNull(countries);
        assertFalse(countries.isEmpty());

        Country smallestPopulationCountry = countries.get(countries.size() - 1);

        assertEquals("PCN", smallestPopulationCountry.code);
        assertEquals("Pitcairn", smallestPopulationCountry.name);
        assertEquals("Oceania", smallestPopulationCountry.continent);
        assertEquals("Polynesia", smallestPopulationCountry.region);
        assertEquals(50, smallestPopulationCountry.population);
        assertEquals("Adamstown", smallestPopulationCountry.capitalName);
    }

    @Test
    void testByContinent_ShouldProvide_CountriesLargestPopulationFirst()
    {
        ArrayList<Country> countries = AllCountries.ByContinent("Africa");
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
    void testByContinent_ShouldProvide_SmallestPopulationLast()
    {
        ArrayList<Country> countries = AllCountries.ByContinent("Africa");
        assertNotNull(countries);
        assertFalse(countries.isEmpty());

        Country smallestPopulationCountry = countries.get(countries.size() - 1);

        assertEquals("SHN", smallestPopulationCountry.code);
        assertEquals("Saint Helena", smallestPopulationCountry.name);
        assertEquals("Africa", smallestPopulationCountry.continent);
        assertEquals("Western Africa", smallestPopulationCountry.region);
        assertEquals(6000, smallestPopulationCountry.population);
        assertEquals("Jamestown", smallestPopulationCountry.capitalName);
    }


    @Test
    void testByRegion_ShouldProvide_CountriesLargestPopulationFirst()
    {
        ArrayList<Country> countries = AllCountries.ByRegion("Caribbean");
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


    @Test
    void testByRegion_ShouldProvide_SmallestPopulationLast()
    {
        ArrayList<Country> countries = AllCountries.ByRegion("Caribbean");
        assertNotNull(countries);
        assertFalse(countries.isEmpty());

        Country smallestPopulationCountry = countries.get(countries.size() - 1);

        assertEquals("AIA", smallestPopulationCountry.code);
        assertEquals("Anguilla", smallestPopulationCountry.name);
        assertEquals("North America", smallestPopulationCountry.continent);
        assertEquals("Caribbean", smallestPopulationCountry.region);
        assertEquals(8000, smallestPopulationCountry.population);
        assertEquals("The Valley", smallestPopulationCountry.capitalName);
    }
}
