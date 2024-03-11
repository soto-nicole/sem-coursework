# USE CASE: 20 Produce a Report on the Top N Populated Capital Cities in the world

## CHARACTERISTIC INFORMATION

### Goal in Context

As a Global Marketing manager, I want to produce a filtered report on the top populated capital cities in the world so that I can prioritise areas which experience high volumes of tourism and foot traffic to decide where set up a campaign of eye-catching advertisements promoting our business.

### Scope

Company.

### Level

Primary task.

### Preconditions

- We know the area we want to limit population data to:
    - Global: All cities in the world
- Database contains current world capital cities population numbers.
- The system has the functionality to sort capital cities based on their population sizes (largest to smallest)

### Success End Condition

A report is available for the Marketing team to view the capital cities with the highest populations in the world to enable the team to select strategic locations for their advertisement campaigns. 

### Failed End Condition

No population data is found and a report cannot be produced.

### Primary Actor

Global Marketing Manager

### Trigger

A request for the business to identity the key capital cities for targeted on-street advertisement campaigns with prioritisation to the capital cities with the most pedestrian and tourist traffic.

## MAIN SUCCESS SCENARIO

1. Global Marketing Manager receives a request to find the capital cities with the most pedestrian and tourist traffic in the world.
2. Global Marketing Manager extracts population data for the required areas.
3. Global Marketing Manager recommends a new capital city with a high population and pedestrian traffic in their report, where they would likely see increased financial benefit if the organization began advertising within the capital city.

## EXTENSIONS

1.**Data does not exist**:
  - Global Marketing Manager informs the requester that the necessary data does not exist within the database.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0