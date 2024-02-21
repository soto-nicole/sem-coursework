# USE CASE: 11 Produce a Report on the Population of all Cities in a district

## CHARACTERISTIC INFORMATION

### Goal in Context

As a Finance manager, I want to produce a report on the population of all the cities in a district so that I can identify the highest and lowest populated cities in that area, identifying where the most and least profit may be made based on potential customer count.

### Scope

Company.

### Level

Primary task.

### Preconditions

- We know the area we want to limit population data to:
    - Specific district
- Database contains current world city population numbers, including districts.
- The system has the functionality to sort cities based on their population sizes (largest to smallest)

### Success End Condition

A report is available for X to view population data and use in reporting to wider company.

### Failed End Condition

No population data is found and a report cannot be produced.

### Primary Actor

Finance team manager

### Trigger

A request to find out which cities have the most and least potential customers in a district, to better hone sales efforts.

## MAIN SUCCESS SCENARIO

1. Finance team receive a request to find the population of cities in a district to identify both good and bad areas for seeking new potential customers.
2. Finance manager extracts population data for a district.
3. Finance manager reports the best and worst cities in a district for future sales endeavours.

## EXTENSIONS

1.**Area does not exist**:
- Finance manager informs the requester that the area data was requested for does not exist.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0