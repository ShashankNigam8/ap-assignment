# My Spring Boot Application

Welcome to my Spring Boot application!

## Getting Started

1. Clone the repository:

   ```bash
   git clone https://github.com/ShashankNigam8/ap-assignment.git
   cd YourRepository

2. Build the project:
   ./gradlew build

3. Run the application:
   ./gradlew bootRun

4. Access the application:
   1.Open your web browser and go to http://localhost:8080/swagger-ui/index.html
   2. You will see below screen. 
   ![img.png](img.png)
   2. Test the endpoints: To test endpoint first you need to create token, without token you will not be able to access any endpoint.
   3. Curl to create token :
      `curl -X 'POST' \
      'http://localhost:8080/token' \
      -H 'accept: */*' \
      -H 'Content-Type: application/json' \
      -d '{
      "userName": "username",
      "password": "password"
      }'`
   4. Once Token is generated, authorize the swagger-ui. You can click on authorize button and pass the token.
   5. Curl to fetch county by name :
       `curl -X 'GET' \
      'http://localhost:8080/api/country/usa' \
      -H 'accept: */*' \
      -H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VybmFtZSIsImV4cCI6MTY5MjUzMjAxNH0.u2JWcbGuW7nuE0NURDjguOkzGYSDCteEKQDKFygvWDIGXb7msUAhYLc14EhZLg0hHGTm6rEiWfP3_4ZHuu7gMA'
`
   6. Curl to fetch countries by different filters like population,area and language.
      `curl -X 'GET' \
      'http://localhost:8080/api/countries/?population=10000000&area=70000&page=0&size=100&sortOrder=desc' \
      -H 'accept: */*' \
      -H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VybmFtZSIsImV4cCI6MTY5MjUzMjAxNH0.u2JWcbGuW7nuE0NURDjguOkzGYSDCteEKQDKFygvWDIGXb7msUAhYLc14EhZLg0hHGTm6rEiWfP3_4ZHuu7gMA'`


   7. Stopping the application:
      To stop the application, press Ctrl+C in the terminal where the application is running.


Additional Notes
1.Java 17 and Spring Boot 3+ are required.
2.I have used OpenApi which has swagger support.
3.Currently there is only one valid credentials the is {"username" , "password"} as mentioned in above curl request.
4.For any issues or questions, feel free to open an issue on this repository.
