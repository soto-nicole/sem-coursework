# USE CASE: 1 Produce a Report on the Top Populated Countries in a given area

## CHARACTERISTIC INFORMATION

### Goal in Context

As a member of the Business Development team, I want to produce a filtered report showing the top populated countries in a given area, so that I can recommend only the countries with the highest potential customer numbers for a new global branch.

### Scope

Company.

### Level

Primary task.

### Preconditions

We know the area we want to limit population data to (world, specific continent, region).  
Database contains current world population numbers, including continents and regions.

### Success End Condition

A report is available for the Business Devleopment team to view top population data and use this in reporting potential locations for a new global expansion branch.

### Failed End Condition

No population data is found and a report cannot be produced.

### Primary Actor

Business Development team employee

### Trigger

A request for the business to create a new global distribution branch in a profitable country they have not yet expanded to.

## MAIN SUCCESS SCENARIO

1. Business Development team receive a request to find highly populated countries in a specific area to and pinpoint a country to open a new company branch in.
2. Business Development employee pinpoints the specific area of the world to receive the top country population data for.
3. Business Development employee extracts population data for the required areas.
4. Business Development employee recommends a new country with a high population in their report, where they would likely see increased financial benefit if the organization began distributing within the country.

## EXTENSIONS

1.**Area does not exist**:
1. Business Development team informs the requester that the area data was requested for does not exist.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0