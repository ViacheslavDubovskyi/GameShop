🎮 **GameShop**

A console-based application to simplify game catalog management for small websites. 
Implemented PostgreSQL persistence using Hibernate ORM. In a separate branch, integrated JDBC with HikariCP as an alternative solution.

📌 **Features**
+ CRUD operations for games;
+ Some filter and sorting methods;
+ Console controller for user interactions;
+ PostgreSQL persistence with Hibernate / JDBC (jdbc branch);
+ HikariCP for connection pooling (jdbc branch).

🔄 **Hibernate**
- Implemented persistence using Hibernate Session;
- Mapped the Game entity using annotations (@Entity, @Table, @Id, etc.);
- Implemented repository layer using Hibernate queries (HQL);
- Maintained existing business logic and console interface;
- Supports full CRUD operations via Hibernate sessions.

🏗 **Architecture**
The Gameshop project follows a layered Repository-Service-Controller architecture with clear separation of concerns:

+ Model Layer: represents the game entity with fields like title, genre, and price (Game.java);
+ Controller Layer: handles user commands and interactions, validates game data (GameController.java);
+ Service Layer: Contains business logic connecting controllers with repositories;
+ Repository Layer: performs CRUD operations on the database (GameRepositoryImpl.java);
+ Console Layer: manages the console user interface and flow (ConsoleApp.java);
+ Configuration: manages database connections using Hibernate (SessionFactoryImpl.java) / HikariCP (ConnectionPool.java).

🛠️ **Technologies**
+ Language: Java 21
+ Database: PostgreSQL
+ Database Access: Hibernate ORM / JDBC with HikariCP (connection pooling)
+ Build Tool: Maven
+ Testing: JUnit, in-memory mock repository

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
