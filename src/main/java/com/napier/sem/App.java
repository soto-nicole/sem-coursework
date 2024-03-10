package com.napier.sem;
import com.napier.sem.Features.AllCities;
import com.napier.sem.Features.AllCountries;
import com.napier.sem.Features.TopNCountries;
import com.napier.sem.Features.TopNCities;
import com.napier.sem.Features.AllCapitalCities;
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
                Runnable action = getUserOption(app, choice);
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
    private static Runnable getUserOption(App app, String choice)
    {
        Map<String, Runnable> keyValues = new HashMap<>();
        String continent = "Africa";
        String region = "Caribbean";
        String country = "Spain";
        String district = "Buenos Aires";

        //---------       All countries by population in descending order --------------
        keyValues.put("1", () -> app.displayCountries(AllCountries.ByWorld()));
        keyValues.put("2", () -> app.displayCountries(AllCountries.ByContinent(continent)));
        keyValues.put("3", () -> app.displayCountries(AllCountries.ByRegion(region)));

        //---------       All cities by population in descending order    ---------------
        keyValues.put("4", () -> app.displayCities(AllCities.ByWorld()));
        keyValues.put("5", () -> app.displayCities(AllCities.ByContinent(continent)));
        keyValues.put("6", () -> app.displayCities(AllCities.ByRegion(region)));
        keyValues.put("7", () -> app.displayCities(AllCities.ByCountry(country)));
        keyValues.put("8", () -> app.displayCities(AllCities.ByDistrict(district)));

        //-------------- Top N countries by population in descending order --------------
        keyValues.put("9", () -> app.displayCountries(TopNCountries.ByWorld(getN(scanner))));
        keyValues.put("10", () -> app.displayCountries(TopNCountries.ByContinent(getN(scanner), continent)));
        keyValues.put("11", () -> app.displayCountries(TopNCountries.ByRegion(getN(scanner), region)));

        //---------------Top N cities by population in descending order -------------------
        keyValues.put("12", () -> app.displayCities(TopNCities.ByWorld(getN(scanner))));
        keyValues.put("13", () -> app.displayCities(TopNCities.ByContinent(getN(scanner), continent)));
        keyValues.put("14", () -> app.displayCities(TopNCities.ByRegion(getN(scanner), region)));
        keyValues.put("15", () -> app.displayCities(TopNCities.ByCountry(getN(scanner), country)));
        keyValues.put("16", () -> app.displayCities(TopNCities.ByDistrict(getN(scanner), district)));

        //---------       All capital cities by population in descending order    ---------------
        keyValues.put("17", () -> app.displayCapitalCities(AllCapitalCities.ByWorld()));

        return keyValues.getOrDefault(choice, () -> System.out.println("Invalid choice."));
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
    public void displayCapitalCities(ArrayList<City> capitalCities) {
        if (capitalCities == null) {
            System.out.println("No capital cities");
            return;
        }

        System.out.println(String.format("%-35s %-60s %-15s", "Name", "Country", "Population"));

        for (City capitalCity : capitalCities) {
            if (capitalCity == null) continue;
            String capitalCityString = String.format("%-35s %-60s %-15d",
                    capitalCity.name, capitalCity.countryCode, capitalCity.population);
            System.out.println(capitalCityString);
        }

        System.out.println("`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````");
    }

        private static int getN(Scanner scanner)
    {
        System.out.println("How many top N would you like to see?:");
        int N = scanner.nextInt();
        scanner.nextLine();
        return N;
    }
}