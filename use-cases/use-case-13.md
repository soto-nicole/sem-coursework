# USE CASE: 13 Produce a Report on the Top N Populated Cities in a continent

## CHARACTERISTIC INFORMATION

### Goal in Context

As a Logistics manager, I want to produce a filtered report on the top N populated cities in a continent so that I can identify the most densely populated cities with the highest number of potential customers, which will help the organization decide where to situate its newest distribution centre to maximise short customer delivery times.

### Scope

Company.

### Level

Primary task.

### Preconditions

- We know the area we want to limit population data to:
    - Specific continent
- Database contains current world city population numbers, including for continents.
- The system has the functionality to sort cities based on their population sizes (largest to smallest).
- System has input box for the user to insert the N top cities.

### Success End Condition

A report is available for X to view population data and use in reporting to wider company.

### Failed End Condition

No population data is found and a report cannot be produced.

### Primary Actor

Logistics manager

### Trigger

A request for the business to open a new distribution centre in a profitable area they have not yet expanded to.

## MAIN SUCCESS SCENARIO

1. Logistics team receive a request to find a highly populated city in a specific continent to place a new distribution branch.
2. Logistics manager extracts population data for the continent.
3. Logistics manager notes a city with a high population in the continent in their report, where most of their customers would likely order from and would benefit from the quick delivery due to proximity of the distribution warehouse.

## EXTENSIONS

1.**Area does not exist**:
- Logistics Manager informs the requester that the area data was requested for does not exist.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0