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
    void testByWorld_ShouldProvide_SmallestPopulationLast()
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

}
