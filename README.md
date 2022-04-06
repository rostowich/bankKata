# BANK KATA

## Prerequisites
In order use the API, you have to install the following software in your computer
	- java version 11
	- maven 3.8.5
## Run the unit tests

In order to run all the unit tests written, simply run the command "mvn clean test" in the root folder of the project

## Run the project

To run the project, first generate the jar file using the command "mvn clean package" in the root folder of the projet

After generating the jar file, run the command: "java -jar target/bank_kata-0.0.1.jar" in the root folder of the projet. 
Make sure you are running the right jar file

## Use the API
After deploying the project, we will have a console app where you can call the different API.
A sample account is created with the ID=1 and the balance=0.
You can call those different functions
- DEPOSIT 1 150: To save the amount 150 to the account ID=1
- WITHDRAWAL 1 500 : To retrieve the amount 500 from the account ID=1
- CHECK 1: To consult the account statement having the ID=1
- HISTORY 1: To display the list of all the account operations made in the accountID=1
- EXIT: To exit the application

The API response format is JSON 
