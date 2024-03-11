package com.napier.sem.View;

import com.napier.sem.App;
import com.napier.sem.Features.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Index
{
    public static void displayOptions()
    {
        System.out.println("=========================================");
        System.out.println("|      Welcome to the report system     |");
        System.out.println("=========================================\n");

        System.out.println("  1)   List of countries in the world by population in descending order");
        System.out.println("  2)   List of countries in a continent by population in descending order");
        System.out.println("  3)   List of countries in a region by population in descending order");
        System.out.println();

        System.out.println("  4)   List of cities in the world by population in descending order");
        System.out.println("  5)   List of cities of a continent by population in descending order");
        System.out.println("  6)   List of cities in a region by population in descending order");
        System.out.println("  7)   List of cities in a country by population in descending order");
        System.out.println("  8)   List of cities in a district by population in descending order");
        System.out.println();
        System.out.println("  9)   Top populated countries in the world");
        System.out.println("  10)  Top populated countries in a continent");
        System.out.println("  11)  Top populated countries in a region");
        System.out.println();
        System.out.println("  12)  Top populated cities in the world");
        System.out.println("  13)  Top populated cities in a continent");
        System.out.println("  14)  Top populated cities in a region");
        System.out.println("  15)  Top populated cities in a country");
        System.out.println("  16)  Top populated cities in a district");
        System.out.println();
        System.out.println("  17)  List of capital cities in the world by population in descending order");
        System.out.println("  18)  List of capital cities in a continent by population in descending order");
        System.out.println("  19)  List of capital cities in a region by population in descending order");
        System.out.println();
        System.out.println("  20)  Top populated capital cities in the world in descending order");
        System.out.println("  21)  Top populated capital cities in a continent in descending order");
        System.out.println("  22)  Top populated capital cities in a region in descending order");
    }

    public static Runnable getUserOption(App app, String choice, Scanner scanner)
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
        keyValues.put("18", () -> app.displayCapitalCities(AllCapitalCities.ByContinent(continent)));
        keyValues.put("19", () -> app.displayCapitalCities(AllCapitalCities.ByRegion(region)));

        //---------       Top N capital cities by population in descending order    ---------------
        keyValues.put("20", () -> app.displayCapitalCities(TopNCapitalCities.ByWorld(getN(scanner))));
        keyValues.put("21", () -> app.displayCapitalCities(TopNCapitalCities.ByContinent(getN(scanner), continent)));
        keyValues.put("22", () -> app.displayCapitalCities(TopNCapitalCities.ByRegion(getN(scanner), region)));

        return keyValues.getOrDefault(choice, () -> System.out.println("Invalid choice."));
    }

    public static int getN(Scanner scanner)
    {
        System.out.println("How many top N would you like to see?:");
        int N = scanner.nextInt();
        scanner.nextLine();
        return N;
    }
}
