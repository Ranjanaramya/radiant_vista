# radiant_vista
A full-stack web-based system designed to manage lighting and sound decoration services, customer bookings, payments, contact queries, and admin-side service management. Built with Java Spring Boot, HTML/CSS/JS, and follows a clean MVC architecture.
Web Application
--------------------------------------------------

Features
--------
Customer Features:
- Browse lighting and sound decoration services
- View pricing and service categories
- Make service selections and proceed to payment
- User-friendly and responsive interface
- Contact form for customer inquiries

Admin Features:
- Admin login system
- Dashboard for quick navigation
- Add, edit, or delete services
- View contact form submissions
- Manage lighting and sound service listings

Tech Stack
----------
Backend:
- Java 17
- Spring Boot
- Spring MVC
- Spring Data JPA

Frontend:
- HTML5, CSS3
- JavaScript
- Bootstrap or Tailwind
- Thymeleaf Templates

Build Tools:
- Maven
- IntelliJ IDEA

Project Structure
-----------------
src/
 ├── main/
 │    ├── java/com/example/rvlights/
 │    │       ├── controllers/
 │    │       ├── models/
 │    │       ├── repositories/
 │    │       └── RvlightsProjectApplication.java
 │    │
 │    └── resources/
 │          ├── templates/
 │          │     ├── admin/
 │          │     └── customer/
 │          ├── static/
 │          │     ├── css/
 │          │     ├── js/
 │          │     └── images/
 │          └── application.properties
 │
 └── test/

How to Run
----------
1. Install Java 17 and Maven.
2. Open the project in IntelliJ IDEA.
3. Let Maven load all dependencies.
4. Run the main application file: RvlightsProjectApplication.java
5. Open your browser and go to: http://localhost:8081

License
-------
This project is released under the MIT License.
