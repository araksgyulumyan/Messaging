#Messaging

This is messaging application which consists of 2 modules:
### 1. messaging-service-gateway
This microservice's functionality is to create the message for concrete channel and saves it to the database. <p/>
It has **pom.xml** file with all necessary dependencies included. </p>
It has **application.properties** file with the following db settings: <br/>
Database name: messaging <br/>

### 2. messaging-service-slack
This microservice's functionality is to connect to get the message
for the channel from **messaging-service-gateway** microservice and 
post it to the **Slack application channel** with specified name, using
**slack api token** in **application.properties** file. <p/>
Please add your own **slack api token** to **application.properties** file. <p/>
To run this application, please use this **cURL** in your terminal <p/>

````
curl --location --request POST 'localhost:8080/messages' \
--header 'Content-Type: application/json' \
--data-raw '{
"channelName":"test-project",
"message":"Hello message"
}'

````

To see how this application works, please play the following video: <br/>
<https://youtu.be/WoSw5xlOZGw>

