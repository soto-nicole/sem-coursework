package com.napier.sem;
import com.napier.sem.Features.*;
import com.napier.sem.Models.City;
import com.napier.sem.Models.Country;
import com.napier.sem.Utils.DatabaseUtil;
import com.napier.sem.View.Index;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App
{
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        App app = new App();
        DatabaseUtil.connect();

        boolean continueLoop = true;
        boolean isDefaultOption = false;

        while (continueLoop)
        {
            Index.displayOptions();
            System.out.println("Select the number you wish to see the report for or press 0 to quit:");

            String choice = null;
            long startTime = System.currentTimeMillis();
            final long timeout = 20000; // 20 secs

            while (System.currentTimeMillis() - startTime < timeout && choice == null)
            {
                try
                {
                    if (System.in.available() > 0)
                    {
                        choice = scanner.nextLine();
                        isDefaultOption = false;
                    } else
                    {
                        Thread.sleep(200);
                    }
                }
                catch (IOException | InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                    System.out.println("An error occurred. Exiting...");
                    return;
                }
            }

            // choosing default after 20 seconds
            if (choice == null)
            {
                choice = "1";
                isDefaultOption = true;
            }

            if ("0".equals(choice))
            {
                continueLoop = false;
            }

            else
            {
                Runnable action = Index.getUserOption(app, choice, scanner);
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

        System.out.println(String.format("%-5s %-40s %-20s %-35s %-15s %-20s", "Code", "Name", "Continent", "Region", "Population", "Capital"));

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

        System.out.println(String.format("%-35s %-60s %-40s %-15s", "Name", "Country", "District", "Population"));

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

        System.out.println(String.format("%-35s %-60s %-15s", "Name", "Country", "Population"));

        for (City capitalCity : capitalCities)
        {
            if (capitalCity == null) continue;
            String capitalCityString = String.format("%-35s %-60s %-15d",
                    capitalCity.name, capitalCity.countryCode, capitalCity.population);
            System.out.println(capitalCityString);
        }

        System.out.println("`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````");
    }
}