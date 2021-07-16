# Academy QA 2021

* [Description](#description)
* [Getting Started](#getting-started)
	* [Setting global environment variables on Windows 10 and Windows 8
](#setting-global-environment-variables-on-windows-10-and-windows-8
)
* [Project Structure](#project-structure)
* [Setup and Run](#setup-and-run)
* [Reporter and Logs](#reporter-and-logs)


## Description
This project is a test suite with several cases for https://www.saucedemo.com/ <br>
These cases are described in the document "CasosPrueba" and were made on based of the document "Requerimientos".

The following description is for a Windows OS, if you have any questions, please contact the author.

## Getting Started
   *Download and decompress this project
   *Installing [Intellij Idea](https://www.jetbrains.com/es-es/idea/) 
   * Installing [Java 8](https://www.oracle.com/mx/java/technologies/javase/javase-jdk8-downloads.html
   * Download and decompress[Maven 3.8] (https://maven.apache.org/download.cgi)
   * Installing Selenium Dev
	
The project has default data in testData.properties, which is located in src/main/resources/, if you want to change some parameters, please do it in the file mentioned before.

<br>

### Setting global environment variables on Windows 10 and Windows 8
1. Search and select System (Control Panel).
2. Click on the Advanced system settings link and then click Environment Variables. Under the section User Variables, click New and add te following variable:
```
*name*: JAVA_HOME
*value*: C:\Program Files\Java\jdk1.8.0_111
```
Then add a new variable again:
```
*name*: MAVEN_HOME
*value*: *{insert path of maven directory}*\apache-maven-3.8.1
```
3. Select the Path environment variable to edit, and click Edit button, add the following line at the end of the list.
```
%MAVEN_HOME%\bin
```
4. Under the section System Variables, click on Path environment variable to edit, and click Edit button, add the following line at the end of the list.
```
%MAVEN_HOME%\bin
```
```
%JAVA_HOME%\bin
```
5.Click on accept button.
6. Check if everything is correct with the next command in terminal.
```
mvn -v
```
Output:
```
Apache Maven 3.8.1 (05c21c65bdfed0f71a2f2ada8b84da59348c4c5d)
Maven home....
Java version: 1.8.0_111, 
Default locale:...
OS name: ..
```
For java:
```
java -version
```
Output:
```
Java version: 1.8.0_111, 
```
Now the project could be opened, edited and run by using Intellij or run through the terminal with maven.

## Project Structure
 ![ProjectStructure](/img/im3.png)

## Setup and Run
To execute a test, do the following:
*Execute one test case by Intellij, click on the next icon.
 ![Execute one case](/img/im1.JPG)

*Execute the completed class, click on the next icon.
 ![Execute completed class](/img/im2.JPG)

*Execute by using the terminal, with the following command.
```
mvn clean test -DsuiteXmlFile=testSuiteDemo.xml
```
The command above is currently running the entire class, create a new xml file in case you want to change the test.

## Reporter and Logs
After executing the class or a test case, a graphical report is generated, which can be viewed in a browser; on the other hand, a log file is created within the Log4j folder.