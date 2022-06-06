# vpark Parking System
## How to Run
1. clone https://github.com/antonymsur/vpark.git
2. cd vpark
3. mvn package #will run all tests including the tests mentioned as example use cases in the pdf
4. java -jar target/vpark-s1-0.0.1.jar #will run the cli with usage details

## Usage
===============VPark Parking System=================
Actions: 
        1. create  : create new parking lot
                1 arg : parking lot type either of 'mall,stadium,airport'
                
        2. preport : parking lot report , TODO Yet to implement
        
        3. park    : park vehicle in one of the slots available based on vehicle type
                2 args : one of the values from 'bike,lmv,hmv'
                
        4. leave   : leave the parking lot
                2 args :  one of the values from 'bike,lmv,hmv'
                          Ticket Id 
                          
        5. vstatus : vehicle status  
                2 args :  one of the values from 'bike,lmv,hmv'
                          Ticket Id 
                          
        6. close     : application close


## Technologies Used:
Core Java - POJO's
Unit Test using junit
Build using Maven
jacoco for code coverage - Not completely covered
checkstyles

