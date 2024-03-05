package com.napier.sem;

import com.napier.sem.Models.City;
import com.napier.sem.Models.Country;
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
        ArrayList<Country> countries = new ArrayList<Country>();
        app.displayCountries(countries);
    }
    @Test
    void printCountriesTestContainsNull()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        countries.add(null);
        app.displayCountries(countries);
    }

    @Test
    void printCountries()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
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
    void displayCitiesTestNull()
    {
        app.displayCities(null);
    }

    @Test
    void displayCitiesTestEmpty()
    {
        ArrayList<City> cities = new ArrayList<City>();
        app.displayCities(cities);
    }

    @Test
    void displayCitiesTestContainsNull()
    {
        ArrayList<City> cities = new ArrayList<City>();
        cities.add(null);
        app.displayCities(cities);
    }

    @Test
    void displayCities()
    {
        ArrayList<City> cities = new ArrayList<City>();
        City city = new City();
        city.name = "Kabul";
        city.countryCode = "Afghanistan";
        city.district = "Kabul";
        city.population = 1780000;
        cities.add(city);

        app.displayCities(cities);
    }
}
