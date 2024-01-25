# Supply Chain Project

This project has been built to help improve my understanding of Kotlin, project architecture and design and http4k. 
<br/><br/>
The tasks I am completing are originally listed [here](https://github.com/aceakash/programming-exercises/blob/main/supply_chain.md)

## ✏️ Table of Contents
- [User Stories](#user-stories)
    - [User story 1](#user-story-1)
    - [User story 2](#user-story-2)
    - [User story 3](#user-story-3)
- [Getting Started](#getting-started)
- [Dependencies](#dependencies)
- [Technical Notes](#technical-notes)
- [Assumptions](#assumptions-)

## User Stories

### User story 1

As an admin at a top-level customer org

I want to view a list of my direct suppliers

### User story 2

As an admin at a top-level customer org

I want to get the details of a direct supplier that I specify by ID

### User story 3

As an admin at a top-level customer org

I want to add a direct supplier to my supply chain

## Package
```
./gradlew distZip
```
(not sure if necessary)

## Getting Started
1. Clone this repository:<br/>
``` git clone https://github.com/leah-massey/Supply-Chain-Project```
<br/><br/>
2. Compile the project<br/>
```./gradle build```


## Dependencies
- Http4k Core: 5.13.0.0
- JSON: 20231013
- Jackson Module for Kotlin: 2.13.0
- Kotlin Standard Library: 1.9.22
- Http4k Testing Approval: 5.13.0.0
- Http4k Testing Hamkrest: 5.13.0.0
- JUnit Jupiter API: 5.10.0
- JUnit Jupiter Engine: 5.10.0




## Technical Notes
- All endpoints are specified in the file [here](src/resources/Endpoints.md)

## Assumptions 
 
- The userId is stored as in the Request Header under "User-Id"


