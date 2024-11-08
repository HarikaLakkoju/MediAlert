📲 MediAlert - Medication Reminder App
MediAlert is a medication reminder application built with 💻 Spring Boot. This app helps users manage their medication schedules by sending automated 📱 SMS reminders through the Twilio API. Swagger is used for API documentation, and SLF4J provides structured logging for easy monitoring.

✨ Features
👤 User Management: Easily manage user details with CRUD operations.
💊 Medication Management: Manage medications and schedules with CRUD operations.
📩 SMS Reminders: Sends automated SMS reminders to users at specified times.
📜 API Documentation: Interactive Swagger UI for testing and exploring the API.
📝 Logging: SLF4J-powered logging for monitoring and debugging.
⚙️ Technologies Used
Spring Boot - Backend framework for handling API endpoints and business logic.
Swagger - For interactive API documentation.
SLF4J - Logging API for tracking application behavior.
Twilio API - For sending SMS notifications as medication reminders.
🚀 API Endpoints
👤 User Endpoints
GET /api/users - Retrieve all users.
GET /api/users/{id} - Retrieve user details by user ID.
POST /api/users - Add a new user.
PUT /api/users/{id} - Update user details by user ID.
DELETE /api/users/{id} - Delete a user by user ID.
💊 Medication Endpoints
GET /api/users/{userId}/medications - Retrieve all medications for a specific user.
POST /api/users/{userId}/medications - Add a medication for a specific user.
DELETE /api/users/{userId}/medications/{id} - Delete a medication by ID for a specific user.
<img src="assets/Entities.png" alt="SMS Example" width="500">
🛠️ Getting Started
Prerequisites
Java 11 or later ☕
Maven 📦
Twilio Account (for sending SMS reminders)
Installation
Clone the repository:

bash
Copy code
git clone https://github.com/your-username/MediAlert.git
Navigate into the project directory:

bash
Copy code
cd MediAlert
Install dependencies:

bash
Copy code
mvn install
Configure Twilio credentials in application.properties:

properties
Copy code
twilio.accountSid=your_account_sid
twilio.authToken=your_auth_token
twilio.fromPhoneNumber=your_twilio_phone_number
Run the application:

bash
Copy code
mvn spring-boot:run
📖 Usage
Access the Swagger API Documentation:

Open a browser and navigate to:

bash
Copy code
http://localhost:9999/swagger-ui/index.html
Explore the API: Use the Swagger interface to test the API endpoints for user and medication management.

📩 SMS Notification Example
This is an example of an SMS reminder that will be sent to the user:
<img src="assets/sms.jpeg" alt="SMS Example" width="500">

📄 Swagger API Documentation
The application includes an interactive Swagger UI for exploring and testing the API.
<img src="assets/SwaggerDocumentation.png" alt="SMS Example" width="500">

📝 Logging
MediAlert uses SLF4J for structured logging, helping track application activity, including API calls and error handling.

🤝 Contributing
Want to contribute? 🎉 Follow these steps:

Fork the repository.
Create a new branch (feature/YourFeature).
Commit your changes.
Push to the branch.
Open a Pull Request.
We welcome all contributions to improve MediAlert! 🚀
