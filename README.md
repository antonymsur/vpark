# vpark Parking System
## How to Run
1. clone https://github.com/antonymsur/vpark.git
2. cd vpark
3. mvn package #will run all tests including the tests mentioned as example use cases in the pdf
4. java -jar target/vpark-s1-0.0.1.jar #will run the cli with usage details

## Usage
===============VPark Parking System=================
        Actions: 
        create  : create new parking lot
                args : parking lot type either of 'mall,stadium,airport'
        preport : parking lot report , TODO
        park    : park vehicle in one of the slots available based on vehicle type
                args : one of the values from 'bike,lmv,hmv'
        leave   : leave the parking lot
                args :  one of the values from 'bike,lmv,hmv'
                        Ticket Id 
        vstatus : vehicle status  
                args :  one of the values from 'bike,lmv,hmv'
                        Ticket Id 
        close     : application close


## Technologies Used:
Core Java - POJO's
Unit Test using junit
Build using Maven
jacoco for code coverage - Not completely covered
checkstyles

