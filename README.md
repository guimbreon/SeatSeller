# SeatSeller

SeatSeller is a Java-based application designed to manage cinema seat reservations efficiently. It provides both graphical (JavaFX) and console interfaces, supporting different user roles and advanced session management features.

## Features

- **User Roles & Authentication**
  - Supports multiple user types: Administrators, Employees, and End Customers.
  - Secure login and role-based access to features.

- **Seat Grid Management**
  - Administrators can create and configure seat grids (“grelhas”) for different cinema rooms.
  - Multiple seat types: Normal, VIP, Love Seat, and Accessible, each with custom properties and pricing.

- **Reservation System**
  - Customers (or employees on their behalf) can select sessions, pick seats, and reserve them.
  - Reservations require credit card validation.
  - Unique reservation codes are generated for each booking.

- **Notifications**
  - Employees can be associated with rooms and receive real-time notifications about reservations.

- **Configuration**
  - The project uses a centralized configuration management system for properties and settings.

## Technologies Used

- Java 8+
- JavaFX for GUI
- Console support for CLI usage
- Singleton and MVC patterns

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or newer
- Maven or Gradle (optional, for dependency management)

### Running the Application

#### GUI Version

1. Navigate to the `gui` directory.
2. Run the main class:

   ```sh
   java gui.Main
   ```

#### Console Version

1. Navigate to the `console` directory.
2. Run the main class:

   ```sh
   java console.Main
   ```

## Project Structure

- `domain/` - Core domain logic (users, reservations, seat types, configuration, etc.)
- `gui/` - JavaFX-based graphical interface.
- `console/` - Console-based application entry point and startup routines.
- `domain/api/` - Interfaces defining system operations and handlers.

## Example Users (Created at Startup)

- **Administrator:**  
  - Username: `admin`  
  - Password: `admin`
- **Employees:**  
  - `zacarias` / `zacarias`  
  - `serafim` / `serafim`
- **End Customers:**  
  - `ana` / `ana`  
  - `maria` / `maria`

## Example Seat Types

- **Lugar Normal:** Typical seat
- **Lugar VIP:** Reclining seat
- **Lugar Love Seat:** Seat without a divider on one side
- **Lugar Acessível:** Wheelchair-accessible seat

## Developers

- Guilherme Soares - n62372
- Vitoria Correia - n62211
- Duarte Soares - n62371
