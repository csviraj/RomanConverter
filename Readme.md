# Roman Numeral Converter

Roman Numeral Converter is a spring boot web app that accepts an Arabic Number in the range of 1 to 3999999
and returns an Roman Numeral equivalent for it.

For example:
 Input: 4   
 Output: IV

### How to run
  - Navigate to rundir: 
  $ cd RomanConverter/RomanConverter-run/rundir
  - Run shell script: 
  $./startup.sh
  - Open browser and use URL: http://localhost:8080/health
  - An OK response would denote app is healthy and responding.
  - You can now use URL: http://localhost:8080/romannumeral?query=<Any Number between 1 and 3999999> to get the equivalent roman numeral.
  - Numeral inside () in the output denote Numerals through Venculum addition.
  - Time Taken for each request to process and respond is logged on console and file.
  - Use command: $./shutdown.sh to shutdown the application.
  - Additionally project can be build using mvn clean install

### Engineering and Testing
Technology Used:
  - Used Spring boot with web starter kit (Includes Tomcat) to create the web application.
  - Used Log4j2 for logging in concole and file.
  - Used Maven as project management tool.
  - IntellJ as the IDE
  - JAVA 8

Logic used:
  - For converting the arabic number into roman numeral I make use of a preloaded treemap with key - value pair of Integer to Roman Numeral (String) for those Roman Numerals that involve subtraction such as 4 (IV - where I denotes 1, V denotes 5 and I before V denotes subtraction of 1 from 5)  or 9(IX). Also added to the map are the numerals that have unique symbol assigned to them such as 10(X) or 50 (L).
  - I use the function floorkey(int) provided by the treemap data structure  to look up the highest key less than or equal to the argument. For example for 3999 the floorkey would return 1000.
  - I extract the Roman Numeral equivalent from the map and append it to our output string. I continue to decrement the number with highest floorkey for each iteration until the no is greater than or equal to 1.
  - I used brackets () instead bar to denote Roman Numerals greater than 3999. (Venaculum addition).
  
  Testing
  - Used Junit 5 for performing Unit Testing.
  - Added test for positive cases, boundary conditions and negative cases to verify correct exception handling
  - RestResponseEntityExceptionHandler class is used to handle all exceptions and map them to correct HTTP ERROR RESPONSE with a user friendly message.

Packaging Layout:
 - I used Maven as project management tool
 - RomanNumeral as parent Project
    * RomanNumeral-run consists of generated jar lib and startup and shutdown script. Also contains logfile under logs sub directory.
    * RomanNumeral-service consists of all the core logic that generates RomanNumeral equivalent for an arabic number.
    * RomanNumeral-webapp consists of the Rest Controllers and Main application class . Resources subdirectory consists of log4j2 configuration ofr logging. 
 - Dependencies are mananged in pom.xml. The parent pom uses dependency mangement tag to manage the version accross all child poms for correctness and consistency.
 - I used Junit5, spring-boot-starter-web, Javax, log4j-core, log4j-api as dependencies.