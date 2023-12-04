SpaceX WebApp
==============================

* * 
* This is meant to be a simple application to show my knowledge and skill as a programmer. 
* Project began on Nov 30th and is complete as of Dec 4th, about 5 days of time working a couple hours a day.
* A number of items were NOT configured to honor the constraints of this project. They will be listed in their own 
* section below.
* 
* One major missing component of requirements is the tying of launches to rockets, which I will address now.
* BACK END
* Step 1: Create array of unique ids given by each launch object.
* Step 2: Perform calls to rockets api and create another array with the id and name of each.
* Step 3: Add a method to Launches object which will match the id of a given rocket with its name.
* FRONT END
* Step 4: Modify launches.html table to display the rocket name on the list button. When clicked, it reveals 
* nested rows of launches. Each launch date can be clicked again to reveal the remaining information.
* WHY NOT PRESENT?
* Out of time as my current job is picking up work significantly. I expect these modifications would have taken me
* another "day" of work given my current rate.
* * 

COMMANDS
==============================
- ./mvnw clean
- ./mvnw spring-boot:run
- ./mvnw test

TECHNOLOGIES USED
==============================
* Java 17
* Spring-Boot (MVC, Web, Security)
* Thymeleaf
* FastJSON Alibaba
* BootStrap
* JavaScript
* HTML
* CSS
* Junit

FURTHER COMMENTS, NEXT STEPS
==============================
SQL
* Not implemented as of this time. Would be required if client wants to store the data on business end.
* SQL tables would consist of Rockets and Launches, a column for each point of essential information.
HTTPS
* Security certificate not implemented as of this time. An essential security configuration in this day.
* But was opted against for the limited nature of a project that will only exist for local use.
LOGIN
* Based on given information a login feature did not seem to be required, and would require more overhead
* related to session management and storing of given user data. May tie back into needing a SQL table to store users.
WEBPACK > CDN
* The CDN for Bootstrap is convenient for the purpose of an exercise like this. But the effort should be done
* to provide Bootstrap from the host machine and not need to make an external call. As that is a security risk.
Expanded Objects
* FastJSON Alibaba is the only "unusual" inclusion as it allowed me to skip the matter of creating detailed objects
* to hold launches and rockets which each have many data points. The package allows me to treat the received JSON
* as a java object innately but this can be limiting in how we apply business logic.
Expanded Test Cases
* More test cases can be added to cover more situations, such as ensuring compareTos are working as intended,
* or that certain passed in data would appear in a way expected on screen.

ASSIGNMENT DETAILS
==============================
General
● Develop a Java Webapp
● Utilize a Java framework such as Spring or similar
● Consume the SpaceX RESTful API within the Java Web Application and return results to
be displayed in the browser
● Style and display information in an effective manner. Use the CSS framework of your
choice such as Bootstrap.
SpaceX Rockets
Using the /rockets SpaceX API endpoint:
● Retrieve and display rocket information on the front-end
● SpaceX API is consumed in the Java Webapp
● Include sort functionality within the Java Webapp, allowing the user to manipulate the
sort field and direction from the front-end interface

Using the /launches API endpoint:
● !For each Rocket, display a list of launches associated with that rocket
● !List of launches is specific to a particular rocket
● SpaceX API is consumed in the Java Webapp

Criterion
Some of the things we will be looking for (in no particular order):
1. Basic requirements have been met
2. Code is properly documented
3. Code adheres to standard security guidelines
4. Code adheres to standard best practices
5. Proper syntax
6. Proper usage of Java frameworks
7. No major bugs found in end-product

Resources
SpaceX API: https://github.com/r-spacex/SpaceX-API/wiki