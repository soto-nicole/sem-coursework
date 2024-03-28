package com.napier.sem.View;

import com.napier.sem.App;
import com.napier.sem.Features.*;
import com.napier.sem.Helpers.ReportHelper;
import com.napier.sem.Utils.DatabaseUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Index {
    private ReportHelper reportHelper;
    private AllCountries allCountries;
    private TopNCountries topNCountries;
    private AllCities allCities;
    private AllCapitalCities allCapitalCities;
    private TopNCapitalCities topNCapitalCities;
    private TopNCities topNCities;
    private AllPopulations allPopulations;
    private SpecificPopulation specificPopulation;

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
    }

    /**
     * Generates the menu options text for user interaction with ui in the terminal
     * @return string containing the menu options.
     */
    public String generateMenuOptionsText()
    {
        StringBuilder menu = new StringBuilder();
        menu.append("=========================================\n");
        menu.append("|      Welcome to the report system     |\n");
        menu.append("=========================================\n\n");

        menu.append("  1) List of countries in the world by population in descending order\n");
        menu.append("  2) List of countries in a continent by population in descending order\n");
        menu.append("  3) List of countries in a region by population in descending order\n\n");

        menu.append("  4) List of cities in the world by population in descending order\n");
        menu.append("  5) List of cities of a continent by population in descending order\n");
        menu.append("  6) List of cities in a region by population in descending order\n");
        menu.append("  7) List of cities in a country by population in descending order\n");
        menu.append("  8) List of cities in a district by population in descending order\n\n");

        menu.append("  9) Top populated countries in the world\n");
        menu.append(" 10) Top populated countries in a continent\n");
        menu.append(" 11) Top populated countries in a region\n\n");

        menu.append(" 12) Top populated cities in the world\n");
        menu.append(" 13) Top populated cities in a continent\n");
        menu.append(" 14) Top populated cities in a region\n");
        menu.append(" 15) Top populated cities in a country\n");
        menu.append(" 16) Top populated cities in a district\n\n");

        menu.append(" 17) List of capital cities in the world by population in descending order\n");
        menu.append(" 18) List of capital cities in a continent by population in descending order\n");
        menu.append(" 19) List of capital cities in a region by population in descending order\n\n");

        menu.append(" 20) Top populated capital cities in the world in descending order\n");
        menu.append(" 21) Top populated capital cities in a continent in descending order\n");
        menu.append(" 22) Top populated capital cities in a region in descending order\n\n");

        menu.append(" 23) Total population of people within and outside of cities in each continent\n");
        menu.append(" 24) Total population of people within and outside of cities in each region\n");
        menu.append(" 25) Total population of people within and outside of cities in each country\n\n");

        menu.append(" 26) Display the total population, population of people within and outside of cities in the world\n");
        menu.append(" 27) Display the total population, population of people within and outside of cities in a continent\n");
        menu.append(" 28) Display the total population, population of people within and outside of cities in a region\n");
        menu.append(" 29) Display the total population, population of people within and outside of cities in a country\n");
        menu.append(" 30) Display the total population, population of people within and outside of cities in a district\n");

        return menu.toString();
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

        //---------       All countries by population in descending order --------------
        keyValues.put("1", () -> app.displayCountries(allCountries.ByWorld()));
        keyValues.put("2", () -> app.displayCountries(allCountries.ByContinent(continent)));
        keyValues.put("3", () -> app.displayCountries(allCountries.ByRegion(region)));

        //---------       All cities by population in descending order    ---------------
        keyValues.put("4", () -> app.displayCities(allCities.ByWorld()));
        keyValues.put("5", () -> app.displayCities(allCities.ByContinent(continent)));
        keyValues.put("6", () -> app.displayCities(allCities.ByRegion(region)));
        keyValues.put("7", () -> app.displayCities(allCities.ByCountry(country)));
        keyValues.put("8", () -> app.displayCities(allCities.ByDistrict(district)));

        //-------------- Top N countries by population in descending order --------------
        keyValues.put("9", () -> app.displayCountries(topNCountries.ByWorld(getN(scanner))));
        keyValues.put("10", () -> app.displayCountries(topNCountries.ByContinent(getN(scanner), continent)));
        keyValues.put("11", () -> app.displayCountries(topNCountries.ByRegion(getN(scanner), region)));

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
