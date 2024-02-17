# USE CASE: 12 Produce a Report on the Top N Populated Cities in the world

## CHARACTERISTIC INFORMATION

### Goal in Context

As a Logistics manager, I want to produce a filtered report on the top N populated cities in the world so that I can identify the most densely populated cities with the highest number of potential customers, which will help the organization decide where to situate its newest distribution center to maximise short customer delivery times.

### Scope

Company.

### Level

Primary task.

### Preconditions

- We know the area we want to limit population data to:
    - Global: All cities in the world
- Database contains current world city population numbers.
- The system has the functionality to sort cities based on their population sizes (largest to smallest).
- System has input box for the user to insert the N top cities.

### Success End Condition

A report is available for the Logistics team to view the most populated cities in an area and report potential locations to situate the main distribution centre for their new expansion to that area.

### Failed End Condition

No population data is found and a report cannot be produced.

### Primary Actor

Logistics manager

### Trigger

A request for the business to open a new distribution centre in a profitable area they have not yet expanded to.

## MAIN SUCCESS SCENARIO

1. Logistics team receive a request to find a highly populated city in a specific area to place a new distribution branch.
2. Logistics manager extracts population data for the world.
3. Logistics manager notes a city with a high population in the world in their report, where most of their customers would likely order from and would benefit from the quick delivery due to proximity of the distribution warehouse.

## EXTENSIONS

1.**Area does not exist**:
  - Logistics Manager informs the requester that the area data was requested for does not exist.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0