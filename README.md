# Microservice
Sample spring boot microservice

# 1. Start mongo db server
Navigate to project folder 'microservice' and run command: $ docker-compose up

# 2. Start product-review-service
Navigate to microservice/product-review-service and run command: $ ./mvnw spring-boot:run

# 3. Start product-service
Navigate to microservice/product-service and run command: $ ./mvnw spring-boot:run

# Pending tasks
- Unit tests
- Security for write operations
- Create base project with common files and add dependency to both projects
- Implement caching
- Configurable service URLS
- Swagger documentation

