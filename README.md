# ShiftLog

ShiftLog is a Java console application for managing equipment
and recording events associated with individual equipment.

## Version

Current version: v0.4

## Features

- Add equipment
- Unique equipment IDs
- List equipment
- Find equipment by ID
- Add events to equipment
- Display event history
- Find events by equipment
- Advanced filtering of events
- Events sorted by date and time
- Event types (MAINTENANCE,FAIL,MESSAGE)
- Event priority (LOW,MEDIUM, HIGH,CRITICAL)
- Find events by priority
- Find events by type
- Basic event statistics
- Full CRUD for equipment
- Persistent event storage and loading
- SQLite database integration using JDBS
- Automatic database initialization

## Technologies

- Java 26
- SQLite
- JDBC
- Maven

## Database

ShiftLog uses SQLite for persistent data storage.

The database and required tables are automatically
created when the application starts.

Equipment and event records are preserved between
application restarts.

The local database file is excluded from Git.

## Requirements

- JDK 26
- Maven
- IntelliJ IDEA (recommended)

## How to Run

1. Clone the repository.
2. Open the project in IntelliJ IDEA.
3. Load the Maven dependencies.
4. Run Main.java.

## Project status

ShiftLog is currently an early console prototype.
The project is being developed while learning Java and will
gradually be expanded with additional functionality.

## Planned

- Edit and delete existing events
- Event change history (audit log)
- Pagination and event navigation
- Role-based access control (Operator,Maintenance,Admin)
- GUI
- User authentication and account manager