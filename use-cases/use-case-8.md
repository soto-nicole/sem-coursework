# USE CASE: 8 Produce a Report on the Total Population in a given area

## CHARACTERISTIC INFORMATION

### Goal in Context

As a Distribution Director, I want to produce a report on the total population of a given area so that I can strategically place new Central Distribution Centres (CDC) to ensure maximum efficiency when transporting goods.

### Scope

Company.

### Level

Primary task.

### Preconditions

- We know the area we want to limit population data to, this case:  continent, region, country. 
- Database contains current world population numbers, including for continents, regions and countries and people living in and out the cities. 
- The system has the capability to calculate percentages for in-cities and out-cities living, within the specified area.

### Success End Condition

A report is available showing the total population, in-city living population, out-city living population and their respective percentages within the chosen area, facilitating CDC strategic placements. 

### Failed End Condition

No population data is found and a report cannot be produced.

### Primary Actor

Distribution Director

### Trigger

A request for the business to optimize the distribution network by placing CDC strategically in a given area depending on the population's density. 

## MAIN SUCCESS SCENARIO

1. Distribution Director receives a request to find highly populated areas, including urban and rural, to strategically place a CDC. 
2. Distribution Director pinpoints the specific area to receive the total population for this specific area. The system also calculates the total population numbers, the % of population living in the city, and outside the city. 
3. Distribution Director employee extracts population data for the required areas.
4. Distribution Director identifies optimal areas for CDC placement while also considering people living in and out the city. 
5. Distribution Director recommends CDC locations to maximize logistical efficiency.

## EXTENSIONS

1.**Area does not exist**:
1. Distribution Director  informs the requester that the area data was requested for does not exist.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0