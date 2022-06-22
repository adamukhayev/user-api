# User  API
## Build instructions

  PostgresDB service - main database to store data

### DB migrations
Done by [Flyway](https://flywaydb.org) on application start see `src/main/resources/db/migration`

#### Commands
Start dev environment (Postgre)

`docker-compose up` or `docker-compose up --build`

Stop dev environment
 
`docker-compose down`

#### API usage

##### API swagger documentation
1. http://localhost:8081/swagger-ui.html
2. Login: `admin@mail.ru`
   Password: `admin`. to get a token.
   
3. administered token example:
   `eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBtYWlsLnJ1Iiwicm9sZSI6IkFETUlOIiwiaWF0IjoxNjE3MzgzOTUxLCJleHAiOjE2MTc5ODg3NTF9.SjtBF7iCVxNKZpn-N5yuoBBm_QK5kbxAiaFvbuNOvxw`
   
4. A user with the ADMIN role has access to all endpoints


### Intellij IDEA Docker Plugin
Docker Integration Plugin can be installed to simplify Docker operations.

1. Go to `Settings` -> `Plugins` -> `Browse Repositories`.
2. In the Search box type `Docker`. 
3. In search results select `Docker Integration Plugin`
4. Click `Install`


