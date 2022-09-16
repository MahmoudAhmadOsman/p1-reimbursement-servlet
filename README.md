## Java Enterprise - Reimbursement System

### Project Description

This system will manage the process of reimbursing employees for expenses incurred while on company time. All registered employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests. Finance managers can log in and view all reimbursement requests and past history for all employees in the company. Finance managers are authorized to approve and deny requests for expense reimbursement.

### Project Design Specifications and Documents

##### Relational Data Model
![Relational Model](https://github.com/MahmoudAhmadOsman/reimbursement-system/blob/main/src/main/resources/design/ERS%20Relational%20Model.png)

##### Reimbursement Types
Reimbursements are to be one of the following types:
- LODGING
- TRAVEL
- FOOD
- OTHER

##### System Use Case Diagrams
![System Use Case Diagrams](https://github.com/MahmoudAhmadOsman/reimbursement-system/blob/main/src/main/resources/design/ERS%20Use%20Case%20Diagram.png)

##### Reimbursment Status State Flow
![Reimbursment Status State Flow](https://github.com/MahmoudAhmadOsman/reimbursement-system/blob/main/src/main/resources/design/ERS%20State%20Flow%20Diagram.png)

### Technologies

**Persistence Tier**
- PostGreSQL (running on Docker)

**Application Tier**
- Written in Java 8.
- Intellij
- Amazon AWS
- Apache Maven
- JDBC
- Java EE Servlets
- JSON Web Tokens
- JUnit
- Mockito
-  POSTMAN


### Functional Requirements

- An new employee or new finance manager can request registration with the system
- An admin user can approve or deny new registration requests
- The system will register the user's information for payment processing
- A registered employee can authenticate with the system by providing valid credentials
- An authenticated employee can view and manage their pending reimbursement requests
- An authenticated employee can view their reimbursement request history (sortable and filterable)
- An authenticated employee can submit a new reimbursement request
- An authenticated finance manager can view a list of all pending reimbursement requests
- An authenticated finance manager can view a history of requests that they have approved/denied
- An authenticated finance manager can approve/deny reimbursement requests
- The system will send a payment request when a reimbursement request is approved
- An admin user can deactivate user accounts, making them unable to log into the system
- An admin user can reset a registered user's password

### Non-Functional Requirements

- Basic validation is enforced to ensure that invalid/improper data is not persisted
- User passwords will be encrypted by the system before persisting them to the data source
- Users are unable to recall reimbursement requests once they have been processed (only pending allowed)
- Sensitive endpoints are protected from unauthenticated and unauthorized requesters using JWTs
- Errors and exceptions are handled properly and their details are obfuscated from the user
- The system conforms to RESTful architecture constraints
- The system's is tested with at least 80% line coverage in all service and utility classes
- The system's data schema and component design is documented and diagrammed
- The system keeps detailed logs on info, error, and fatal events that occur

### Other Features
- Authenticated employees are able to upload an receipt image along with their reimbursement request
- The system notifies the user of changes to their account registration status by email
- The system notifies the user of changes to their reimbursement request status by email
- Document your API using a tool like OpenAPI/Swagger
- Run your application within a Docker container
- Automate builds using GitHub Actions



### Technical Scoring Achievements

| Requirement                                                                                       | Functional/Non-Functional | Value |
|---------------------------------------------------------------------------------------------------|---------------------------|-------|
| An new employee or new finance manager can request registration with the system                   | Functional                | 10    |
| An admin user can approve or deny new registration requests                                       | Functional                | 5     |
| A registered employee can authenticate with the system by providing valid credentials             | Functional                | 10    |
| An authenticated employee can view and manage their pending reimbursement requests                | Functional                | 10    |
| An authenticated employee can view their reimbursement request history (sortable and filterable)  | Functional                | 10    |
| An authenticated employee can submit a new reimbursement request                                  | Functional                | 10    |
| An authenticated finance manager can view a list of all pending reimbursement requests            | Functional                | 10    |
| An authenticated finance manager can view a history of requests that they have approved/denied    | Functional                | 10    |
| An authenticated finance manager can approve/deny reimbursement requests                          | Functional                | 10    |
| An admin user can deactivate user accounts, making them unable to log into the system             | Functional                | 5     |
| An admin user can reset a registered user's password                                              | Functional                | 5     |
| Basic validation is enforced to ensure that invalid/improper data is not persisted                | Non-Functional            | 10    |
| User passwords will be encrypted by the system before persisting them to the data source          | Non-Functional            | 5     |
| Users are unable to recall reimbursement requests once they have been processed                   | Non-Functional            | 10    |
| Sensitive endpoints are protected from unauthenticated and unauthorized requesters                | Non-Functional            | 5     |
| Errors and exceptions are handled properly and their details are obfuscated from the user         | Non-Functional            | 10    |
| The system conforms to RESTful architecture constraints                                           | Non-Functional            | 10    |
| The system's is tested with at least 80% line coverage in all service and utility classes         | Non-Functional            | 10    |
| The system's data schema and component design is documented and diagrammed                        | Non-Functional            | 5     |
| The system keeps detailed logs on info, error, and fatal events that occur                        | Non-Functional            | 5     |

 





________________________________________________________________________________________________



# POSTMAN TESTING ROUTES


### USERS ROUTES

 SIGNUP
 - POST: http://localhost:8080/northern/users/signup

```bash

 {
    "username" : "osman8080",
    "password1" : "osman8080",
     "password2" : "osman8080",
    "email": "osman8080@gmail.com",
    "given_name": "Mahmoud",
    "surname" : "Osman"

    
}
 
```

  GET ALL USERS
 - GET: http://localhost:8080/northern/users




### USER ROLES ROUTE

ADD NEW USER ROLE

- POST: http://localhost:8080/northern/users/roles


```bash

 {
    "role_id": "305",
    "role": "MARKETING"
}
 
```


GET ALL USERS ROLES

 - GET: http://localhost:8080/northern/users/roles

DELETE USER ROLE 
- DELETE:  http://localhost:8080/northern/users/roles

GET USER ROLE BY ID

- GET: http://localhost:8080/northern/users/roles



### REIMBURSEMENT TYPES ROUTE
 
ADD NEW REIMBURSEMENT TYPE

- POST: http://localhost:8080/northern/types


```bash

 {
    "type_id" : "100",
    "type": "CELLPHONE PAYMENT"
}


```


GET LIST OF REIMBURSEMENT TYPES 
- GET: http://localhost:8080/northern/types



DELETE REIMBURSEMENT TYPE BY ID

- DELETE: http://localhost:8080/northern/types


UPDATE REIMBURSEMENT TYPE
- UPDATE: http://localhost:8080/northern/types



### REIMBURSEMENT STATUS ROUTES

ADD NEW REIMBURSEMENT STATUS

- POST: http://localhost:8080/northern/types/status

```bash

{
"amount": "80.65",
"description": "CELLPHONE  PAYMENT",
"receipt": "PAID",
"payment_id":"400",
"author_id": "6652d73b-c01a-4213-82e1-b1ac579e3682",
"resolver_id": "6652d73b-c01a-4213-82e1-b1ac579e3682",
"status_id": "300",
"type_id":"202"

}


```



GET ALL REIMBURSEMENTS STATUS 

- GET: http://localhost:8080/northern/types/status


 

### Contributors
- [www.mahmoudosman.com](http://www.mahmoudosman.com/)
