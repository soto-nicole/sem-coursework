package com.napier.sem.Models;

/**
 * A class that creates Population objects
 */
public class Population
{
    /**
     * The name of the area the population is being reported of
     */
    public String areaName;
    /**
     * The total number of people within the area
     */
    public long population;
    /**
     * The total number of people who live within cities
     */
    public long populationCities;
    /**
     * The total number of people who live within cities as a percentage of the total population of the area
     */
    public float populationCitiesPercentage;
    /**
     * The total number of people who live outwith cities
     */
    public long populationOutsideCities;
    /**
     * The total number of people who live outside of cities as a percentage of the total population of the area
     */
    public float populationOutsideCitiesPercentage;
}