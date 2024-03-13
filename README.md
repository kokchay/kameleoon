# Weather Service

This service provides weather information based on city names. It retrieves data from the OpenWeatherMap API and stores it in cache for 10 minutes.

## Running the Service

To run this service, ensure you have Docker installed. Then, use the provided Docker Compose file: `bash` docker-compose up and run spring-boot-application throught your `IDE` or run with 
`maven` : go to the application module, open terminal and run next command: `mvn org.springframework.boot:spring-boot-maven-plugin:run`

# Architecture (hexagonal)
This service adheres to clean architecture principles, with a focus on modularity and separation of concerns. The architecture is organized into the following modules:

### `Domain`:
 Contains the core domain logic and entities related to weather information.

### `Application`:
 Implements application-specific business rules and orchestrates interactions between different modules.

### `REST Adapter`:
 Handles REST API communication and exposes weather endpoints to clients.

### `Hazelcast Adapter`:
 Provides integration with Hazelcast for caching weather data.

### `External Service Adapter`:
 Manages interactions with external services like OpenWeatherMap.

For further information on clean architecture and its benefits, refer to [this](https://en.wikipedia.org/wiki/Hexagonal_architecture_(software)) and [this](https://medium.com/ssense-tech/hexagonal-architecture-there-are-always-two-sides-to-every-story-bc0780ed7d9c) samples


Contributing
Contributions are welcome! Feel free to submit issues or pull requests.

# This is an example of data retrieval from the cache (5ms).
![diff 5ms](https://github.com/kokchay/kameleoon/assets/125590318/5d91c53c-6f74-4594-8c3b-b336d89705fa)

## This is an example of retrieving data from the external service (OpenWeatherMap.org) (490ms).
![diff 490ms](https://github.com/kokchay/kameleoon/assets/125590318/05406577-bd60-4862-9ba6-3cbaa5f74af6)

# Additional useful links
1. [Hazelcast documentation](https://docs.hazelcast.com/hazelcast/5.3/)
2. [Hazelcast Spring framework implementation](https://reflectoring.io/spring-boot-hazelcast/)
3. [Swagger documentation](https://swagger.io/docs/)
4. [Swagger Spring framework implementation](https://www.baeldung.com/spring-rest-openapi-documentation)
5. [Docker documentation](https://docs.docker.com/)
6. [Docker Compose documentation](https://docs.docker.com/compose/)

