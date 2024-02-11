# Vision Statement

## Intro
### Purpose
The intended purpose for this application is to provide the organization Fresh Blooms with the ability to view and report on population information from around the world. The task is to design and implement a new system to allow easy access to this population information.  
This will enable them to pinpoint the most populated countries, cities and other areas in the world to find new places to distribute their products to. There is also an intention to provide number of speakers of specific languages - this will help the company prioritise translation efforts for their global marketing to reach more customers. 
The main teams this system will benefit are marketing and logistics.
n existing SQL database exists with this population data which can be utilized by the application.

### Solution Overview
The application will provide a tabular front-end view to the database, offering users the ability to view and filter pre-defined population records from different areas of the world. These will be ordered from largest to smallest population, to visualize immediately where the largest impacts may be had.
This will allow the team to quickly receive specific data of the populations being sought after and contribute to important business decisions based on the resulting data. This includes number of people living in specific cities, countries, continents and even the number of people who speak certain languages across the world.

## User Description
Individuals using the application within these teams are likely individuals with limited technical knowledge – instead focused on the business knowledge that can be gleaned from the application. They require a simple and quick way to access their desired data to complement business needs. This could potentially take the form of an interactive website or a desktop application - which is clearly labeled and easy to navigate.

## Features

### Produce population reports based on specific criteria

- All the countries in the world organised by largest population to smallest.
- All the countries in a continent organised by largest population to smallest.
- All the countries in a region organised by largest population to smallest.
- The top `N` populated countries in the world where `N` is provided by the user.
- The top `N` populated countries in a continent where `N` is provided by the user.
- The top `N` populated countries in a region where `N` is provided by the user.
- All the cities in the world organised by largest population to smallest.
- All the cities in a continent organised by largest population to smallest.
- All the cities in a region organised by largest population to smallest.
- All the cities in a country organised by largest population to smallest.
- All the cities in a district organised by largest population to smallest.
- The top `N` populated cities in the world where `N` is provided by the user.
- The top `N` populated cities in a continent where `N` is provided by the user.
- The top `N` populated cities in a region where `N` is provided by the user.
- The top `N` populated cities in a country where `N` is provided by the user.
- The top `N` populated cities in a district where `N` is provided by the user.
- All the capital cities in the world organised by largest population to smallest.
- All the capital cities in a continent organised by largest population to smallest.
- All the capital cities in a region organised by largest to smallest.
- The top `N` populated capital cities in the world where N is provided by the user.
- The top `N` populated capital cities in a continent where N is provided by the user.
- The top `N` populated capital cities in a region where N is provided by the user.
- The population of people, people living in cities, and people not living in cities in each continent.
- The population of people, people living in cities, and people not living in cities in each region.
- The population of people, people living in cities, and people not living in cities in each country.

Country Report - A country report requires the following columns:

- Code.
- Name.
- Continent.
- Region.
- Population.
- Capital.

- City Report - A city report requires the following columns:

- Name.
- Country.
- District.
- Population.

Capital City Report - A capital city report requires the following columns:

- Name.
- Country.
- Population.

Additionally, the following information should be accessible to the organisation:

- The population of the world.
- The population of a continent.
- The population of a region.
- The population of a country.
- The population of a district.
- The population of a city.

Finally, the organisation has asked if it is possible to provide the number of people who speak the following the following languages from greatest number to smallest, including the percentage of the world population:

- Chinese.
- English.
- Hindi.
- Spanish.
- Arabic.
  Population Report - For the population reports, the following information is requested:

The name of the continent/region/country.
The total population of the continent/region/country.
The total population of the continent/region/country living in cities (including a %).
The total population of the continent/region/country not living in cities (including a %).

