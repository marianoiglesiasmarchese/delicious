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

2. Select one of the requests, and go to __Authentication__ tab.

3. Click on __Get New Access Token__ button, complete it with the following attributes and click on __Request Token__:

    - Token Name: delicious.com
    - Grant Type: __Authorization Code__
    - Callback URL: __http://localhost:9000/loginSuccess__
    - Auth URL: __http://localhost:9000/oauth2/authorization/google__
    - Access Token URL: __http://localhost:9000/login/oauth2/code/google__
    - Client ID: __774894818075-dbad4ckuftdlfn3vr96kp54he9hv8ea1.apps.googleusercontent.com__
    - Client Secret: __-0NgO7dmS0dnScBo0ZO6XYrh__
    - Scope: e.g. read:org _(default value)_
    - State: State _(default value)_
    - Client Authentication: __Send as Basic Auth header__

    __Once you have OAuth2 token in your environment, you can use it for all the request included in the collection.__

4. On your chosed request, click __send__. It __should login to the application in your first request__, after this first request you can start to use each request with they main propousal. __Your Postman session will be logged in the app so you don't have to worry about this proccess again.__

