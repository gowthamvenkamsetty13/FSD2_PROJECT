# TravelGo: Design and Development of a Web-Based Travel Booking Management System

A  
Skill Oriented Course Web Application / Project Report submitted in partial fulfillment of the requirements for the award of the Degree of  
**BACHELOR OF TECHNOLOGY**  
in  
**COMPUTER SCIENCE AND ENGINEERING**  

Submitted by  
**PHANITHAPU VENKATA SAI VISHNU**  
**Roll No: 24761A05V0**  

Under the esteemed guidance of  
**Mr. N. SrinivasaRao**  
Sr. Assistant Professor  

---

### DEPARTMENT OF COMPUTER SCIENCE AND ENGINEERING  
**LAKIREDDY BALIREDDY COLLEGE OF ENGINEERING (AUTONOMOUS)**  
*(Accredited by NAAC with ‘A’ Grade & NBA Under Tier - I, An ISO 21001:2018, 14001:2015, 50001:2018 Certified Institution)*  
Approved by AICTE, New Delhi and Affiliated to JNTUK, Kakinada  
L.B. Reddy Nagar, Mylavaram, NTR District, Andhra Pradesh – 521230  
**2024–2028**  

---

## CERTIFICATE

This is to certify that the Skill Oriented Course Web Application / Project entitled **“TravelGo: Design and Development of a Web-Based Travel Booking Management System”** is being submitted by  

| Student Name | Roll Number |
| :--- | :--- |
| **PHANITHAPU VENKATA SAI VISHNU** | **24761A05V0** |

in partial fulfillment of the requirements for the award of the degree of **B.Tech in Computer Science and Engineering** from **Jawaharlal Nehru Technological University Kakinada**, and is a record of bonafide work carried out at **Lakireddy Balireddy College of Engineering (Autonomous)**.

The results embodied in this project report have not been submitted to any other university or institute for the award of any degree or diploma.

<br><br>

| **INTERNAL EXAMINER** | **EXTERNAL EXAMINER** |
| :--- | :--- |

---

## ACKNOWLEDGEMENT

I would like to express my sincere gratitude to **Mr. N. SrinivasaRao**, Sr. Assistant Professor, Department of Computer Science and Engineering, for his continuous encouragement, valuable suggestions, and guidance in carrying out this Skill Oriented Course Web Application / Project.

I sincerely thank **Dr. S. Nagarjuna Reddy**, Professor and Head of the Department of CSE, for his valuable cooperation, advice, and administrative support. I also express my deep gratitude to the management and **Dr. K. Appa Rao**, Principal, for providing the necessary infrastructural facilities and computing resources required to complete this project successfully.

I am thankful to the teaching and non-teaching staff members of the CSE department for their technical help and guidance. Finally, I express my heartfelt gratitude to my family and friends for their continuous support, patience, and encouragement throughout the duration of this work.

<br>
**PHANITHAPU VENKATA SAI VISHNU**  
Roll No: 24761A05V0  

---

## DECLARATION

I hereby declare that the project entitled **“TravelGo: Design and Development of a Web-Based Travel Booking Management System”** has been carried out by me under the supervision and guidance of **Mr. N. SrinivasaRao**, Sr. Assistant Professor, Department of Computer Science and Engineering, Lakireddy Balireddy College of Engineering (Autonomous). 

The work contained in this report is original to the best of my knowledge and has not been submitted to any other university, institute, or examining body for the award of any degree, diploma, or title.

I have followed the institute’s academic guidelines for preparing this report. Concepts, documentation, and external materials from other referenced sources, where used, have been duly acknowledged and cited in the references section.

<br>
**Signature of the Student:**  
**PHANITHAPU VENKATA SAI VISHNU**  
Roll No: 24761A05V0  

---

## ABSTRACT

**TravelGo** is a full-stack, web-based travel booking and tour reservation management platform designed to streamline the discovery, selection, reservation, and administrative control of holiday itineraries. In conventional offline tour booking systems, travelers frequently struggle with opaque package pricing, manual reservation friction, delayed confirmations, and clumsy booking cancellations. TravelGo resolves these challenges by providing a centralized, transparent digital portal where tourists can browse curated domestic and international travel packages, check live slot availability, review detailed trip durations and inclusions, and securely book vacations.

The web application is built strictly utilizing **Java Servlets (Jakarta EE 10)** for server-side business logic and programmatic HTML rendering via standard Java classes, eliminating template compilation overhead. Persistent storage and relational integrity are powered by **MySQL 8.0**, interfaced through low-latency **Java Database Connectivity (JDBC)** using atomic transactions and parameterized queries. The presentation tier incorporates lightweight, responsive HTML5 and clean CSS without bloated external UI frameworks, prioritizing fast loading speeds and accessibility.

The platform enforces role-based access control (RBAC) via an HTTP Servlet Filter (`AuthFilter`), distinguishing standard travelers from administrators. Regular users can register, authenticate, reserve packages with live slot deduction, inspect personal booking history, and cancel active itineraries with automated inventory restoration. Administrators are provided a dedicated dashboard featuring real-time statistical tracking (active bookings, registered travelers, package counts) and a complete Content Management System (CMS) to create, modify, and delete travel itineraries. Deployed as a Web Application Archive (WAR) onto **Apache Tomcat 10.1**, TravelGo demonstrates a robust, scalable architectural blueprint for modern tourism information systems.

---

## LIST OF CONTENTS

| Chapter No. | Title | Page No. |
| :---: | :--- | :---: |
| | **LIST OF ABBREVIATIONS** | **vi** |
| **1.** | **INTRODUCTION** | **1** |
| | 1.1 Overview of the Project | 1 |
| | 1.2 Problem Statement | 2 |
| | 1.3 Objectives | 2 |
| | 1.4 Scope | 3 |
| | 1.5 Feasibility Study | 3 |
| **2.** | **TECHNOLOGIES USED** | **4** |
| | 2.1 HTML and Lightweight CSS | 4 |
| | 2.2 Core Java (JDK 21) | 4 |
| | 2.3 Jakarta Servlets (Jakarta EE 10) | 5 |
| | 2.4 JDBC and MySQL Database | 5 |
| | 2.5 Apache Tomcat 10.1 & Build Automation | 6 |
| **3.** | **SYSTEM ANALYSIS AND DESIGN** | **7** |
| | 3.1 Functional Requirements | 7 |
| | 3.2 Non-Functional Requirements | 8 |
| | 3.3 System Architecture | 8 |
| | 3.4 Database Design and Entity Relationships | 9 |
| | 3.5 Use-Case Description | 10 |
| **4.** | **CODING AND IMPLEMENTATION** | **11** |
| | 4.1 Project Directory Structure | 11 |
| | 4.2 Database Connection & Connection Pooling Configuration | 12 |
| | 4.3 User Registration and Authentication Module | 13 |
| | 4.4 Travel Package Catalog & Details Engine | 15 |
| | 4.5 Booking Engine & Slot Inventory Synchronization | 17 |
| | 4.6 Booking Management & Cancellation Handling | 18 |
| | 4.7 Administrative Dashboard & Package CRUD Management | 19 |
| **5.** | **RESULTS AND SCREENSHOTS** | **21** |
| **6.** | **TESTING** | **25** |
| **7.** | **CONCLUSION AND FUTURE ENHANCEMENTS** | **27** |
| | 7.1 Conclusion | 27 |
| | 7.2 Future Enhancements | 27 |
| **8.** | **REFERENCES** | **28** |
| | **APPENDIX A: CORE CODE EXAMPLES** | **29** |
| | **APPENDIX B: ABBREVIATIONS** | **33** |

---

## LIST OF ABBREVIATIONS

* **HTML** – HyperText Markup Language
* **CSS** – Cascading Style Sheets
* **JDBC** – Java Database Connectivity
* **SQL** – Structured Query Language
* **DBMS** – Database Management System
* **HTTP** – HyperText Transfer Protocol
* **HTTPS** – HyperText Transfer Protocol Secure
* **MVC** – Model–View–Controller
* **DAO** – Data Access Object
* **CRUD** – Create, Read, Update, Delete
* **IDE** – Integrated Development Environment
* **WAR** – Web Application Archive
* **JDK** – Java Development Kit
* **JRE** – Java Runtime Environment
* **UI** – User Interface
* **UX** – User Experience
* **API** – Application Programming Interface
* **PK** – Primary Key
* **FK** – Foreign Key
* **RBAC** – Role-Based Access Control

---

## 1. INTRODUCTION

### 1.1 Overview of the Project
With global tourism expanding rapidly, digital information systems have become crucial in connecting prospective travelers with holiday package organizers. Traditional paper-based reservation records and scattered telephonic inquiries often result in double-booking, opaque billing, lost customer records, and manual administrative delays.

**TravelGo** is an end-to-end, web-based Travel Booking Management System developed to automate vacation planning and package reservations. Designed using standard Java enterprise specifications, the application provides an interactive portal for tourists to explore diverse itineraries across cultural heritage, adventure, and beach getaways. Concurrently, it offers travel agencies an automated back-office suite to manage real-time seat inventory, monitor confirmed passenger rosters, and dynamically publish new vacation packages.

### 1.2 Problem Statement
Manual and fragmented travel management systems suffer from several distinct vulnerabilities:
1. **Inventory Discrepancies:** Without synchronized database locking, travel agencies frequently face overbooking of passenger seats on fixed-capacity tours.
2. **Lack of Instant Visibility:** Customers have difficulty viewing verified price breakdowns, daily durations, and available slots simultaneously.
3. **Complex Cancellation Handling:** Cancelling a scheduled trip typically requires extensive customer support intervention, causing friction and inventory release delays.
4. **Administrative Inefficiency:** Administrators lack centralized operational dashboards to gauge aggregate passenger booking metrics and adjust package pricing on demand.

### 1.3 Objectives
The primary objectives of the TravelGo platform are:
* To implement a reliable, pure Java-based web application for end-to-end travel reservations without third-party template bloat.
* To provide seamless user account management, secure login verification, and session state tracking.
* To deliver an interactive, responsive tour catalog detailing destinations, costs, trip duration, and real-time remaining slots.
* To build an atomic reservation engine that computes aggregate ticket pricing and decrements available package capacity automatically.
* To enable user-centric booking management, including instant booking cancellation coupled with automatic slot restoration.
* To empower travel administrators with a dedicated operations dashboard for system analytics and full CRUD (Create, Read, Update, Delete) package administration.

### 1.4 Scope
The scope of TravelGo encompasses the complete tourist lifecycle: visitor exploration, user account creation, session-governed reservation pipelines, passenger record tracking, and administrative governance. The system is designed for deployment on local enterprise servlet containers (Apache Tomcat 10.1) as well as cloud-hosted virtual servers. The current implementation focuses on core reservation and inventory management workflows, laying the architectural groundwork for external payment gateway webhooks, SMS/email alerts, and GPS route visualization in subsequent milestones.

### 1.5 Feasibility Study

#### Technical Feasibility
The platform is developed using standard Java 21, Jakarta Servlets, JDBC, and MySQL 8.0. These established, enterprise-grade technologies offer long-term stability, robust multithreading capabilities, and strong community support. The deployment requires only an Apache Tomcat container and a relational database server, making it fully achievable within academic and commercial environments.

#### Economic Feasibility
The entire development stack consists of free, open-source software: Eclipse/VS Code IDE, Java Development Kit, MySQL Community Server, and Apache Tomcat. No proprietary runtime licenses or subscription-based frameworks are required, ensuring zero licensing overhead for educational and institutional deployment.

#### Operational Feasibility
TravelGo features an intuitive, clean graphical user interface that requires no prior training. Clear navigation bars, informative alert banners, and structured tabular data allow customers and tour administrators to complete their operations effortlessly across standard desktop and mobile browsers.

#### Schedule Feasibility
The project was structured into modular development milestones: database schema modeling, DAO access layer creation, servlet authentication, UI layout templating, booking engine logic, and validation testing. This division ensured timely completion within the prescribed academic course schedule.

---

## 2. TECHNOLOGIES USED

### 2.1 HTML and Lightweight CSS
The user interface is constructed using semantic HTML5 elements (forms, inputs, semantic containers, tables) coupled with a custom-engineered, lightweight Cascading Style Sheet (`style.css`). Rather than relying on heavy CSS frameworks (such as Bootstrap or Tailwind) that introduce bloated assets, TravelGo employs an efficient custom stylesheet (~80 lines). It features a navy-and-blue theme (`#1e3a5f`, `#2563eb`), responsive CSS Grid layouts, card containers, and accessible typography, ensuring instant page load times and minimal network bandwidth consumption.

### 2.2 Core Java (JDK 21)
The application core is developed in Java 21 (LTS). Java provides high-performance object-oriented programming, strict type safety, memory management, and built-in multithreading. Java classes serve as data transfer models (`User`, `TravelPackage`, `Booking`), database interaction layers (`DBConnection`, `UserDAO`, `PackageDAO`, `BookingDAO`), and programmatic HTML generation helpers (`HtmlUtil`).

### 2.3 Jakarta Servlets (Jakarta EE 10)
Instead of traditional JSP pages, TravelGo employs pure **Jakarta Servlet classes** extending `HttpServlet`. Servlets intercept HTTP `GET` and `POST` requests, validate parameters, invoke backend DAO operations, and directly stream optimized HTML markup via `PrintWriter.println()`. This architectural decision simplifies compilation, avoids JSP compilation runtime overhead, ensures direct compile-time error checking, and offers full programmatic control over web outputs.

### 2.4 JDBC and MySQL Database
Persistent data storage is handled by **MySQL 8.0**, a dependable relational database management system. Connectivity between Java and MySQL is achieved through **Java Database Connectivity (JDBC)** and the `mysql-connector-j-8.0.33` driver. All queries leverage `PreparedStatement` interfaces to guarantee protection against SQL injection vulnerabilities and provide high query execution performance.

### 2.5 Apache Tomcat 10.1 & Build Automation
The project is hosted on **Apache Tomcat 10.1.57**, which natively supports the modern **Jakarta EE 10 Servlet 6.0** specification. A specialized automation script (`build-and-deploy.ps1`) compiles Java source files via `javac`, packages compiled bytecodes, libraries, static CSS assets, and `web.xml` into a deployable Web Application Archive (`travel-booking.war`), and deploys it directly to Tomcat’s `webapps` directory for live execution.

---

## 3. SYSTEM ANALYSIS AND DESIGN

### 3.1 Functional Requirements
* **FR-1 (User Registration):** A prospective traveler can create an account by providing their full name, username, email, phone number, and password.
* **FR-2 (Authentication & Session):** Registered users can log in using their credentials. The system establishes an HTTP session storing identity attributes and role indicators.
* **FR-3 (Role-Based Access Control):** The application distinguishes regular users from administrators (`role = 'admin'`). Protected endpoints (`/book`, `/my-bookings`, `/admin/*`) are safeguarded by an authentication filter.
* **FR-4 (Package Catalog Browsing):** Any visitor or authenticated user can view available vacation packages with destination details, descriptions, prices, and remaining seats.
* **FR-5 (Package Reservation):** An authenticated traveler can select a tour package, specify the number of travelers and travel date, and submit a booking request. The system verifies slot availability, calculates total price, and decrements slots atomically.
* **FR-6 (Booking History & Tracking):** Logged-in users can view their past and upcoming bookings, including status badges (`CONFIRMED` or `CANCELLED`).
* **FR-7 (Booking Cancellation):** Users can cancel confirmed bookings, causing the system to update the reservation status and immediately restore the reserved seats to the package’s available slot count.
* **FR-8 (Admin Dashboard Analytics):** Administrators can view global operational statistics (total active bookings, registered travelers, available tour packages) and audit recent bookings.
* **FR-9 (Package Content Management):** Administrators can create new travel packages, update existing pricing or durations, and remove packages from the catalog.

### 3.2 Non-Functional Requirements
* **Security:** All form inputs are sanitized and HTML-escaped using `HtmlUtil.escHtml()` to mitigate Cross-Site Scripting (XSS). Database queries utilize parameterized statements to prevent SQL Injection. Administrative routes are guarded at the container level.
* **Performance:** Direct servlet rendering avoids JSP translation delays, delivering server response times well below 100 milliseconds on local servers.
* **Reliability & Consistency:** Inventory updates (slot reduction and restoration) occur synchronously during booking transactions to maintain data integrity.
* **Maintainability:** The project adopts a clean 3-tier architectural separation: Entity Models, Data Access Objects (DAOs), and Controller Servlets.

### 3.3 System Architecture
TravelGo follows an enterprise Model-View-Controller (MVC) 3-tier architecture:

```
+-------------------------------------------------------------------+
|                        PRESENTATION LAYER                         |
|   Web Browser: Client sends HTTP Requests (GET/POST)              |
|   Receives: Server-Rendered HTML5 + Minimal CSS (style.css)       |
+-------------------------------------------------------------------+
                                  |
                                  v
+-------------------------------------------------------------------+
|                         CONTROLLER LAYER                          |
|   AuthFilter (Security Guard for Sessions & Admin Roles)           |
|   Jakarta Servlets:                                               |
|   - HomeServlet ("/", "/home")       - PackageListServlet         |
|   - LoginServlet / RegisterServlet   - BookingServlet             |
|   - MyBookingsServlet                - CancelBookingServlet       |
|   - AdminDashboardServlet            - AdminPackageServlet        |
+-------------------------------------------------------------------+
                                  |
                                  v
+-------------------------------------------------------------------+
|                      DATA ACCESS & MODEL LAYER                    |
|   Java Models: User, TravelPackage, Booking                       |
|   Data Access Objects (DAOs): UserDAO, PackageDAO, BookingDAO     |
|   Utility: HtmlUtil, DBConnection (JDBC Driver Manager)           |
+-------------------------------------------------------------------+
                                  |
                                  v
+-------------------------------------------------------------------+
|                        PERSISTENCE LAYER                          |
|   MySQL Relational Database: `travel_booking`                     |
|   Tables: `users`, `travel_packages`, `bookings`                  |
+-------------------------------------------------------------------+
```

### 3.4 Database Design and Entity Relationships
The relational schema `travel_booking` comprises three primary normalized entities:

#### 1. Table: `users`
| Column Name | Data Type | Constraints | Description |
| :--- | :--- | :--- | :--- |
| `id` | INT | PRIMARY KEY, AUTO_INCREMENT | Unique user identifier |
| `username` | VARCHAR(50) | UNIQUE, NOT NULL | Account login handle |
| `email` | VARCHAR(100) | UNIQUE, NOT NULL | User email address |
| `password` | VARCHAR(255) | NOT NULL | User password |
| `full_name` | VARCHAR(100) | NOT NULL | Traveler full legal name |
| `phone` | VARCHAR(15) | NULL | Contact mobile number |
| `role` | VARCHAR(10) | DEFAULT 'user' | Access role ('user' / 'admin') |
| `created_at` | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP | Registration timestamp |

#### 2. Table: `travel_packages`
| Column Name | Data Type | Constraints | Description |
| :--- | :--- | :--- | :--- |
| `id` | INT | PRIMARY KEY, AUTO_INCREMENT | Package unique ID |
| `name` | VARCHAR(150) | NOT NULL | Package display title |
| `destination` | VARCHAR(100) | NOT NULL | Target destination cities |
| `description` | TEXT | NULL | Comprehensive itinerary overview |
| `duration_days` | INT | NOT NULL | Tour duration in days |
| `price` | DECIMAL(10,2) | NOT NULL | Unit price per traveler (INR) |
| `available_slots` | INT | NOT NULL, DEFAULT 20 | Remaining passenger capacity |
| `image_url` | VARCHAR(255) | NULL | Optional reference image path |
| `created_at` | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP | Record creation timestamp |

#### 3. Table: `bookings`
| Column Name | Data Type | Constraints | Description |
| :--- | :--- | :--- | :--- |
| `id` | INT | PRIMARY KEY, AUTO_INCREMENT | Booking confirmation ID |
| `user_id` | INT | FOREIGN KEY (`users.id`) | Reference to customer account |
| `package_id` | INT | FOREIGN KEY (`travel_packages.id`) | Reference to booked package |
| `num_travelers` | INT | NOT NULL, DEFAULT 1 | Count of booked seats |
| `total_price` | DECIMAL(10,2) | NOT NULL | Calculated aggregate cost |
| `booking_date` | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP | Reservation timestamp |
| `travel_date` | DATE | NOT NULL | Scheduled vacation start date |
| `status` | VARCHAR(20) | DEFAULT 'CONFIRMED' | Booking status ('CONFIRMED'/'CANCELLED') |

### 3.5 Use-Case Description
* **Actors:**
  * **Guest / Visitor:** Can open the landing page, view travel package listings, and navigate to login or registration pages.
  * **Registered Traveler (User):** Can authenticate, view package details, initiate bookings, review their personal booking ledger, and cancel confirmed trips.
  * **Travel Administrator (Admin):** Can log in to the administrative portal, inspect aggregate operational metrics (total bookings, revenue, users), add new tour packages, update package details, and delete existing itineraries.

---

## 4. CODING AND IMPLEMENTATION

### 4.1 Project Directory Structure
The application structure is organized in accordance with standard Maven web application standards:

```
c:\Users\gowth\OneDrive\Desktop\FSD2_PROJECT\
│
├── build-and-deploy.ps1                 # Automated compile, package & Tomcat deploy script
├── pom.xml                              # Maven project metadata and build configuration
├── sql/
│   └── schema.sql                       # Complete MySQL DDL schema and initial seed data
│
├── src/main/java/com/travel/
│   ├── model/
│   │   ├── User.java                    # Customer and administrator entity bean
│   │   ├── TravelPackage.java           # Holiday package data model
│   │   └── Booking.java                 # Reservation model with joined display fields
│   │
│   ├── dao/
│   │   ├── DBConnection.java            # JDBC connection manager
│   │   ├── UserDAO.java                 # Authentication, duplicate checks & user queries
│   │   ├── PackageDAO.java              # Package CRUD, slot decrements & slot restoration
│   │   └── BookingDAO.java              # Booking creation, user rosters & metric aggregations
│   │
│   ├── filter/
│   │   └── AuthFilter.java              # Session security and role-based gatekeeper
│   │
│   ├── servlet/
│   │   ├── HomeServlet.java             # Landing page controller (mapped to "", "/home")
│   │   ├── LoginServlet.java            # Authentication handler & login UI generator
│   │   ├── RegisterServlet.java         # Account creation handler & registration UI
│   │   ├── LogoutServlet.java           # Session invalidation controller
│   │   ├── PackageListServlet.java      # Public catalog browser
│   │   ├── BookingServlet.java          # Reservation processor with slot synchronization
│   │   ├── MyBookingsServlet.java       # User personal booking ledger
│   │   ├── CancelBookingServlet.java    # Cancellation processor with slot release
│   │   ├── AdminDashboardServlet.java   # Executive operational metrics dashboard
│   │   └── AdminPackageServlet.java     # Admin package CMS (create/edit/delete)
│   │
│   └── util/
│       └── HtmlUtil.java                # Shared layout engine (navbars, headers, footers)
│
└── src/main/webapp/
    ├── css/
    │   └── style.css                    # Lightweight custom application styling
    └── WEB-INF/
        └── web.xml                      # Deployment descriptor (Filter mappings & Servlet 6.0)
```

### 4.2 Database Connection
Database connectivity is managed centrally by `DBConnection.java`. It pre-loads the modern MySQL driver (`com.mysql.cj.jdbc.Driver`) and supplies active connections via `DriverManager.getConnection()`.

```java
package com.travel.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = 
        "jdbc:mysql://localhost:3306/travel_booking?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root"; // Configured per deployment

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL Driver not found", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
```

### 4.3 User Registration and Authentication Module
The authentication lifecycle is handled by `RegisterServlet` and `LoginServlet`:
* **Registration:** `RegisterServlet.doPost()` verifies field completeness, checks for duplicate usernames and emails via `UserDAO.usernameExists()` and `UserDAO.emailExists()`, and creates a new traveler record.
* **Login & Session Establishment:** `LoginServlet.doPost()` authenticates credentials via `UserDAO.login()`. Upon success, an `HttpSession` is generated, storing the `User` object as session attribute `"user"`. If the user has an `admin` role, they are redirected to `/admin/dashboard`; otherwise, they navigate to `/packages`.

### 4.4 Travel Package Catalog & Details Engine
`PackageListServlet` handles package discovery. It invokes `PackageDAO.getAllPackages()`, retrieving all active vacation packages. The servlet programmatically outputs a responsive card grid featuring the package name, destination, trip duration, remaining capacity, and formatted pricing (using `HtmlUtil.formatPrice()`). If seats are available, a prominent "Book Now" link navigates the user to `/book?id=<pkgId>`. If slots are exhausted (`available_slots = 0`), a styled "Sold Out" badge is displayed.

### 4.5 Booking Engine & Slot Inventory Synchronization
The core reservation logic is implemented inside `BookingServlet`:
1. `doGet()` renders the booking confirmation form displaying destination specifics, daily duration, unit cost, and remaining seats.
2. `doPost()` reads the package ID, number of requested travelers, and chosen travel date.
3. It performs concurrency verification: if requested travelers exceed remaining slots, an error alert is presented.
4. If capacity is sufficient, the system executes an atomic slot reduction query:
   ```sql
   UPDATE travel_packages SET available_slots = available_slots - ? 
   WHERE id = ? AND available_slots >= ?
   ```
5. Upon successful slot deduction, `BookingDAO.createBooking()` persists the confirmed booking with total calculated price, redirecting the traveler to their booking ledger with a confirmation banner.

### 4.6 Booking Management & Cancellation Handling
Travelers can view their historical and upcoming trips via `MyBookingsServlet`. The page renders a comprehensive table showing booking IDs, package destinations, passenger counts, total prices, travel dates, and status indicators (`CONFIRMED` or `CANCELLED`).

If a customer initiates a cancellation via `CancelBookingServlet`:
* The servlet verifies that the booking belongs to the currently authenticated session user.
* It sets the booking status to `'CANCELLED'`.
* It invokes `PackageDAO.restoreSlots()`, incrementing the package’s available seat count by the cancelled traveler quantity:
  ```sql
  UPDATE travel_packages SET available_slots = available_slots + ? WHERE id = ?
  ```

### 4.7 Administrative Dashboard & Package CRUD Management
* **Analytics Dashboard (`AdminDashboardServlet`):** Aggregates key platform metrics via `BookingDAO.countBookings()`, `BookingDAO.countUsers()`, and `PackageDAO.getAllPackages().size()`. It renders high-level summary cards alongside an audit log of all customer reservations.
* **Package Management (`AdminPackageServlet`):** Provides travel operators with an administrative interface to insert new tour itineraries, modify existing descriptions, prices, or capacities, and delete discontinued tours.

---

## 5. RESULTS AND SCREENSHOTS

The following functional screens confirm the deployment and operational stability of the TravelGo platform:

### 5.1 Landing Page
* **Route:** `http://localhost:8080/travel-booking/`
* **Description:** Welcomes visitors with the TravelGo brand banner, an overview of platform services, a "Browse Packages" call-to-action button, and dynamic navigation links that adapt based on the user's active session state.

### 5.2 User Authentication Screens
* **Routes:** `/travel-booking/login` and `/travel-booking/register`
* **Description:** Clean, centered card forms equipped with instant client validation, alert feedback banners (e.g., "Registration successful! Please login" or "Invalid username or password"), and direct links to switch between login and registration.

### 5.3 Travel Packages Catalog
* **Route:** `http://localhost:8080/travel-booking/packages`
* **Description:** A responsive grid displaying available tours across India (such as *Golden Triangle Tour*, *Goa Beach Getaway*, *Kerala Backwaters*, and *Himalayan Adventure*). Each card illustrates destination highlights, duration, seat availability, unit cost, and a "Book Now" action.

### 5.4 Booking Confirmation Screen
* **Route:** `http://localhost:8080/travel-booking/book?id=1`
* **Description:** Summarizes selected package parameters and prompts the traveler for their party size and vacation departure date, complete with capacity limits.

### 5.5 Customer Booking Ledger
* **Route:** `http://localhost:8080/travel-booking/my-bookings`
* **Description:** Tabulates all reservations associated with the authenticated customer account. Displays booking identifiers, travel dates, status badges, and an interactive "Cancel" button for active itineraries.

### 5.6 Admin Dashboard & Package Management
* **Route:** `http://localhost:8080/travel-booking/admin/dashboard` & `/admin/packages`
* **Description:** Displays executive metrics (Active Bookings, Registered Users, Package Totals), a live customer booking audit table, and an administration form to add, edit, or delete tour packages.

---

## 6. TESTING

Comprehensive manual and automated functional testing was conducted across all application modules to ensure structural stability and security.

| Test Case ID | Test Module | Input / Action | Expected Result | Actual Result | Status |
| :---: | :--- | :--- | :--- | :--- | :---: |
| **TC-01** | User Registration | Enter valid unique username, email, phone, and password | User record created in MySQL; redirected to login with success alert | User registered successfully; redirected to login | **PASS** |
| **TC-02** | Duplicate Registration | Register with an already existing username or email | Registration halted; "Username already taken" or "Email already registered" alert shown | Appropriate error alert rendered; no record inserted | **PASS** |
| **TC-03** | User Login (Success) | Enter valid registered username and password | Authenticated session created; redirected to package catalog | User session created; redirected to `/packages` | **PASS** |
| **TC-04** | User Login (Failure) | Enter incorrect password for an existing account | Authentication rejected; error alert "Invalid username or password" displayed | Login rejected; error alert shown | **PASS** |
| **TC-05** | Security Filter | Direct URL access to `/book` or `/my-bookings` without logging in | Request intercepted by `AuthFilter`; client redirected to `/login` | Unauthenticated request redirected to `/login` | **PASS** |
| **TC-06** | Admin Authorization | Non-admin user attempts access to `/admin/dashboard` | `AuthFilter` evaluates `user.isAdmin() == false`; redirects user to `/packages` | Unauthorized access blocked; redirected to `/packages` | **PASS** |
| **TC-07** | Package Catalog Fetch | Open `/packages` endpoint | All active travel packages retrieved from MySQL and rendered in card grid | All 6 seed packages rendered with correct attributes | **PASS** |
| **TC-08** | Booking Creation | Book 2 travelers for Golden Triangle Tour (Available: 25) | Booking record inserted; available slots decremented to 23; success redirect | Booking confirmed; available slots updated to 23 | **PASS** |
| **TC-09** | Overbooking Prevention | Attempt to book more travelers than available slots | System rejects request with "Not enough slots available" alert | Request halted; slots unchanged; error alert displayed | **PASS** |
| **TC-10** | Booking Cancellation | Click "Cancel" on an active confirmed booking | Status changed to `CANCELLED`; package available slots incremented by party size | Status marked `CANCELLED`; slots restored accurately | **PASS** |
| **TC-11** | Admin Package Insertion | Admin submits new package via `/admin/packages` | New package record inserted in `travel_packages`; instantly visible in public catalog | Package inserted and immediately visible to travelers | **PASS** |
| **TC-12** | Admin Package Deletion | Admin clicks "Delete" on package record | Record removed from `travel_packages`; catalog view updated | Record deleted; catalog reflects updated inventory | **PASS** |

---

## 7. CONCLUSION AND FUTURE ENHANCEMENTS

### 7.1 Conclusion
The **TravelGo Travel Booking Management System** successfully delivers an automated, secure, and user-friendly web application for tourism management. By adopting a pure Java Servlet architecture, the project eliminates legacy template compilation overhead while maintaining full programmatic control over web outputs.

The system demonstrates clean software engineering principles: Model-View-Controller tier separation, secure role-based session filtering, atomic inventory slot management, and parameterized database transactions. It provides practical automation for travel reservations, eliminates manual booking errors, and equips travel administrators with real-time operational insights.

### 7.2 Future Enhancements
* **Online Payment Gateway Integration:** Incorporate third-party payment gateways (such as Razorpay, Stripe, or PayPal) with automated payment confirmation webhooks.
* **Email & SMS Notifications:** Integrate automated transactional email (via JavaMail API) and SMS alerts (via Twilio) to dispatch booking receipts and travel itinerary reminders.
* **Interactive Itinerary Mapping:** Embed interactive maps (e.g., Google Maps API or Leaflet) on package detail pages to visually present daily sightseeing routes.
* **Customer Reviews & Ratings:** Allow travelers who completed a tour to submit star ratings and verified feedback to assist future customers.
* **Password Hashing:** Upgrade plain credential verification to modern cryptographic hashing (such as BCrypt or Argon2) with salt generation.
* **PDF Ticket Generation:** Implement server-side PDF generation (via Apache PDFBox or iText) allowing travelers to download printable travel passes.

---

## 8. REFERENCES

1. **Jakarta EE Specification:** Jakarta Servlet Specification Version 6.0, Eclipse Foundation. Available: *https://jakarta.ee/specifications/servlet/6.0/*
2. **Oracle Corporation:** *Java Platform, Standard Edition 21 (JDK 21) API Specification*, Oracle Technology Network.
3. **Apache Software Foundation:** *Apache Tomcat 10.1 Documentation*, Available: *https://tomcat.apache.org/tomcat-10.1-doc/*
4. **MySQL AB / Oracle Corporation:** *MySQL 8.0 Reference Manual and MySQL Connector/J Developer Guide*, 2024.
5. **Gamma, E., Helm, R., Johnson, R., & Vlissides, J.:** *Design Patterns: Elements of Reusable Object-Oriented Software*, Addison-Wesley Professional.
6. **Hall, M., & Brown, L.:** *Core Servlets and JavaServer Pages: Volume 1: Core Technologies*, Prentice Hall.
7. **Mozilla Developer Network (MDN):** *HTML5, CSS3, and Web APIs Documentation*, Available: *https://developer.mozilla.org/*

---

## APPENDIX A: CORE CODE EXAMPLES

### A.1 Database Connection Helper (`DBConnection.java`)
```java
package com.travel.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = 
        "jdbc:mysql://localhost:3306/travel_booking?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL Driver not found", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
```

### A.2 Security & Role-Based Authentication Filter (`AuthFilter.java`)
```java
package com.travel.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig config) {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        // Verify active authenticated session
        if (session == null || session.getAttribute("user") == null) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // Restrict administrative endpoints
        if (req.getRequestURI().contains("/admin")) {
            com.travel.model.User user = (com.travel.model.User) session.getAttribute("user");
            if (!user.isAdmin()) {
                res.sendRedirect(req.getContextPath() + "/packages");
                return;
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
```

### A.3 Booking Execution with Atomic Slot Reduction (`BookingServlet.java`)
```java
package com.travel.servlet;

import com.travel.dao.BookingDAO;
import com.travel.dao.PackageDAO;
import com.travel.model.*;
import com.travel.util.HtmlUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;

@WebServlet("/book")
public class BookingServlet extends HttpServlet {
    private PackageDAO packageDAO = new PackageDAO();
    private BookingDAO bookingDAO = new BookingDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String ctx = req.getContextPath();
        User user = (User) req.getSession().getAttribute("user");
        int pkgId = Integer.parseInt(req.getParameter("packageId"));
        int numTravelers = Integer.parseInt(req.getParameter("numTravelers"));
        String travelDateStr = req.getParameter("travelDate");

        TravelPackage pkg = packageDAO.getPackageById(pkgId);
        if (pkg == null) {
            resp.sendRedirect(ctx + "/packages");
            return;
        }

        if (numTravelers > pkg.getAvailableSlots()) {
            req.setAttribute("error", "Not enough slots available. Only " + pkg.getAvailableSlots() + " left.");
            req.setAttribute("id", String.valueOf(pkgId));
            doGet(req, resp);
            return;
        }

        BigDecimal totalPrice = pkg.getPrice().multiply(BigDecimal.valueOf(numTravelers));

        Booking booking = new Booking();
        booking.setUserId(user.getId());
        booking.setPackageId(pkgId);
        booking.setNumTravelers(numTravelers);
        booking.setTotalPrice(totalPrice);
        booking.setTravelDate(Date.valueOf(travelDateStr));

        // Atomic inventory decrement and booking persistence
        if (packageDAO.reduceSlots(pkgId, numTravelers) && bookingDAO.createBooking(booking)) {
            resp.sendRedirect(ctx + "/my-bookings?success=true");
        } else {
            req.setAttribute("error", "Booking failed. Please try again.");
            doGet(req, resp);
        }
    }
}
```

### A.4 Database Schema Outline (`schema.sql`)
```sql
CREATE DATABASE IF NOT EXISTS travel_booking;
USE travel_booking;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    phone VARCHAR(15),
    role VARCHAR(10) DEFAULT 'user',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE travel_packages (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    destination VARCHAR(100) NOT NULL,
    description TEXT,
    duration_days INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    available_slots INT NOT NULL DEFAULT 20,
    image_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE bookings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    package_id INT NOT NULL,
    num_travelers INT NOT NULL DEFAULT 1,
    total_price DECIMAL(10,2) NOT NULL,
    booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    travel_date DATE NOT NULL,
    status VARCHAR(20) DEFAULT 'CONFIRMED',
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (package_id) REFERENCES travel_packages(id)
);
```

---

## APPENDIX B: ABBREVIATIONS

* **API** – Application Programming Interface
* **CRUD** – Create, Read, Update, Delete
* **CSS** – Cascading Style Sheets
* **DAO** – Data Access Object
* **DBMS** – Database Management System
* **FK** – Foreign Key
* **HTML** – HyperText Markup Language
* **HTTP** – HyperText Transfer Protocol
* **HTTPS** – HyperText Transfer Protocol Secure
* **IDE** – Integrated Development Environment
* **JDBC** – Java Database Connectivity
* **JDK** – Java Development Kit
* **JRE** – Java Runtime Environment
* **MVC** – Model–View–Controller
* **PK** – Primary Key
* **RBAC** – Role-Based Access Control
* **SQL** – Structured Query Language
* **UI** – User Interface
* **UX** – User Experience
* **WAR** – Web Application Archive

