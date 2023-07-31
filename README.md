# Practical Test for Boku: Build & Deploy Instructions


## Step 1: Build Your Spring Boot Application
To begin, open the command prompt or terminal and navigate to the root directory of the Spring Boot project.

Build the application using Maven with the following command:
    'mvn clean package'

This will compile the code, run tests, and package the application into a WAR file in the 'target' folder of the Spring Boot project.


## Step 2: Deploy the Spring Boot Application to Tomcat
Locate the generated WAR file of the Spring Boot application in the 'target' directory.

### Copy the WAR file to Tomcat's 'webapps' directory. 

For example, if the WAR file is named 'boku-0.0.1.war', you can copy it to the Tomcat webapps directory using the command:
    'cp target/boku-0.0.1.war <path-to-tomcat>/webapps/'

Replace <path-to-tomcat> with the actual path to your Tomcat installation.

**Note:** For this project, it says that we have to access the application as the root context at 'http://localhost:1337/' (without a context path). To achieve this, rename the 'boku-0.0.1.war' file to 'ROOT.war'. 
Before copying the 'ROOT.war' file to the webapps folder, ensure that you remove any existing 'ROOT.war'(file for the TOMCAT Homepage) file and 'ROOT' folder from the webapps directory. (This is only necessary if you want to run the app in the ROOT context). 
If you prefer to run the app with a specific context path, you can access it using 'http://localhost:1337/<context-path>/'. 
For instance, if the WAR file is named 'boku-0.0.1.war', the app can be accessed using 'http://localhost:1337/boku-0.0.1/'.

### Start or restart the Tomcat server. 

If you are using Windows, use the following command:
    '<path-to-tomcat>\bin\startup.bat'

Again, replace <path-to-tomcat> with the actual path to your Tomcat installation.


## Step 3: Access the Deployed Spring Boot Application
Once the Tomcat server is up and running, open a web browser and navigate to your application's URL:

If you set a context path in Step 2, access your application using:
    http://localhost:1337/<context-path>/

If you deployed your application as the root context (without a context path), access it using:
    http://localhost:1337/


## Step 4: Send requests from terminals to see the expected results
To test the application, you can send requests from the terminal using curl:

Example of operation:

Terminal 1:
curl -H "Content-Type: application/json" -d "{\"inputValue\": \"4\"}" http://localhost:1337/
// No output

Terminal 2:
curl -H "Content-Type: application/json" -d "{\"inputValue\": \"7\"}" http://localhost:1337/
// No output

Terminal 3:
curl -H "Content-Type: application/json" -d "{\"inputValue\": \"end X\"}" http://localhost:1337/
// 11 X as the output

Note: I encountered a 415 error (Unsupported Media Type) when running the command curl -d '4' http://localhost:1337/'. 
To resolve this, I updated the command in the example provided above. Please use the updated commands when testing the application.
