Order Processing System
A Spring Boot application for managing and processing orders in an e-commerce platform. It supports order creation, status tracking, and metrics reporting while using MySQL for storage and an in-memory queue for order processing.

1. Tech Stack

Java 21
Spring Boot 3+
Spring Data JPA
MySQL
ExecutorService (for async queue processing)
Spring Boot Actuator (for metrics)


2. Setup & Installation

Prerequisites
Java 21
MySQL installed & running


Steps to Run Locally:
Clone the Repository
Set Up MySQL Database: 
Open MySQL CLI or MySQL Workbench and run:
CREATE DATABASE order_db; also update the src/main/resources/application.properties with your MySQL credentials

Finally, build and run the application:
mvn clean package
mvn spring-boot:run


3. API Endpoints

3.1 Create an Order
Request:
curl -X POST "http://localhost:8080/orders" -H "Content-Type: application/json" -d '{
    "userId": 1,
    "itemIds": [101, 102, 103],
    "totalAmount": 250.50
}'

Response:
{
    "orderId": "d85b3b1e-9b68-4ebc-818f-f83265c2e2c4",
    "status": "PENDING"
}

3.2 Get Order Status
Request:
curl -X GET "http://localhost:8080/orders/d85b3b1e-9b68-4ebc-818f-f83265c2e2c4"

Response:
{
    "orderId": "d85b3b1e-9b68-4ebc-818f-f83265c2e2c4",
    "status": "COMPLETED"
}

3.3 Get Total Orders
Request:
curl -X GET "http://localhost:8080/metrics/total-orders"

Response:
{
    "totalOrders": 10
}

3.4 Get Orders Count by Status
Request:
curl -X GET "http://localhost:8080/metrics/orders-by-status"

Response:
{
    "PENDING": 5,
    "PROCESSING": 2,
    "COMPLETED": 3
}


4. Design Decisions & Trade-offs

4.1 Order Processing with In-Memory Queue
Used BlockingQueue with ExecutorService for async processing.
Trade-off: Simple but not persistent, meaning orders in queue will be lost if the service crashes.

4.2 MySQL as the Database
Chose MySQL for scalability and ACID compliance.
Trade-off: Not as fast as NoSQL for heavy write operations.

4.3 Spring Boot for REST API
Used Spring Boot 3+ for quick development and easy integration with Spring Data JPA.

4.4 Metrics Tracking with Actuator
Implemented API for total orders count and order status tracking.
Trade-off: More advanced monitoring (e.g., Prometheus) could be added.


5. Assumptions
Orders only transition from PENDING -> PROCESSING -> COMPLETED.
Processing time is simulated using Thread.sleep(2000) (2 seconds per order).
Concurrent order processing is limited by queue size (can be optimized using Kafka/RabbitMQ).
User authentication is not required, assuming a trusted internal system.


6. Future Enhancements
Use Kafka/RabbitMQ instead of an in-memory queue.
Add Redis cache to speed up order status retrieval.
Implement JWT authentication for security.
Deploy on Docker & Kubernetes for scalability.


7. Contributors
👤  Dhruwanth
✉️ dhruwanthreddy@gmail.com