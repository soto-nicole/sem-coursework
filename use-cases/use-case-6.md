# USE CASE: 6 Produce a Report on the Top N Populated Countries in a region

## CHARACTERISTIC INFORMATION

### Goal in Context

As a Business Development manager, I want to produce a filtered report showing the top N populated countries in a region, so that I can recommend only the countries with the highest potential customer numbers for a new global branch.

### Scope

Company.

### Level

Primary task.

### Preconditions

- We know the area we want to limit population data to:
    - Specific region
- Database contains current world population numbers, including for regions.
- The system has the functionality to sort countries based on their population sizes (largest to smallest)

### Success End Condition

A report is available for the Business Development team to view top population data and use this in reporting potential locations for a new global expansion branch.

### Failed End Condition

No population data is found and a report cannot be produced.

### Primary Actor

Business Development manager

### Trigger

A request for the business to create a new global distribution branch in a profitable country they have not yet expanded to.

## MAIN SUCCESS SCENARIO

1. Business Development team receives a request to find highly populated countries in a region and pinpoint a country to open a new company branch in.
2. Business Development manager extracts population data for the region.
3. Business Development manager recommends a new country with a high population in their report, where they would likely see increased financial benefit if the organization began distributing within the country.

## EXTENSIONS

1.**Area does not exist**:
- Business Development team informs the requester that the area data was requested for does not exist.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0