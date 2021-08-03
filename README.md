# comepamhelloweb
A simple project for automating tests scenarios for [Pastebin](https://pastebin.com/) web application.
#### Description of files used in the project:
[AbstractPage:](./src/test/java/AbstractPage.java) An parent PageObject that all classes should inherit to be able to perform WebDriver tests.

[PasteBinTest:](./src/test/java/PasteBinTest.java) A class that wires all test methods together.

[HomePage](./src/test/java/HomePage.java) and [CreatedPastePage:](./src/test/java/CreatedPastePage.java) A form classes which include the locators, variables and methods to perform test scenarios.

#### Tools:
ChromeDriver version 91.0.4472.101

Java 1.8

TestNg 7.1.0

Selenium 3.141.59

Maven 3.0.0

Allure 2.14

1. Download and set up JAVA:

- Navigate to 
https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html

- Select the appropriate JDK version and click Download.

- Set up JAVA_HOME system environment

##### for MacOS:

Execute the following Terminal commands:

`nano ~/.bash_profile`

`export JAVA_HOME=/usr/libexec/java_home -v <version>`

`export PATH=$JAVA_HOME/bin:$PATH`

`source ~/.bash_profile`

##### for Windows:

- Right click My Computer and select Properties.
- On the Advanced tab, select Environment Variables, and then edit JAVA_HOME to point to where the JDK software is located, for example, 

C:\Program Files\Java\jdk1.8.0_02

- Append JAVA_HOME/bin to the PATH. For the above example it will be

C:\Program Files\Java\jdk1.8.0_02/bin

3. Download ChromeDriver from

https://chromedriver.chromium.org/downloads

- Set up environment variable for chromedriver

##### for MacOS:

`nano ~/.bash_profile`

`export PATH=$PATH: path/to/executable/chromedriver`

`source ~/.bash_profile`

##### for Windows:

- Right click My Computer and select Properties.
- On the Advanced tab, select Environment Variables, and then specify PATH where the chromedriver executable is located.

4. Clone the project

`git clone https://github.com/alenakardash/comepamhelloweb`

5. Download and set up Maven

- Navigate to https://maven.apache.org/download.cgi
- Download the binary and unpack it.
- Add MAVEN_HOME to system variables

##### for MacOS:

`nano ~/.bash_profile`

`export M2_HOME=path/to/maven`
`export PATH=$PATH:$M2_HOME/bin`

`source ~/.bash_profile`

##### for Windows:

- Right click My Computer and select Properties.
- On the Advanced tab, select Environment Variables, and then edit MAVEN_HOME to point to where the maven is located.
- Add {maven home}/bin to the Path

5. Download Allure framework

##### for MacOS:

`brew install allure`

##### for Windows:

To install Allure, download and install Scoop and then execute in the Powershell:

`scoop install allure`
 
6. Navigate to comepamhelloweb directory and run:

`mvn clean test`

7. To generate an Allure report run:

`allure serve /home/path/to/project/allure-results/`