package com.napier.sem;
import com.napier.sem.Models.City;
import com.napier.sem.Models.Country;
import com.napier.sem.Models.Language;
import com.napier.sem.Models.Population;
import com.napier.sem.Utils.DatabaseUtil;
import com.napier.sem.View.Index;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Creates an instance of the application
 */
public class App
{
    /**
     * Allows user input
     */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Main method
     * @param args Custom database location and delay time can be passed in here
     */
    public static void main(String[] args)
    {
        App app = new App();
        if (args.length < 2)
        {
            DatabaseUtil.connect("localhost:33060", 30000);
        }
        else
        {
            DatabaseUtil.connect(args[0], Integer.parseInt(args[1]));
        }
        Index index = new Index();


        boolean continueLoop = true;
        boolean isDefaultOption = false;

         // This loop will keep displaying the selection options to the user and accepting their input until:
         //- The user decides to exit pressing 0
         // - or, there is no input within the first 20 seconds
         // This is a workaround for a failing pipeline and wanting to have an interactive mode using docker compose for user to input what report they wish to see

        while (continueLoop)
        {
            index.displayOptions();
            System.out.println("Select the number you wish to see the report for or press 0 to quit:");

            String choice = null;
            long startTime = System.currentTimeMillis();
            final long endTime = 20000; // 20 secs for timeout


            //  This loop will wait for the user input ot timeout

            while (System.currentTimeMillis() - startTime < endTime && choice == null)
            {
                try
                {
                    if (System.in.available() > 0) //checks if there is a user input available from the user
                    {
                        choice = scanner.nextLine();
                        isDefaultOption = false;    //no defaulted option, yes user input
                    }

                    else
                    {
                        Thread.sleep(200);
                    }
                }
                catch (IOException | InterruptedException e)
                {
                    Thread.currentThread().interrupt(); //Interrupts the thread
                    System.out.println("An error occurred. Exiting...");
                    return;
                }
            }

            // choosing default choice after 20 seconds
            if (choice == null)
            {
                choice = "1";     //choose table 1 as default
                isDefaultOption = true;
            }

            if ("0".equals(choice))  //exit the loop
            {
                continueLoop = false;
            }

            else
            {
                Runnable action = index.getUserOption(app, choice, scanner);
                action.run();

                if (isDefaultOption)
                {
                    continueLoop = false; //exit the loop if the default value is used after 20 secs
                }
            }
        }

        DatabaseUtil.disconnect();
        scanner.close();
        System.out.println("Thanks for using our system!");
    }


    /**
     * Displays the countries' details in a formatted table
     * @param countries The list of Country objects that will be displayed
     */
    public void displayCountries(ArrayList<Country> countries)
    {
        if (countries == null)
        {
            System.out.println("No countries");
            return;
        }

        System.out.printf("%-5s %-40s %-20s %-35s %-15s %-20s%n", "Code", "Name", "Continent", "Region", "Population", "Capital");

        for (Country country : countries)
        {
            if (country == null) continue;
            String countryString = String.format("%-5s %-40s %-20s %-35s %-15d %-20s",
                    country.code, country.name, country.continent, country.region, country.population, country.capitalName);
            System.out.println(countryString);
        }

        System.out.println("`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````");
    }

    /**
     * Displays the cities' details in a formatted table
     * @param cities The list of City objects that will be displayed
     */
    public void displayCities(ArrayList<City> cities)
    {
        if (cities == null)
        {
            System.out.println("No cities");
            return;
        }

        System.out.printf("%-35s %-60s %-40s %-15s%n", "Name", "Country", "District", "Population");

        for (City city : cities)
        {
            if (city == null) continue;
            String cityString = String.format("%-35s %-60s %-40s %-15d",
                    city.name, city.countryCode, city.district, city.population);
            System.out.println(cityString);
        }

        System.out.println("`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````");
    }

    /**
     * Displays the capital cities' details in a formatted table
     * @param capitalCities The list of City objects that will be displayed
     */
    public void displayCapitalCities(ArrayList<City> capitalCities)
    {
        if (capitalCities == null)
        {
            System.out.println("No capital cities");
            return;
        }

        System.out.printf("%-35s %-60s %-15s%n", "Name", "Country", "Population");

        for (City capitalCity : capitalCities)
        {
            if (capitalCity == null) continue;
            String capitalCityString = String.format("%-35s %-60s %-15d",
                    capitalCity.name, capitalCity.countryCode, capitalCity.population);
            System.out.println(capitalCityString);
        }
        System.out.println("`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````");
    }

    /**
     * Displays the population details in a formatted table
     * @param populations The list of Population objects that will be displayed
     */
    public void displayPopulations(ArrayList<Population> populations)
    {
        if (populations == null)
        {
            System.out.println("No populations");
            return;
        }

        System.out.printf("%-45s %-25s %-25s %-25s %-25s %-25s%n", "AreaName", "TotalPopulation", "PopulationInCities", "PopulationInCities(%)", "PopulationOutsideCities", "PopulationOutsideCities(%)");

        for (Population population : populations)
        {
            if (population == null) continue;
            String populationString = String.format("%-45s %-25s %-25s %-25s %-25s %-25s",
                    population.areaName, population.population, population.populationCities, population.populationCitiesPercentage, population.populationOutsideCities, population.populationOutsideCitiesPercentage);
            System.out.println(populationString);
        }

        System.out.println("`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````");
    }

    /**
     * Displays the population details in a formatted table
     * @param population The population object that will be displayed
     * @param type The area type that population data will be displayed for - affects displayed columns
     */
    public void displaySpecificPopulation(Population population, String type)
    {
        if (population == null)
        {
            System.out.println("No population");
            return;
        }

        String populationString;

        if (!Objects.equals(type, "District") && !Objects.equals(type, "City")) {
            System.out.printf("%-45s %-25s %-25s %-25s %-25s %-25s%n", "AreaName", "TotalPopulation", "PopulationInCities", "PopulationInCities(%)", "PopulationOutsideCities", "PopulationOutsideCities(%)");

            populationString = String.format("%-45s %-25s %-25s %-25s %-25s %-25s",
                    population.areaName, population.population, population.populationCities, population.populationCitiesPercentage, population.populationOutsideCities, population.populationOutsideCitiesPercentage);
        } else {
            System.out.printf("%-45s %-25s %n", "AreaName", "TotalPopulation");

            populationString = String.format("%-45s %-25s",
                    population.areaName, population.population);
        }

        System.out.println(populationString);

        System.out.println("`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````");
    }

    /**
     * Displays the language details in a formatted table
     * @param languages The language objects that will be displayed
     */
    public void displayLanguages(ArrayList<Language> languages)
    {
        if (languages == null)
        {
            System.out.println("No languages");
            return;
        }

        System.out.printf("%-30s %-30s %-30s %n", "Language", "TotalPopulation", "WorldPercentage (%)");

        for (Language language : languages)
        {
            if (language == null) continue;
            String languageString = String.format("%-30s %-30s %-30s ",
                    language.languageName, language.totalSpeakers, language.totalSpeakersPercentage);
            System.out.println(languageString);
        }

        System.out.println("`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````");
    }

    /**
     * Creates a connection to the database
     * @param location The location of the database/server
     * @param delay The length of time to wait in milliseconds for the database to connect
     */
    public void connect(String location, int delay)
    {
        DatabaseUtil.connect(location, delay);
    }
}