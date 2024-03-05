package com.napier.sem;
import com.napier.sem.Features.CountriesList;
import com.napier.sem.Models.Country;
import com.napier.sem.Utils.DatabaseUtil;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {

        DatabaseUtil.connect();
        //------------------ All countries by population in descending order --------------------------------

        //1. All countries in the world sorted by population number in descending order (largest-to-smallest)
        ArrayList<Country> countriesWorld = CountriesList.ByWorld();
        System.out.println("All countries in the world organised by largest population to smallest:");
        System.out.println("``````````````````````````````````````````````````````````````````````````````````````````");
        displayCountries(countriesWorld);

        //2. All countries in a continent by population number in descending order (largest-to-smallest)
        String continent = "Africa";
        ArrayList<Country> countriesContinent = CountriesList.ByContinent(continent);
        System.out.println("All countries in " + continent + " organised by largest population to smallest:");
        System.out.println("``````````````````````````````````````````````````````````````````````````````````````````");
        displayCountries(countriesContinent);

        DatabaseUtil.disconnect();
    }

    /**
     * Displays the countries' details in a formatted table
     * @param countries The list of Country objects that will be displayed
     */
    public static void displayCountries(ArrayList<Country> countries) {
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
    }
}