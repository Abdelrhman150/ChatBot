# Hospital Management System
Welcome to the **Hospital Management System**! This is a comprehensive Java-based application designed to manage various hospital operations, including patient records, appointments, staff management, and billing. The system incorporates a robust architecture utilizing design patterns, a dedicated API, and a desktop graphical user interface.
## 🏗 Project Architecture
The project is structured into multiple core packages and modules to ensure modularity and separation of concerns:
### 1. `hospital-api` (Backend API)
A Maven-based Java project that serves as the backend API for the hospital management system. It provides RESTful endpoints to interact with the database and handle business logic.
- **Technologies:** Java 17, Jakarta Servlet API, Gson, MySQL/MSSQL JDBC Drivers.
- **Configuration:** Managed via `pom.xml`.
### 2. `JavaApplication10` (Core Application & GUI)
This module contains the primary logic and desktop graphical user interfaces for the system. It is heavily modularized into packages representing different architectural layers:
#### `Package1` (Models & Core Entities)
Contains the core domain models and entities used throughout the application. It employs various design patterns (like Factory and Flyweight) to manage object creation and shared states efficiently.
- **Key Entities:** `Admin`, `Doctor`, `Nurse`, `Patient`, `Secretary`, `Department`, `Room`, `Appointment`, `Bill`.
- **Patterns:** `StaffFactory`, `RoomFactory`, `RoomFlyweightFactory`, `MedicalRecordDecorator`, `PaymentProcessor` (with Adapters for Paypal and Insurance).
#### `Package2` (Controllers & Services)
Handles the business logic, formatting, and communication between the views and the data access layer.
- **Controllers:** `AuthController`, `DoctorController`, `PatientController`, `AppointmentController`, `PaymentController`, etc.
- **Services:** `EmailService`, `SMSService` (with Adapters), `NotificationController`, `PDFFormatter`, `ExcelFormatter`.
#### `Package3` (Data Access Objects - DAO)
Responsible for all database interactions. It abstracts the underlying database implementation from the rest of the application.
- **DAOs:** `DoctorDAO`, `PatientDAO`, `AppointmentDAO`, `RoomDAO`, `BillDAO`, etc.
- **Database:** Includes `DatabaseConnection` and `UserDatabase` logic.
#### `Package4` (User Interface - GUI)
Provides a rich, interactive Desktop GUI for different users of the system (Admins, Doctors, Receptionists, etc.).
- **GUIs:** `LoginGUI`, `DashboardGUI`, `MainMenuGUI`, `DoctorUI`, `PatientFormGUI`, `MedicalRecordGUI`, etc.
## ✨ Key Features
- **User Authentication:** Secure login for Admins, Doctors, Nurses, and Secretaries.
- **Patient Management:** Register patients, view medical records, and update details.
- **Appointment Scheduling:** Book, update, or cancel doctor appointments.
- **Staff Management:** Manage doctors, nurses, administrative staff, and their availability.
- **Room Management:** Handle general, ICU, and private rooms, and track room statuses.
- **Billing & Payments:** Generate invoices, handle room/visit bills, and process payments via multiple methods (Insurance, PayPal).
- **Notifications:** Send alerts and notifications via Email, SMS, or Mobile App using Adapter patterns.
- **Reporting:** Generate comprehensive financial and patient reports.
## 🛠 Getting Started
### Prerequisites
- Java Development Kit (JDK) 17 or higher.
- Maven 3.x+ (for the `hospital-api`).
- A relational database (MySQL or Microsoft SQL Server).
- Your preferred Java IDE (Eclipse, IntelliJ IDEA, or NetBeans).
### Setup Instructions
1. **Clone the Repository:** Clone the source code to your local machine.
2. **Database Configuration:** Update the database connection credentials located in the `DatabaseConnection.java` or `pom.xml` to match your local database setup.
3. **Build the API:** Navigate to the `hospital-api` directory and run `mvn clean install` to resolve dependencies.
4. **Run the Application:** 
   - Open `JavaApplication10` in your IDE.
   - Set `Main.java` or `LoginGUI.java` as your startup class.
   - Run the application to launch the desktop graphical user interface.
## 🧑‍💻 Contributing
Feel free to submit issues, fork the repository, and send pull requests to improve the system!
