# USE CASE: 2 Produce a Report on the Population of all Countries in a continent

## CHARACTERISTIC INFORMATION

### Goal in Context

As a Marketing manager, I want to produce a report showing the population of all countries in a continent so that I can find highly populated countries to pursue further business expansion and marketing to benefit my organization, as well as the least populated where efforts may not be as effective.

### Scope

Company.

### Level

Primary task.

### Preconditions

- We know the area we want to limit population data to:
  - Specific continent
- Database contains current world population numbers, including for continents.
- The system has the functionality to sort countries based on their population sizes (largest to smallest)

### Success End Condition

A report is available for Marketing to view population data and use in reporting to wider company.

### Failed End Condition

No population data is found and a report cannot be produced.

### Primary Actor

Marketing manager

### Trigger

A request for the business to expand into new countries is received by the Marketing team.

## MAIN SUCCESS SCENARIO

1. Marketing team receive a request to find highly populated countries in a continent to expand their organization's customer base.
2. Marketing manager extracts population data for the specified continent.
3. Marketing manager recommends new countries with high populations in a report which the business could see benefit from expanding into, as well as low population countries where sales may not be as effective.

## EXTENSIONS

1. **Area does not exist**:
  - Marketing manager informs the requester that the area data was requested for does not exist.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0