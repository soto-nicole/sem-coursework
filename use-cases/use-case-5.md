# USE CASE: 5 Produce a Report on the Population of all Capital Cities in a given area

## CHARACTERISTIC INFORMATION

### Goal in Context

As a Talent Ambassador, I want to produce a report on the population of all capital cities in a given area so that I can choose where to emphasise our efforts to advertise available careers to the most potential employees.

### Scope

Company.

### Level

Primary task.

### Preconditions

- We know the area we want to limit population data to:
    - Global: All cities in the world
    - Specific continent
    - Specific region within a continent.
- Database contains current world capital cities population numbers, including for continents and regions.
- The system has the functionality to sort capital cities based on their population sizes (largest to smallest)

### Success End Condition

A report is available for the Talent team which lists the capital cities for each specific area, ordered by population form largest to smallest to choose what city they should emphasise their efforts for advertising available careers. 

### Failed End Condition

No population data is found and a report cannot be produced.

### Primary Actor

Talent Ambassador employee and recruitment marketing

### Trigger

A request for the business to gain insights into the population in capital cities to execute targeted recruitment advertising campaigns.

## MAIN SUCCESS SCENARIO

1. Talent Ambassador receive a request to find highly populated capital cities in a specific area to focus on recruitment advertising with the highest potential for attracting new talent. 
2. Talent Ambassador employee pinpoints the specific area of the world to receive the capital city's population data.
3. Talent Ambassador employee extracts population data for the required areas.
4. Talent Ambassador recommends new capital cities with high populations in which the business could see benefit from advertising into, as well as low population capital cities where advertising may not be as effective.

## EXTENSIONS

1.**Area does not exist**:
1. Talent Ambassador informs the requester that the area data was requested for does not exist.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0