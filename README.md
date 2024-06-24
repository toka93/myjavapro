# How to setup the project on Eclipse?

- Install JDK 20: <br />
https://www.oracle.com/eg/java/technologies/downloads/
https://www.codejava.net/java-se/install-oracle-jdk-20-on-windows

- Install Maven: <br />
https://phoenixnap.com/kb/install-maven-windows
- Install Eclipse IDE: <br />
https://www.eclipse.org/downloads/
- Open Eclipse and open Help List and choose Install New Software , Search for TestNG and Install it.
- Add Lombok : https://projectlombok.org/setup/eclipse
- Open file List and choose Open project from file system and choose project directory 
- Run this command: setx _JAVA_OPTIONS "-Dtestng.dtd.http=true" on cmd.





# Running Configuration :

- Profiles folder conatins .properties files, one for each environment. <br />
- Each file contains : <br />
#Application data <br />
 AppURL=https://github.com/ <br />
 username= <br />
 password= <br />
#DataBase data <br />
 DBclass= <br />
 DBHost= <br />
 DBuser = <br />
 DBpassword = <br />
 Schema= <br />
#running configuration <br />
 Browser = chrome <br />
#local running in local machine , remote if running in pipeline <br />
 Type=local <br />
 Headless=false <br />
 seleniumHubHost=tcp://<host>:4444 <br />
#AWS Credentials <br />
AccessKey= <br />
SecretKey= <br />
#Buckets <br />
Bucket= <br />
#APIs <br />
BaseURL=https://jsonplaceholder.typicode.com/ <br />
logRequest=all <br />
logResponse=all <br />
#securitykey <br />
Key=ItworxInhousekey <br />

   
  

# Test Data:

- TestData folder contains all data needed to run scripts 
  - TestData.xlsx 
  
 
# Ways for running without IDE:
- Open CMD
- CD to the directory contains the project
- Run from cmd (maven should be installed) using mvn clean test + any property, in this case it will run suite file in surfire plugin in pom.xml
- mvn test -DpropertyFile=profiles/syst.properties   for system environment or mvn test -DpropertyFile=profiles/dev.properties  for dev environment
- To run using different configuration other than the ones in .properties file :
  mvn test -D(name of property to update)= value

# After Every Run : 
- Report ,Log and Screen-shots for every test will be generated.
- Reports contains Screen-shots for every test in ExtentReport folder and logs in logs/AppLog.log




# For UI tests:

- Create classes for each page in pages package(should extend PageBase).
- Create matching json files in object repo folder inside resources to add webelements.
- Create tests classes in the tests package(should extend BaseConfiguration).
- Add test-data in TestDate folder.
- Edit suite file with test classes names.


# For API tests:

- Create models in models package for your requests and responses if needed.
- Add json schemas in jsonschemas folder.
- Start building request calls using builder design pattern.


# References:

- https://www.educative.io/answers/what-are-the-solid-principles-in-java
- https://www.digitalocean.com/community/tutorials/builder-design-pattern-in-java


# Configure Project on Eclipse:
 
- RightClick Project then choose Build Path then Configure Build Path and Add Libraries matching as the screenshot below.

![build path](https://itworx.sharepoint.com/Shared%20Documents/Forms/AllItems.aspx?viewpath=%2FShared%20Documents%2FForms%2FAllItems%2Easpx&OR=Teams%2DHL&CT=1690893986920&clickparams=eyJBcHBOYW1lIjoiVGVhbXMtRGVza3RvcCIsIkFwcFZlcnNpb24iOiIyNy8yMzA3MDMwNzMzMCIsIkhhc0ZlZGVyYXRlZFVzZXIiOmZhbHNlfQ%3D%3D&id=%2FShared%20Documents%2FTest%20Automation%2FJava%2Fbuildpath%2Epng&viewid=10386c7f%2D695c%2D4935%2Dbe58%2D17e5a996805c&parent=%2FShared%20Documents%2FTest%20Automation%2FJava)


- Build Project :  RightClick Project then choose maven then maven update project.

![build project](https://itworx.sharepoint.com/Shared%20Documents/Forms/AllItems.aspx?viewpath=%2FShared%20Documents%2FForms%2FAllItems%2Easpx&OR=Teams%2DHL&CT=1690893986920&clickparams=eyJBcHBOYW1lIjoiVGVhbXMtRGVza3RvcCIsIkFwcFZlcnNpb24iOiIyNy8yMzA3MDMwNzMzMCIsIkhhc0ZlZGVyYXRlZFVzZXIiOmZhbHNlfQ%3D%3D&id=%2FShared%20Documents%2FTest%20Automation%2FJava%2Fbuildproject%2Epng&viewid=10386c7f%2D695c%2D4935%2Dbe58%2D17e5a996805c&parent=%2FShared%20Documents%2FTest%20Automation%2FJava)

- check force update only when applying changes to pom.xml file.


# Details on the project: 

- Project is built using java 8 , maven , selenium webdriver and TestNG. <br />
- Project also uses extent report and log4j for logging and reporting. <br />
- Project has utilities for API testing and reading from excel and data base and aws connections.<br />
- Project is deigned according to solid java prinicples as a hybrid testing framework , that support the following : <br />
  - Parallel testing.<br />
  - Data Driven testing.<br />
  - Functional testing for web apps.<br />
  - Integration testing for backend services.<br />
  

