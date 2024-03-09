package com.napier.sem.Models;

/**
 * A class that creates City objects
 */
public class City
{
    /**
     * The unique ID of the city in the database
     */
    public int id;
    /**
     * The name of the city
     */
    public String name;
    /**
     * The country code that relates to the name of the country where the city is found
     */
    public String countryCode;
    /**
     * The name of the district within which the city is located
     */
    public String district;
    /**
     * The total population of the city
     */
    public int population;
}