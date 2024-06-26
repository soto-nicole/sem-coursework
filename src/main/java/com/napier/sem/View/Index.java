package com.napier.sem.View;

import com.napier.sem.App;
import com.napier.sem.Features.*;
import com.napier.sem.Helpers.ReportHelper;
import com.napier.sem.Utils.DatabaseUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Allows display and selection of the application's query options to users
 */
public class Index
{
    /**
     * Helps other methods create queries to the db
     */
    private final ReportHelper reportHelper;
    /**
     * Allows use of methods relevant to querying all the countries in specific areas
     */
    private final AllCountries allCountries;
    /**
     * Allows use of methods relevant to querying the top populated countries in specific areas
     */
    private final TopNCountries topNCountries;
    /**
     * Allows use of methods relevant to querying all the cities in specific areas
     */
    private final AllCities allCities;
    /**
     * Allows use of methods relevant to querying all the capital cities in specific areas
     */
    private final AllCapitalCities allCapitalCities;
    /**
     * Allows use of methods relevant to querying the top populated capital cities in specific areas
     */
    private final TopNCapitalCities topNCapitalCities;
    /**
     * Allows use of methods relevant to querying the top populated cities in specific areas
     */
    private final TopNCities topNCities;
    /**
     * Allows use of methods relevant to querying all the populations of specific area types
     */
    private final AllPopulations allPopulations;
    /**
     * Allows use of methods relevant to querying the population of specific areas
     */
    private final SpecificPopulation specificPopulation;
    /**
     * Allows use of a method relevant to querying the number of speakers of certain languages in the world
     */
    private final LanguageByPopulation languages;

    /**
     * Constructor to create an Index object with access to all the necessary methods for querying data
     */
    public Index()
    {
        this.reportHelper = new ReportHelper(DatabaseUtil.getConnection());
        this.allCountries = new AllCountries(reportHelper);
        this.topNCountries = new TopNCountries(reportHelper);
        this.allCities = new AllCities(reportHelper);
        this.allCapitalCities = new AllCapitalCities(reportHelper);
        this.topNCapitalCities = new TopNCapitalCities(reportHelper);
        this.topNCities = new TopNCities(reportHelper);
        this.allPopulations = new AllPopulations(reportHelper);
        this.specificPopulation = new SpecificPopulation(reportHelper);
        this.languages = new LanguageByPopulation(reportHelper);
    }

    /**
     * Generates the menu options text for user interaction with ui in the terminal
     * @return string containing the menu options.
     */
    public String generateMenuOptionsText() {
        return "=========================================\n" +
                "|      Welcome to the report system     |\n" +
                "=========================================\n\n" +
                " 1) All the countries in the world by population in descending order\n" +
                " 2) All the countries in a continent by population in descending order\n" +
                " 3) All the countries in a region by population in descending order\n\n" +
                " 4) Top N populated countries in the world in descending order\n" +
                " 5) Top N populated countries in a continent in descending order\n" +
                " 6) Top N populated countries in a region in descending order\n\n" +
                " 7) All the cities in the world by population in descending order\n" +
                " 8) All the cities in a continent by population in descending order\n" +
                " 9) All the cities in a region by population in descending order\n" +
                " 10) All the cities in a country by population in descending order\n" +
                " 11) All the cities in a district by population in descending order\n\n" +
                " 12) Top N populated cities in the world in descending order\n" +
                " 13) Top N populated cities in a continent in descending order\n" +
                " 14) Top N populated cities in a region in descending order\n" +
                " 15) Top N populated cities in a country in descending order\n" +
                " 16) Top N populated cities in a district in descending order\n\n" +
                " 17) All the capital cities in the world by population in descending order\n" +
                " 18) All the capital cities in a continent by population in descending order\n" +
                " 19) All the capital cities in a region by population in descending order\n\n" +
                " 20) Top N populated capital cities in the world in descending order\n" +
                " 21) Top N populated capital cities in a continent in descending order\n" +
                " 22) Top N populated capital cities in a region in descending order\n\n" +
                " 23) Total population of people, people living within and outside of cities in each continent\n" +
                " 24) Total population of people, people living within outside of cities in each region\n" +
                " 25) Total population of people, people living within and outside of cities in each country\n\n" +
                " 26) Display the total population, population of people within and outside of cities in the world\n" +
                " 27) Display the total population, population of people within and outside of cities in a continent\n" +
                " 28) Display the total population, population of people within and outside of cities in a region\n" +
                " 29) Display the total population, population of people within and outside of cities in a country\n" +
                " 30) Display the total population of people in a district\n" +
                " 31) Display the total population of people in a city\n" +
                " 32) Display the total number of people who speak certain languages in the world and as a total percentage of the world population, in descending order of total speakers";
    }

    /**
     * Displaying the menu options for user interaction with the terminal.
     */
    public void displayOptions()
    {
        System.out.println(generateMenuOptionsText());
    }


    /**
     * Gets the action based on the user's choice and returns it as a Runnable.
     * @param app App object that contains the methods for displaying the data coming from the db
     * @param choice user's choice from the input
     * @param scanner Scanner object used fo the input
     * @return  A Runnable for the action corresponding to the user's choice
     */
    public Runnable getUserOption(App app, String choice, Scanner scanner)
    {
        Map<String, Runnable> keyValues = new HashMap<>();
        String continent = "Africa";
        String region = "Caribbean";
        String country = "Spain";
        String district = "Buenos Aires";
        String city = "Seoul";

        //---------       All countries by population in descending order --------------
        keyValues.put("1", () -> app.displayCountries(allCountries.ByWorld()));
        keyValues.put("2", () -> app.displayCountries(allCountries.ByContinent(continent)));
        keyValues.put("3", () -> app.displayCountries(allCountries.ByRegion(region)));

        //-------------- Top N countries by population in descending order --------------
        keyValues.put("4", () -> app.displayCountries(topNCountries.ByWorld(getN(scanner))));
        keyValues.put("5", () -> app.displayCountries(topNCountries.ByContinent(getN(scanner), continent)));
        keyValues.put("6", () -> app.displayCountries(topNCountries.ByRegion(getN(scanner), region)));

        //---------       All cities by population in descending order    ---------------
        keyValues.put("7", () -> app.displayCities(allCities.ByWorld()));
        keyValues.put("8", () -> app.displayCities(allCities.ByContinent(continent)));
        keyValues.put("9", () -> app.displayCities(allCities.ByRegion(region)));
        keyValues.put("10", () -> app.displayCities(allCities.ByCountry(country)));
        keyValues.put("11", () -> app.displayCities(allCities.ByDistrict(district)));

        //---------------Top N cities by population in descending order -------------------
        keyValues.put("12", () -> app.displayCities(topNCities.ByWorld(getN(scanner))));
        keyValues.put("13", () -> app.displayCities(topNCities.ByContinent(getN(scanner), continent)));
        keyValues.put("14", () -> app.displayCities(topNCities.ByRegion(getN(scanner), region)));
        keyValues.put("15", () -> app.displayCities(topNCities.ByCountry(getN(scanner), country)));
        keyValues.put("16", () -> app.displayCities(topNCities.ByDistrict(getN(scanner), district)));

        //---------       All capital cities by population in descending order    ---------------
        keyValues.put("17", () -> app.displayCapitalCities(allCapitalCities.ByWorld()));
        keyValues.put("18", () -> app.displayCapitalCities(allCapitalCities.ByContinent(continent)));
        keyValues.put("19", () -> app.displayCapitalCities(allCapitalCities.ByRegion(region)));

        //---------       Top N capital cities by population in descending order    ---------------
        keyValues.put("20", () -> app.displayCapitalCities(topNCapitalCities.ByWorld(getN(scanner))));
        keyValues.put("21", () -> app.displayCapitalCities(topNCapitalCities.ByContinent(getN(scanner), continent)));
        keyValues.put("22", () -> app.displayCapitalCities(topNCapitalCities.ByRegion(getN(scanner), region)));

        //---------       All total populations of people, within and outwith cities for each specified area type  ---------------
        keyValues.put("23", () -> app.displayPopulations(allPopulations.ByContinent()));
        keyValues.put("24", () -> app.displayPopulations(allPopulations.ByRegion()));
        keyValues.put("25", () -> app.displayPopulations(allPopulations.ByCountry()));

        //---------       Total population of people, within and outwith cities in a specific area type  ---------------
        keyValues.put("26", () -> app.displaySpecificPopulation(specificPopulation.ByWorld(), "World"));
        keyValues.put("27", () -> app.displaySpecificPopulation(specificPopulation.ByContinent(continent), "Continent"));
        keyValues.put("28", () -> app.displaySpecificPopulation(specificPopulation.ByRegion(region), "Region"));
        keyValues.put("29", () -> app.displaySpecificPopulation(specificPopulation.ByCountry(country), "Country"));
        keyValues.put("30", () -> app.displaySpecificPopulation(specificPopulation.ByDistrict(district), "District"));
        keyValues.put("31", () -> app.displaySpecificPopulation(specificPopulation.ByCity(city), "City"));

        //---------       Total population of people who speak certain languages  ---------------
        keyValues.put("32", () -> app.displayLanguages(languages.ByWorld()));

        return keyValues.getOrDefault(choice, () -> System.out.println("Invalid choice."));
    }

    /**
     * Asks user for input: number of top N values they would like to see.
     * @param scanner Scanner object used fo the input
     * @return user's chosen value for N
     */
    public int getN(Scanner scanner)
    {
        System.out.println("How many top N would you like to see?:");
        int N = scanner.nextInt();
        scanner.nextLine();
        return N;
    }
}
