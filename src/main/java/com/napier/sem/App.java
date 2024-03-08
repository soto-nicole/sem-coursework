package com.napier.sem;
import com.napier.sem.Features.AllCities;
import com.napier.sem.Features.AllCountries;
import com.napier.sem.Features.TopNCountries;
import com.napier.sem.Features.TopNCities;
import com.napier.sem.Models.City;
import com.napier.sem.Models.Country;
import com.napier.sem.Utils.DatabaseUtil;
import java.util.ArrayList;

public class App
{
    public static void main(String[] args)
    {
        App app = new App();
        String continent = "Africa";
        String region = "Caribbean";
        String country = "Spain";
        String district = "Tucumán";
        int N = 5;

        DatabaseUtil.connect();

        //--------------------- All countries by population in descending order --------------------------------
        //1. All countries in the world sorted by population number in descending order (largest-to-smallest)
        System.out.println("All countries in the world organised by largest population to smallest:");
        app.displayCountries(AllCountries.ByWorld());

        //2. All countries in a continent by population number in descending order (largest-to-smallest)
        System.out.println("All countries in the " + continent + " continent organised by largest population to smallest:");
        app.displayCountries(AllCountries.ByContinent(continent));

        //3. All countries in a region by population number in descending order (largest-to-smallest)
        System.out.println("All countries in the " + region + " region organised by largest population to smallest:");
        app.displayCountries(AllCountries.ByRegion(region));


        //------------------ All cities by population in descending order --------------------------------
        //4. All cities in the world sorted by population number in descending order (largest-to-smallest)
        System.out.println("All cities in the world organised by largest population to smallest:");
        app.displayCities(AllCities.ByWorld());

        //5. All cities in a continent sorted by population number in descending order (largest-to-smallest)
        System.out.println("All countries in the " + continent + " continent organised by largest population to smallest:");
        app.displayCities(AllCities.ByContinent(continent));

        //6. All cities in a region by population number in descending order (largest-to-smallest)
        System.out.println("All cities in the " + region + " region organised by largest population to smallest:");
        app.displayCities(AllCities.ByRegion(region));

        //7. All cities in a country by population number in descending order (largest-to-smallest)
        System.out.println("All cities in " + country + " organised by largest population to smallest:");
        app.displayCities(AllCities.ByCountry(country));

        //8. All cities in a district by population number in descending order (largest-to-smallest)
        System.out.println("All cities in the " + district + " district organised by largest population to smallest:");
        app.displayCities(AllCities.ByDistrict(district));

        //------------------ Top N countries by population in descending order --------------------------------
        //9. Top N populated countries in the world sorted by population number in descending order (largest-to-smallest)

        System.out.println("Top " + N + " countries in the world organised by largest population to smallest:");
        app.displayCountries(TopNCountries.ByWorld(N));

        //10. Top N populated countries in a specific continent sorted by population number in descending order (largest-to-smallest)

        System.out.println("Top " + N + " countries in the " + continent + " continent organised by largest population to smallest:");
        app.displayCountries(TopNCountries.ByContinent(N, continent));

        //11. Top N populated countries in a specific region sorted by population number in descending order (largest-to-smallest)

        System.out.println("Top " + N + " countries in the " + region + " region organised by largest population to smallest:");
        app.displayCountries(TopNCountries.ByRegion(N, region));

        //------------------ Top N cities by population in descending order --------------------------------
        //12. Top N populated cities in the world sorted by population number in descending order (largest-to-smallest)

        System.out.println("Top " + N + " cities in the world organised by largest population to smallest:");
        app.displayCities(TopNCities.ByWorld(N));

        //13. Top N populated cities in a specific continent sorted by population number in descending order (largest-to-smallest)

        System.out.println("Top " + N + " cities in the " + continent + " continent organised by largest population to smallest:");
        app.displayCities(TopNCities.ByContinent(N, continent));

        //14. Top N populated cities in a specific region sorted by population number in descending order (largest-to-smallest)

        System.out.println("Top " + N + " cities in the " + region + " region organised by largest population to smallest:");
        app.displayCities(TopNCities.ByRegion(N, region));

        //15. Top N populated cities in a specific country sorted by population number in descending order (largest-to-smallest)

        System.out.println("Top " + N + " cities in the country " + country + " organised by largest population to smallest:");
        app.displayCities(TopNCities.ByCountry(N, country));

        DatabaseUtil.disconnect();
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
}