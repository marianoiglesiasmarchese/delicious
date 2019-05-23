## Delicious 

Backend implementation is a REST API with OAuth2 authentication, is only reachable through the following postman collection.

## How to start the app

1. With a terminal, go to delicious project folder and there run.

    ```
    ./gradlew build
    ```

2. Once dependencies were downloaded and the application is successfully builded, go to __/delicious/build/libs/__ there should be present a file called __delicious-0.0.1-SNAPSHOT.jar__.
   
3. In that place, with the terminal run.

    ```
    java -jar delicious-0.0.1-SNAPSHOT.jar    
    ```   

## Postman collection to test each endpoint

This collections have defined authentication keys and a request every each endpoint.

    https://www.getpostman.com/collections/adabcdda44b31614c4f6

## How to use postman 

1. In Postman, go to __import__ option, there chose __import from link__ tab and introduce there the previous collection link. 
