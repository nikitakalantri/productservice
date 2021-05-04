## TradeService ##

###### PROBLEM STATEMENT ######
  There is a scenario where thousands of trades are flowing into one store, assume any way of transmission of trades. We need to create a one trade store, which stores the trade     in the following order.
  


 Trade Id  | Version| CounterPartyId| Book Id| Maturity Date| Created Date| Expired
------------- | -------------| -------------| -------------| -------------| -------------| -------------
Tl  | 1| CP-1| B1| 20/05/2020| <today date>| N
T2  | 2| CP-2| B1| 20/05/2021| <today date>| N
T2  | 1| CP-1| B1| 20/05/2021| 14/03/2015| N
T2  | 3| CP-3| B2| 20/05/2014| <today date>| Y


###### There are couples of validation, we need to provide in the above assignment ###### 
- During transmission if the lower version is being received by the store it will reject the trade and throw an exception. If the version is same it will override the existing record.
- Store should not allow the trade which has less maturity date then today date.
- Store should automatically update the expire flag if in a store the trade crosses the maturity date.

Prerequisite :

Make sure Java 8 or higher is installed

Refer the below link to download & install :
https://www.java.com/en/download/help/download_options.html

Run the following command to confirm:

$ java -version
java version "1.8.0_131"
Java(TM) SE Runtime Environment (build 1.8.0_131-b11)
Java HotSpot(TM) 64-Bit Server VM (build 25.131-b11, mixed mode)

##- Java Springboot Application with gradle. ##

TO MAKE CHANGES & RUN, FOLLOW THESE STEPS :

1. Make changes in the appropriate Java files.

2. To compile the project open the command prompt and change the directory so you are in this project's directory then run the following command:

	gradlew clean build
	
3. Run the SpringBoot Application TradeStorageApplication.java which is in the package com.tradeservice

4. Application would start at port 5000.

5. Api spec for using the application can be found in -
      
    api/tradeservice.yaml
    
6. Scheduler runs every 5 minutes to check the expired trades.

7. To run the test cases -
	Execute TradeServiceTest.java with Junit Test

## Validations ##
1. Check if Trade is added.
2. Check if Version is same the list will be updated.
3. Check if Version is low the trade will be rejected.
4. Check if maturity Date is greater than todays date the trade is added.
5. Check if maturity Date is lower than todays date the Trade will not be added.
6. Scheduler is scheduled to run five minute to update the expired records.
7. Check with T1	1	CP-1	B1	20/05/2020	<today date>	N
8. Check With T2	2	CP-2	B1	20/05/2021	<today date>	N
9. Check With T2	1	CP-1	B1	20/05/2021	14/03/2015	N
10. Check Expired T3	3	CP-3	B2	20/05/2014	<today date>	Y