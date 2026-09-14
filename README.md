# FreelanceHub

FreelanceHub is a full-stack web application designed to help independent freelancers manage the complete lifecycle of their work — from onboarding a new client to getting paid for completed work.

The core idea is simple: a freelancer signs up, adds the clients they work with, creates projects under each client with an agreed hourly rate, and breaks each project down into concrete tasks. As work progresses, time spent on each task is logged. At the end of a billing period, the freelancer selects a client and a date range, and the application automatically aggregates all logged hours into a formal invoice — calculating the total amount owed, grouping the work by project, and generating a downloadable PDF ready to send to the client. Invoices can then be tracked through their lifecycle (draft, sent, paid, overdue), and a dashboard provides an at-a-glance summary of active projects, hours logged in the current month, and outstanding payments.

Beyond the user-facing functionality, this project was built as a deliberate exercise in production-grade software engineering practices, including:

Secure authentication — JWT-based login with password hashing (BCrypt), ensuring each user's data is fully isolated from other users
Relational data modeling — a domain built around meaningful entity relationships (Client → Project → Task → TimeEntry → Invoice), with database migrations managed as code (Flyway)
Business logic enforcement — rules like preventing deletion of clients with active projects, or preventing edits to paid invoices, implemented at the service layer rather than left to the frontend
RESTful API design — clear separation between entities and DTOs, consistent error handling, input validation, and pagination for list endpoints
Automated testing — unit tests for business logic (Mockito) and integration tests covering the persistence and web layers
Containerized deployment — packaged with Docker and docker-compose for consistent local and production environments
CI pipeline — automated build and test execution on every push via GitHub Actions

The frontend is built with Angular, using reactive forms for data entry and an HTTP interceptor to handle authentication tokens transparently across all API calls.

This project was developed incrementally, feature by feature, following a structured set of user stories — mirroring how real-world software teams break down and deliver work.
