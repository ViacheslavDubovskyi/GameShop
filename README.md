🎮 **GameShop**

A layered console-based solution designed to simplify game catalog management for small websites.
It allows users to efficiently perform CRUD operations on a games database using a clean, modular architecture.

📌 **Features**
+ CRUD operations for games;
+ Some filter and sorting methods;
+ Console controller for user interactions;
+ PostgreSQL persistence with JDBC;
+ HikariCP for connection pooling.

🏗 **Architecture**

The Gameshop project follows a layered Repository-Service-Controller architecture with clear separation of concerns:

+ Model Layer: represents the game entity with fields like title, genre, and price (Game.java);
+ Controller Layer: handles user commands and interactions, validates game data (GameController.java);
+ Service Layer: Contains business logic connecting controllers with repositories;
+ Repository Layer: performs CRUD operations on the database (GameRepositoryImpl.java);
+ Console Layer: manages the console user interface and flow (ConsoleApp.java);
+ Configuration: manages database connections using HikariCP (ConnectionPool.java).

🛠️ **Technologies**
+ Language: Java 21
+ Database: PostgreSQL
+ Database Access: JDBC with HikariCP (connection pooling)
+ Build Tool: Maven
+ Testing: JUnit, Mockito (manual mocks)

⚙️ **Build & Run**

Configure the Database:

Create a PostgreSQL database named gameshop.Credentials:
```
url=jdbc:postgresql://localhost:5432/gameshop
username=postgres
password=postgres
```
The project is built with Maven.

Build
```
mvn clean install
```
Run
```
mvn exec:java -Dexec.mainClass="org.example.Main"
```
