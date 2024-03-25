package com.napier.sem;
import com.napier.sem.Models.City;
import com.napier.sem.Models.Country;
import com.napier.sem.Utils.DatabaseUtil;
import com.napier.sem.View.Index;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class App
{
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
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


    public void connect(String location, int delay)
    {
        DatabaseUtil.connect(location, delay);
    }
}