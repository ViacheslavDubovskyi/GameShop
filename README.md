🎮 **GameShop**

This project is a layered console-based solution designed to simplify game catalog management for small websites. 
It leverages PostgreSQL with JDBC and HikariCP to perform efficient CRUD (Create, Read, Update, Delete) operations on a games table.

📌 **Features**

* Add new games to the catalog (Create)
* View game list (Read)
* Update game information (Update)
* Remove game records (Delete)
* Some filter and sorting methods
* Save and load game data via database persistence

🏗 **Architecture**

The project follows a Repository-Service-Console architecture, ensuring a clean separation of concerns:
* Controller: Handles user input and interactions (e.g., GameCard.java, GameController.java, GameValidator.java).
* Model: Defines the data structure (e.g., Game.java).
* Repository: Manages data access and persistence (e.g., GameRepository.java, GameRepositoryImpl.java, SQLQueries.java).
* DAO: Data Access Object layer for database operations.
* Service: Contains business logic (e.g., GameService.java, AppMessages.java).
* Console: Provides the command-line interface (e.g., ConsoleApp.java).

🛠️ **Technologies**

* Language: Java 21
* Database: PostgreSQL
* Connection Pooling: HikariCP
* JDBC: For database interactions
* Testing: JUnit with manual Mockito mocks

🧪 **Testing**

The project includes unit tests using JUnit and manual Mockito mocks to ensure reliability and functionality of the CRUD operations.

⚙️ **Build & Run**

The project is built with Maven.

Build
```
mvn clean install
```
Run
```
mvn exec:java -Dexec.mainClass="org.example.Main"
```
🗄️ **Database Configuration**

Make sure PostgreSQL is installed and running.

Create a database, for example:
```
CREATE DATABASE gameshop;
```

The application uses the following default connection settings (defined in code):

**URL**: jdbc:postgresql://localhost:5432/gameshop

**Username**: postgres

**Password**: postgres
