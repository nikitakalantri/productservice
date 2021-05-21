## ProductService ##

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
      
    api/productservice.yaml