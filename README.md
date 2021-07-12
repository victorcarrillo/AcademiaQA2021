# Academia QA 2021

## Enviroment setup

### For windows

1. Download and install **Google Chrome browser** from [The Google Chrome Browser download page]([Navegador web Google Chrome](https://www.google.com/intl/es-419/chrome/))
1. Download **apache-maven-3.8.1-bin.zip** from [the apache maven project download page](https://maven.apache.org/download.cgi)
2. Extract the downloaded folder on your root folder. You can verify you have done this correctly with the following command
```
ls C:\apache-maven-3.8.1
```
Output should display the contents of your extracted folder.

3. Add this directory to your enviroment variables, by issuing the following command
```
setx MAVEN_HOME "absolute/path/to/your/extracted/folder"
```
Reset the terminal to save changes. You can verify this has been set correctly by issuing the following command on the terminal.

```
echo %MAVEN_HOME%
```
Output should be the location of your extracted folder.

4.  Make sure you have java 8 installed. This can be verified with the following command
```
java -version
```
Output should be your system's Java version.

5) If you do not have it installed you can download **jdk-8u202-windows-x64.exe** from
[The oracle downloads webpage](https://www.oracle.com/mx/java/technologies/javase/javase8-archive-downloads.htmli)	

Install the downloaded file, accepting all default options during installation.
After restarting the terminal, you should be able to locate your java version from the command line.
It is recommended you disable automatic update check on your system.

6) Add the location to your java installation to your enviroment variables. Run the following command.
 ```
	setx JAVA_PATH "absolute/path/to/your/installation"
```

You can verify this was set correctly through the terminal. (Terminal must be reset)
```
echo %JAVA_HOME%
```
Output should be the absolute path to your java installation.

7. Add the recently created variables to the PATH variable by issuing the following commands as administrator and restarting the terminal
```
	setx PATH "%PATH%;%JAVA_HOME%"
	setx PATH "%PATH%;%MAVEN_HOME%\bin"
	setx PATH /M "%PATH%;%JAVA_HOME%\bin"
	setx PATH /M "%PATH%;%MAVEN_HOME%\bin"
```
8. You can verify this has been set correctly by opening a new terminal and issuing the following command. 
```
mvn -v
```
Output should be your Systems maven version, as well as the jdk version

## Test setup and run

1.  Issue the following commands on the terminal
```
cd C:\Your-projects-folder
git clone https://github.com/victorcarrillo/AcademiaQA2021.git
```
2.  You can use a text editor to change the test parameters for the test run. These will be located, from the root of your project:

**src/main/resources/testData.properties**

3. Issue the following command to begin the test
```
mvn test clean -DsuiteXmlFile=testSuite.xml
```
4. The test suite will be executed, and a test log will be created, from the root folder of the project : 

**Log4j/**