# **EventTrackerProject**

## **Overview**

| **Homework (Week 12) - Skill Distillery** |
|-------------------------------------------|
| The Event Tracker Project is a RESTful API designed to manage expenses. Built with **MySQL**, **Spring Data JPA**, and **Spring Boot**, the application allows for a user to perform CRUD operations on expenses and organize them by categories and payment methods. The purpose of this project is to create a backend API that provides endpoints for interacting with expenses data. |
| A front-end will be developed next week to interact with the API. |

### **Rest Endpoints**

| HTTP Verb | URI                                         | Request Body                             | Response Body                                  | Response Codes  |
|-----------|---------------------------------------------|------------------------------------------|------------------------------------------------|-----------------|
| GET       | `/api/expenses`                             |                                          | List of all expenses                           | 200             |
| GET       | `/api/expenses/{expenseId}`                 |                                          | Representation of a specific expense           | 200, 404        |
| GET       | `/api/expenses/categories/{categoryId}`     |                                          | List of expenses for a specific category       | 200, 404        |
| GET       | `/api/expenses/payments/{paymentMethodId}`  |                                          | List of expenses for a specific payment method | 200, 404        |
| POST      | `/api/expenses`                             | Representation of a new _expense_        | Representation of the created expense          | 201, 400        |
| PUT       | `/api/expenses/{expenseId}`                 | Representation of an updated _expense_   | Representation of the updated expense          | 200, 404, 400   |
| DELETE    | `/api/expenses/{expenseId}`                 |                                          |                                                | 204, 404, 400   |

- https://www.postman.com/downloads/

### **Methodologies Used:**
- **RESTful Design:**
  - Created an API with clear endpoints that represent resources.
  - Followed best practices for resource naming conventions to create readable and predictable URIs.
- Test-Driven Development (TDD)
  - Used JUnit to verify entity mappings.
  - TODO - stretch

## **Technologies Used**
- Java
- Spring Boot
- JPA: Java Persistence API
- MySQL, MySQL Workbench
- AWS
- git, GitHub
- Postman
 
## **Lessons Learned** 
- **Understanding MySQL Workbench**
  - Gained experience with designing and visualizing database schemas.
- **Understanding APIs:** 
  - Gained experience with how to structure APIs to serve as a bridge between backend systems and (future) frontend applications.
  - Tested - using Postman to verify all CRUD operations and potential errors.

## **Resources**
- https://github.com/SkillDistillery/SD44/blob/main/rest/EventTracker/README.md
- https://www.restapitutorial.com/introduction/resourcenaming
- https://www.restapitutorial.com/httpstatuscodes

